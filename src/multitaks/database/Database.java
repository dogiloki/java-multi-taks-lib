package multitaks.database;

import multitaks.directory.annotations.Directory;
import multitaks.directory.ModelDirectory;
import multitaks.directory.enums.DirectoryType;

/**
 *
 * @author dogi_
 */

@Directory(type=DirectoryType.FOLDER)
public class Database extends ModelDirectory{
    
    public Database(String src){
        super.aim(this,src);
    }
    
    public Collection collection(String name){
        return new Collection(this.src+"/"+name);
    }
    
}
