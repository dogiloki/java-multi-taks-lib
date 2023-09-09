package com.dogiloki.multitaks.download;

import com.dogiloki.multitaks.callbacks.OnCallbackNotReturn;
import com.dogiloki.multitaks.directory.FileBlock;
import com.dogiloki.multitaks.directory.ModelDirectory;
import com.dogiloki.multitaks.download.enums.DownloadStatus;
import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;

/**
 *
 * @author dogi_
 */

public class Download extends ModelDirectory implements Runnable{
    
    private final String url;
    private final FileBlock file_block;
    private BufferedInputStream in;
    private final Thread thread=new Thread(this);
    private boolean pause=false;
    private boolean canceled=false;
    private boolean delete_if_canceled=false;
    private final DownloadMetrics metrics=new DownloadMetrics();
    private OnCallbackNotReturn<DownloadMetrics> on_metrics=(item)->{};
    
    public Download(String url, String path){
        super(path);
        this.url=url;
        this.file_block=this.fileBlock(1024).append(true);
        this.metrics.file=this.getFile();
    }
    
    public void onMetrics(OnCallbackNotReturn<DownloadMetrics> action){
        this.on_metrics=action;
    }
    
    public boolean deleteIfCanceled(){
        return this.delete_if_canceled;
    }
    
    public boolean deleteIfCanceled(boolean b){
        return this.delete_if_canceled=b;
    }
    
    public Download start(){
        this.thread.start();
        return this;
    }
    
    public void pause(){
        this.metrics.status=DownloadStatus.PAUSED;
        this.metrics.message="[ Paused ] "+this.metrics.message;
        this.pause=true;
    }
    
    public void resumen(){
        this.pause=false;
    }
    
    public void canceled(){
        this.metrics.status=DownloadStatus.CANCELLED;
        this.metrics.message="[ Cancelled ] "+this.metrics.message;
        this.canceled=false;
    }

    @Override
    public void run(){
        try{
            
            this.metrics.message=MessageFormat.format(DownloadMetrics.TEXT_CONNECTING,this.url);
            this.metrics.status=DownloadStatus.CONNECTING;
            this.on_metrics.run(this.metrics);
            
            URLConnection connection=new URL(this.url).openConnection();
            connection.connect();
            
            this.in=new BufferedInputStream(connection.getInputStream());
            this.metrics.totalSize(connection.getContentLength());
            int b=0;
            byte[] buffer=new byte[this.file_block.getBlockSize()];
            this.in.skip(this.file_block.readAll().length);
            
            while(true){
                if(this.pause){
                    continue;
                }
                if(this.canceled){
                    this.in.close();
                    this.file_block.close();
                    this.close();

                    if(this.deleteIfCanceled()){
                        this.delete();
                    }
                    break;
                }
                this.metrics.status=DownloadStatus.DOWNLOADING;
                b=this.in.read(buffer);
                if(b==-1){
                    break;
                }
                this.file_block.write(buffer,0,b);
                this.metrics.message=MessageFormat.format(DownloadMetrics.TEXT_DOWNLOADING,this.metrics.totalSize().toString(),this.metrics.currentSize(),this.metrics.percent());
                this.on_metrics.run(this.metrics);
            }
            
            this.in.close();
            this.file_block.close();
            this.close();
            this.metrics.status=DownloadStatus.FINALIZED;
            this.metrics.message="[ Finalized ] "+this.metrics.message;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
