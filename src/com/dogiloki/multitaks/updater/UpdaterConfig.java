package com.dogiloki.multitaks.updater;

import com.dogiloki.multitaks.directory.ModelDirectory;
import com.dogiloki.multitaks.directory.annotations.Directory;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import com.google.gson.annotations.Expose;

/**
 *
 * @author _dogi
 */

@Directory(type=DirectoryType.JSON,fromJar=true)
public class UpdaterConfig extends ModelDirectory{
    
    public static String MANIFEST_FILE_NAME="manifest.json";
    
    @Expose
    public String project;
    @Expose
    public String version;
    @Expose
    public String url;
    
    public String manifest_file_name;
    
    public UpdaterConfig(){
        this.manifest_file_name=UpdaterConfig.MANIFEST_FILE_NAME;
    }
    
    public UpdaterConfig(Class<?> clazz){
        super.referenceClass(clazz);
        super.aim("updater-config.json");
    }
    
    public String getUrlManifest(){
        return this.url+"/"+this.project+"/"+this.manifest_file_name;
    }
    
    public String getUrl(){
        return this.url+"/"+this.project;
    }
    
    public String getUrl(String path){
        return this.url+"/"+this.project+"/"+path;
    }
    
}
