package com.dogiloki.multitaks.download;

import com.dogiloki.multitaks.directory.SizeFormat;
import com.dogiloki.multitaks.download.enums.DownloadStatus;
import java.io.File;

/**
 *
 * @author dogi_
 */

public class DownloadMetrics{
    
    public static String TEXT_CONNECTING="{0}";
    public static String TEXT_DOWNLOADING="{0} / {1} - {2}%";
    
    public File file;
    public String message;
    public DownloadStatus status;
    private SizeFormat total_size;
    private SizeFormat current_size;
    
    public DownloadMetrics(){
        this.total_size=new SizeFormat();
        this.current_size=new SizeFormat();
    }
    
    public DownloadMetrics totalSize(long size){
        this.totalSize().size(size);
        return this;
    }
    
    public SizeFormat totalSize(){
        return this.total_size;
    }
    
    public SizeFormat currentSize(){
        return this.current_size.size(file.length());
    }
    
    public int percent(){
        long total_size=this.totalSize().size();
        return (int)(total_size<=0?0:(this.currentSize().size()*100)/total_size);
    }
    
}
