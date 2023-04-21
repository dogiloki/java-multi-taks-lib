package database;

import java.util.ArrayList;
import java.util.List;
import multitaks.annotations.directory.Directory;
import multitaks.annotations.directory.Key;
import multitaks.directory.ModelDirectory;
import multitaks.enums.DirectoryType;
import multitaks.enums.FieldType;

/**
 *
 * @author dogi_
 */

@Directory(type=DirectoryType.JSON)
public class Schema extends ModelDirectory{
    
    @Key(value="collections",type=FieldType.LIST)
    public List<Collection> collections=new ArrayList<>();
    
    public Schema(){
        
    }
    
    public Schema(String src){
        src+="/schema.json";
        super.aim(this,src);
    }
    
    public void addCollection(Collection collection){
        this.collections.add(collection);
    }
    
    public Collection getCollection(String name){
        for(Collection collection:this.collections){
            if(collection.name.equals(name)){
                return collection;
            }
        }
        return null;
    }
    
}
