package com.dogiloki.multitaks.directory.compressed;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;

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
    
    public void updateFile(String path,Object content){
        try{
            String str=(String)content;
            InputStream input=new ByteArrayInputStream(str.getBytes());
            ZipParameters parameters=new ZipParameters();
            parameters.setFileNameInZip(path);
            this.addStream(input,parameters);
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
