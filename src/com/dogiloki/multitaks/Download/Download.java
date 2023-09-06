package com.dogiloki.multitaks.Download;

import com.dogiloki.multitaks.callbacks.OnCallbackNotReturn;
import com.dogiloki.multitaks.directory.FileBlock;
import com.dogiloki.multitaks.directory.ModelDirectory;
import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;

/**
 *
 * @author dogi_
 */

public class Download extends ModelDirectory implements Runnable{
    
    private String url;
    private FileBlock file_block;
    private BufferedInputStream in;
    private Thread thread=new Thread(this);
    private boolean pause=false;
    private boolean canceled=false;
    private boolean delete_if_canceled=false;
    private DownloadMetrics metrics=new DownloadMetrics();
    private OnCallbackNotReturn<DownloadMetrics> on_connecting=(item)->{};
    private OnCallbackNotReturn<DownloadMetrics> on_progress=(item)->{};
    
    public Download(String url, String path){
        super(path);
        this.url=url;
        this.file_block=this.fileBlock(1024);
        this.metrics.file=this.getFile();
    }
    
    public void onConnecting(OnCallbackNotReturn<DownloadMetrics> action){
        this.on_connecting=action;
    }
    
    public void onProgress(OnCallbackNotReturn<DownloadMetrics> action){
        this.on_progress=action;
    }
    
    public boolean deleteIfCanceled(){
        return this.delete_if_canceled;
    }
    
    public Download start(){
        this.thread.start();
        return this;
    }
    
    public void pause(){
        this.metrics.status="[ Paused ] "+this.metrics.status;
        this.pause=true;
    }
    
    public void resumen(){
        this.pause=false;
    }
    
    public void canceled(){
        this.metrics.status="[ Cancelled ] "+this.metrics.status;
        this.canceled=false;
    }

    @Override
    public void run(){
        try{
            
            this.metrics.status=MessageFormat.format(DownloadMetrics.TEXT_CONNECTING,this.url);
            this.on_connecting.run(this.metrics);
            
            URLConnection connection=new URL(this.url).openConnection();
            connection.connect();
            
            this.in=new BufferedInputStream(connection.getInputStream());
            this.metrics.totalSize(connection.getContentLength());
            int b=0;
            
            while(b!=-1){
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
                b=this.in.read();
                this.file_block.write(b);
                this.metrics.status=MessageFormat.format(DownloadMetrics.TEXT_PROGRESS,this.metrics.totalSize().toString(),this.metrics.currentSize());
                this.on_progress.run(this.metrics);
            }
            
            this.in.close();
            this.file_block.close();
            this.close();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
