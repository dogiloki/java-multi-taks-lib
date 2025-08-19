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
    private String base_directory;
    private Storage backup;
    private Storage update;
    private int total_downloads=0;
    private Manifest remote_manifest;
    private UpdateStatus _status;
    private UpdateItems update_items=new UpdateItems();
    private boolean apply_after_download=false;
    
    public Updater(String base_directory){
        if(base_directory==null || base_directory.trim().isEmpty()){
            base_directory=".";
        }
        super.aim(base_directory);
        this.cfg=new UpdaterConfig().builder();
        this.base_directory=base_directory+"/updates";
        this.backup=new Storage(this.base_directory+"/backup");
        this.update=new Storage(this.base_directory+"/update");
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
    
    protected Updater changeStatus(UpdateStatus status, Exception ex){
        return this._changeStatus(status,ex);
    }
    
    protected Updater changeStatus(UpdateStatus status){
        return this._changeStatus(status,null);
    }
    
    private Updater _changeStatus(UpdateStatus status, Exception ex){
        this._status=status;
        if(status==UpdateStatus.FINALIZED){
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
    
    protected UpdateStatus getStatus(){
        return this._status;
    }
    
    @Override
    public void loadRemoteManifest(boolean bloking){
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

    @Override
    public boolean checkForUpdates(){
        this.changeStatus(UpdateStatus.CHECKING);
        this.loadRemoteManifest(true);
        boolean needs_update=Function.compareTo(this.getCurrentVersion(),this.getLastVersion())<0;
        if(!needs_update){
            this.changeStatus(UpdateStatus.NO_UPDATE);
        }
        return needs_update;
    }

    @Override
    public void downloadUpdate(){
        if(!this.checkForUpdates()) return;
        this.changeStatus(UpdateStatus.DOWNLOADING);
        this.update_items.clear();
        AtomicInteger finished=new AtomicInteger(0);
        this.total_downloads=0;
        for(UpdateFile update_file:this.remote_manifest.files){
            String url=this.cfg.getUrl(update_file.path);
            UpdateItem update_item=new UpdateItem(
                    new Storage(this.getSrc()+"/"+update_file.path),
                    new Storage(this.backup.getSrc()+"/"+update_file.path),
                    new Storage(this.update.getSrc()+"/"+update_file.path)
            );
            if(!update_item.current.hashing().equals(update_file.hash)){
                try{
                    this.total_downloads++;
                    Download download=new Download(url,update_item.last.getSrc())
                            .overwriteIfExists(true)
                            .start();
                    download.onMetrics((metrics)->{
                        if(metrics.status==DownloadStatus.FINALIZED){
                            this.update_items.add(update_item);
                            int done=finished.incrementAndGet();
                            if(done==this.total_downloads){
                                this.changeStatus(UpdateStatus.DOWNLOAD_COMPLETED);
                                if(this.isApplyAfterDownload()){
                                    this.applyUpdate();
                                }
                            }
                        }
                    });
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }
        if(this.total_downloads==0){
            this.changeStatus(UpdateStatus.NO_UPDATE);
        }
    }

    @Override
    public void applyUpdate(){
        if(this.getStatus()!=UpdateStatus.DOWNLOAD_COMPLETED) return;
        if(this.update_items.size()<=0){
            this.changeStatus(UpdateStatus.NO_UPDATE);
            return;
        }
        this.changeStatus(UpdateStatus.APPLYING);
        for(UpdateItem update_item:this.update_items){
            try{
                update_item.apply();
            }catch(Exception ex){
                ex.printStackTrace();
                this.changeStatus(UpdateStatus.FAILED,ex);
            }
        }
        this.changeStatus(UpdateStatus.FINALIZED);
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
    public boolean verifyUpdate(){
        return false;
    }

    @Override
    public boolean backupCurrent(){
        return false;
    }

    @Override
    public void onProgress(){
        
    }

    @Override
    public void onError(Exception ex){
        
    }

    @Override
    public void onComplete(){
        
    }
    
}
