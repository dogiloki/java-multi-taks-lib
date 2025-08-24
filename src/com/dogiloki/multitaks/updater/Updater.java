package com.dogiloki.multitaks.updater;

import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.directory.ModelDirectory;
import com.dogiloki.multitaks.directory.Storage;
import com.dogiloki.multitaks.download.Download;
import com.dogiloki.multitaks.download.enums.DownloadStatus;
import com.dogiloki.multitaks.updater.contracts.UpdatableApp;
import com.dogiloki.multitaks.updater.enums.UpdateStatus;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author _dogi
 */

public abstract class Updater extends ModelDirectory implements UpdatableApp{
    
    private final UpdaterConfig cfg;
    private final String base_directory;
    private final Storage backup;
    private final Storage update;
    private final UpdateItems _update_items=new UpdateItems();
    private int _total_downloads;
    private AtomicInteger _finished_downloads;
    private Manifest remote_manifest;
    private UpdateStatus _status;
    private boolean apply_after_download=false;
    
    public Updater(String base_directory, UpdaterConfig cfg){
        if(base_directory==null || base_directory.trim().isEmpty()){
            base_directory=".";
        }
        super.aim(base_directory);
        this.cfg=cfg;
        this.base_directory=base_directory+"/updates";
        this.backup=new Storage(this.base_directory+"/backup");
        this.update=new Storage(this.base_directory+"/update");
        this.setTotalDownloads(0);
        this.setFinishedDownloads(0);
        this.changeStatus(UpdateStatus.IDLE);
    }
    
    public void start(){
        this.applyAfterDownload(true);
        this.downloadUpdate();
    }
    
    public Updater applyAfterDownload(boolean b){
        this.apply_after_download=b;
        return this;
    }
    
    public boolean isApplyAfterDownload(){
        return this.apply_after_download;
    }
    
    protected synchronized Updater changeStatus(UpdateStatus status, Exception ex){
        return this._changeStatus(status,ex);
    }
    
    protected synchronized Updater changeStatus(UpdateStatus status){
        return this._changeStatus(status,null);
    }
    
    private synchronized Updater _changeStatus(UpdateStatus status, Exception ex){
        this._status=status;
        if(this._status==UpdateStatus.COMPLETED){
            this.onComplete();
            this.onProgress();
        }else if(status==UpdateStatus.FAILED){
            this.onError(ex);
            this.onProgress();
        }else{
            this.onProgress();
        }
        return this;
    }
    
    protected synchronized UpdateStatus getStatus(){
        return this._status;
    }
    
    protected void loadRemoteManifest(boolean bloking){
        this.changeStatus(UpdateStatus.CHECKING);
        Download download=new Download(this.cfg.getUrlManifest(),this.base_directory+"/"+this.cfg.manifest_file_name)
                .overwriteIfExists(true);
        if(bloking){
            download.startBlocking();
             this.remote_manifest=new Manifest(this.base_directory).builder();
        }else{
            download.onMetrics((metrics)->{
                if(metrics.status==DownloadStatus.FINALIZED){
                    this.remote_manifest=new Manifest(this.base_directory).builder();
                }
            });
            download.start();
        }
    }
    
    public synchronized UpdateItems getUpdateItems(){
        return this._update_items;
    }
    
    public synchronized int getTotalDownloads(){
        return this._total_downloads;
    }
    
    private synchronized Updater addTotalDownloads(int value){
        this._total_downloads+=value;
        return this;
    }
    
    private synchronized Updater setTotalDownloads(int value){
        this._total_downloads=value;
        return this;
    }
    
    public synchronized AtomicInteger getFinishedDownloads(){
        return this._finished_downloads;
    }
    
    private synchronized int addFinishedDownloads(int value){
        return this._finished_downloads.addAndGet(value);
    }
    
    private synchronized Updater setFinishedDownloads(int value){
        this._finished_downloads=new AtomicInteger(value);
        return this;
    }
    
    public synchronized int getPercentDownloads(){
        if(this.getTotalDownloads()==0){
            return 100;
        }
        int done=this.getFinishedDownloads().get();
        return (int)(done*100.0f)/this.getTotalDownloads();
    }

    @Override
    public boolean checkForUpdates(){
        this.loadRemoteManifest(true);
        boolean needs_update=Function.compareVersion(this.getCurrentVersion(),this.getLastVersion())<0;
        this.changeStatus(needs_update?UpdateStatus.UPDATE:UpdateStatus.NO_UPDATE);
        return needs_update;
    }

    @Override
    public void downloadUpdate(){
        this.changeStatus(UpdateStatus.DOWNLOADING);
        this.getUpdateItems().clear();
        this.setTotalDownloads(0);
        this.setFinishedDownloads(0);
        for(UpdateFile update_file:this.remote_manifest.files){
            String url=this.cfg.getUrl(update_file.path);
            UpdateItem update_item=new UpdateItem(
                    new Storage(this.getSrc()+"/"+update_file.path),
                    new Storage(this.backup.getSrc()+"/"+update_file.path),
                    new Storage(this.update.getSrc()+"/"+update_file.path)
            );
            if(!update_item.current.exists() || !update_item.current.hashing().equals(update_file.hash)){
                try{
                    this.addTotalDownloads(1);
                    Download download=new Download(url,update_item.last.getSrc())
                            .overwriteIfExists(true)
                            .start();
                    download.onMetrics((metrics)->{
                        if(metrics.status==DownloadStatus.FINALIZED){
                            this.getUpdateItems().add(update_item);
                            int done=this.addFinishedDownloads(1);
                            if(done==this.getTotalDownloads()){
                                this.changeStatus(UpdateStatus.DOWNLOAD_COMPLETED);
                                if(this.isApplyAfterDownload()){
                                    this.applyUpdate();
                                }
                            }
                        }
                        this.changeStatus(UpdateStatus.DOWNLOADING);
                    });
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }
        if(this.getTotalDownloads()==0){
            this.changeStatus(UpdateStatus.NO_UPDATE);
        }
    }

    @Override
    public void applyUpdate(){
        if(this.getStatus()!=UpdateStatus.DOWNLOAD_COMPLETED) return;
        if(this.getUpdateItems().size()<=0){
            this.changeStatus(UpdateStatus.NO_UPDATE);
            return;
        }
        for(UpdateItem update_item:this.getUpdateItems()){
            try{
                update_item.applyWithBackup();
            }catch(Exception ex){
                ex.printStackTrace();
                this.changeStatus(UpdateStatus.FAILED,ex);
            }
            this.changeStatus(UpdateStatus.APPLYING);
        }
        this.changeStatus(UpdateStatus.COMPLETED);
    }
    
    public synchronized int getPercentApplyUpdate(){
        int done=this.getUpdateItems().getCountApplied();
        return (int)(done*100.0f)/this.getUpdateItems().size();
    }

    @Override
    public String getCurrentVersion(){
        return this.cfg.version;
    }
    
    @Override
    public String getLastVersion(){
        if(this.remote_manifest==null){
            this.changeStatus(UpdateStatus.FAILED);
            return null;
        }
        return this.remote_manifest.version;
    }

    @Override
    public synchronized void applyBackup(){
        System.out.println("PENDIENTE!!!");
    }

    @Override
    public synchronized void onProgress(){
        
    }

    @Override
    public synchronized void onError(Exception ex){
        
    }

    @Override
    public synchronized void onComplete(){
        
    }
    
}
