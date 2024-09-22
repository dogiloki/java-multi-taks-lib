package com.dogiloki.multitaks.server.web.httpd;

import com.dogiloki.multitaks.directory.ModelDirectory;
import com.dogiloki.multitaks.directory.annotations.Directory;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author _dogi
 */

@Directory(type=DirectoryType.JSON,src="server/httpd.env")
public class HttpConfig extends ModelDirectory{
    
    @Expose
    public String server_root="web";
    
    @Expose
    public int listen_port=8000;
    
    public List<Directory> directories=new ArrayList<>();
    
    public HttpConfig(){
        
    }
    
}
