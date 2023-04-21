package database;

import multitaks.annotations.directory.Directory;
import multitaks.annotations.directory.Key;
import multitaks.enums.DirectoryType;

/**
 *
 * @author dogi_
 */

@Directory(type=DirectoryType.JSON)
public class Field{
    
    @Key(value="name")
    public String name;
    
    public Field(){
        
    }
    
    public Field(String name){
        this.name=name;
    }
    
}
