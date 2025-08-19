package com.dogiloki.multitaks.updater;

import com.dogiloki.multitaks.directory.ModelDirectory;
import com.dogiloki.multitaks.directory.Storage;
import com.dogiloki.multitaks.directory.annotations.Directory;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import com.google.gson.annotations.Expose;

/**
 *
 * @author _dogi
 */

@Directory(type=DirectoryType.JSON)
public final class Manifest extends ModelDirectory{
    
    @Expose
    public String proyect;
    @Expose
    public String version;
    @Expose
    public String url;
    @Expose
    public UpdateFiles files;
    
    public Manifest(String path){
        super.aim(path+"/"+UpdaterConfig.MANIFEST_FILE_NAME);
    }
    
    public Manifest create(){
        this.proyect=new Storage(this.getFolder()).getName();
        this.files=new UpdateFiles(this.getFolder());
        return this;
    }
    
    public Manifest proyect(String value){
        this.proyect=value;
        return this;
    }
    
    public Manifest version(String value){
        this.version=value;
        return this;
    }
    
    public Manifest url(String value){
        this.url=value;
        return this;
    }
    
}
