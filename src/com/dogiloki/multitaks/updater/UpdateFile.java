package com.dogiloki.multitaks.updater;

import com.dogiloki.multitaks.directory.Storage;
import com.dogiloki.multitaks.directory.annotations.Directory;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import com.google.gson.annotations.Expose;
import java.nio.file.Paths;

/**
 *
 * @author _dogi
 */

@Directory(type=DirectoryType.JSON)
public class UpdateFile{
    
    @Expose
    public String path;
    @Expose
    public String hash;
    @Expose
    public long size;
    
    public final Storage file;
    
    public UpdateFile(String path, String base_path){
        this.file=new Storage(path);
        this.path=Storage.formatPath(Paths.get(base_path).relativize(Paths.get(path)).toString());
        this.hash=this.file.hashing();
        this.size=this.file.getSize();
    }
    
}
