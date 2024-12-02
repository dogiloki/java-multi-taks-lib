package com.dogiloki.multitaks.directory.compressed;

import java.io.File;
import net.lingala.zip4j.ZipFile;

/**
 *
 * @author _dogi
 */

public class CompressedStorage extends ZipFile{
    
    private String path;
    
    public CompressedStorage(String path){
        super(path);
        
    }
    
    public void addFolder(String path){
        try{
            this.addFolder(new File(path));
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public CompressedList listDirectory(){
        return new CompressedList(this);
    }
    
    public String getPath(){
        return this.path;
    }
    
}
