package multitaks.database;

import multitaks.annotations.directory.Directory;
import multitaks.directory.ModelDirectory;
import multitaks.enums.DirectoryType;

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
