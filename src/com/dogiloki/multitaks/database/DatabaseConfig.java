package com.dogiloki.multitaks.database;

import com.dogiloki.multitaks.directory.annotations.Directory;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import com.google.gson.annotations.Expose;

/**
 *
 * @author dogi_
 */

@Directory(type=DirectoryType.ENV)
public class DatabaseConfig{
    
    @Expose
    public static String FOLDER_COLLECTIONS="data";
    @Expose
    public static String FOLDER_LOGS="logs";
    
}
