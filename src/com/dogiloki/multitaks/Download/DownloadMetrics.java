package com.dogiloki.multitaks.Download;

import com.dogiloki.multitaks.directory.SizeFormat;
import java.io.File;

/**
 *
 * @author dogi_
 */

public class DownloadMetrics{
    
    public static String TEXT_CONNECTING="{0}";
    public static String TEXT_PROGRESS="{0} / {1}";
    
    public File file;
    public String status;
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
    
}
