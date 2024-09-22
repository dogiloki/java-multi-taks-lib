package com.dogiloki.multitaks.server.web.httpd;

import com.dogiloki.multitaks.directory.annotations.Directory;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import com.google.gson.annotations.Expose;

/**
 *
 * @author _dogi
 */

@Directory(type=DirectoryType.JSON)
public class HttpDirectory{
    
    @Expose
    public String alias="";
    
    @Expose
    public String path_root="";
    
    @Expose
    public String path_public="";
    
    public HttpDirectory(){
        
    }
    
}
