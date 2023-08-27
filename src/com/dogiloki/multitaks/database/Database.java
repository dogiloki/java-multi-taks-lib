package com.dogiloki.multitaks.database;

import com.dogiloki.multitaks.directory.annotations.Directory;
import com.dogiloki.multitaks.directory.ModelDirectory;
import com.dogiloki.multitaks.directory.enums.DirectoryType;

/**
 *
 * @author dogi_
 */

@Directory(type=DirectoryType.FOLDER)
public class Database extends ModelDirectory{
    
    public Database(String src){
        super.aim(this,src);
    }
    
    public Collection collection(String name, Class clazz){
        return new Collection(this.getSrc()+"/"+name,clazz);
    }
    
    public Collection collection(String name){
        return new Collection(this.getSrc()+"/"+name,null);
    }
    
}
