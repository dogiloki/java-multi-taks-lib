package com.dogiloki.multitaks;

import com.dogiloki.multitaks.directory.ModelDirectory;
import com.dogiloki.multitaks.directory.annotations.Directory;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import com.google.gson.annotations.Expose;

/**
 *
 * @author dogi_
 */

@Directory(type=DirectoryType.ENV)
public class Configuration extends ModelDirectory{
    
    @Expose
    public static String FOLDER;
    @Expose
    public String hola;
    
}
