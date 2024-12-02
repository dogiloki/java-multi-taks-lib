package com.dogiloki.multitaks.directory.compressed;

import com.dogiloki.multitaks.datastructure.AbstractIterator;
import com.dogiloki.multitaks.directory.FileBuffered;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipOutputStream;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.FileHeader;

/**
 *
 * @author _dogi
 */

public class CompressedList extends AbstractIterator<FileHeader>{
    
    private ZipFile zip;
    
    public CompressedList(ZipFile zip){
        this.zip=zip;
        try{
            this.items(this.zip.getFileHeaders());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public FileHeader get(String file_name){
        try{
            return this.zip.getFileHeader(file_name);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public FileBuffered getFile(String path){
        try{
            return new FileBuffered(this.zip.getInputStream(this.get(path)));
        }catch(Exception ex){
           ex.printStackTrace();
        }
        return null;
    }
    
}
