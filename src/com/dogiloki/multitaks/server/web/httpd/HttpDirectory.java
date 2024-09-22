package com.dogiloki.multitaks.server.web.httpd;

import com.dogiloki.multitaks.directory.annotations.Directory;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author _dogi
 */

@Directory(type=DirectoryType.JSON)
public class HttpDirectory{
    
    public String path_root="";
    public String path_public="";
    
    public HttpDirectory(){
        
    }
    
}
