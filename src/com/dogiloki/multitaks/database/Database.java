package com.dogiloki.multitaks.database;

import com.dogiloki.multitaks.directory.ConfigFile;
import com.dogiloki.multitaks.directory.annotations.Directory;
import com.dogiloki.multitaks.directory.ModelDirectory;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import com.dogiloki.multitaks.logger.Logger;

/**
 *
 * @author dogi_
 */

@Directory(type=DirectoryType.FOLDER)
public class Database extends ModelDirectory{
    
    public final Logger LOGGER;
    
    public Database(String src){
        ConfigFile.load(DatabaseConfig.class,src+"/config.cfg");
        this.LOGGER=new Logger(src+"/"+DatabaseConfig.FOLDER_LOGS);
        this.LOGGER.info("using database "+src);
        src+="/"+DatabaseConfig.FOLDER_COLLECTIONS;
        super.aim(src);
    }
    
    public Collection collection(String name, Class clazz){
        this.LOGGER.info("using collection "+name+" "+clazz);
        return new Collection(this.getSrc()+"/"+name,clazz).db(this);
    }
    
    public Collection collection(String name){
        this.LOGGER.info("using collection "+name);
        return new Collection(this.getSrc()+"/"+name,null).db(this);
    }
    
}
