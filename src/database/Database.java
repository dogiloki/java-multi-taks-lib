package database;

import java.util.ArrayList;
import java.util.List;
import multitaks.annotations.directory.Directory;
import multitaks.directory.ModelDirectory;
import multitaks.enums.DirectoryType;

/**
 *
 * @author dogi_
 */

@Directory(type=DirectoryType.FOLDER)
public class ModelDB extends ModelDirectory{
    
    private List<Collection> collections=new ArrayList<>();
    
    public ModelDB(String src){
        super.aim(this,src);
    }
    
    public Collection collection(String name){
        Collection collection=new Collection(name,this.src);
        this.collections.add(collection);
        return collection;
    }
    
}
