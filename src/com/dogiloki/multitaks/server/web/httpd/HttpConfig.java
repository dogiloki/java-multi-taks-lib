package com.dogiloki.multitaks.server.web.httpd;

import com.dogiloki.multitaks.directory.ModelDirectory;
import com.dogiloki.multitaks.directory.annotations.Directory;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import com.google.gson.annotations.Expose;

/**
 *
 * @author _dogi
 */

@Directory(type=DirectoryType.JSON)
public class HttpConfig extends ModelDirectory{
    
    @Expose
    public String server_root="web";
    
    @Expose
    public int listen_port=8000;
    
    public HttpConfig(){
        
    }
    
}
