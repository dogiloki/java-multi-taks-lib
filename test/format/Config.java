package format;

import com.dogiloki.multitaks.directory.ModelDirectory;
import com.dogiloki.multitaks.directory.annotations.Directory;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import com.google.gson.annotations.Expose;

/**
 *
 * @author dogi_
 */

@Directory(type=DirectoryType.JSON,src="config.json")
public class Config extends ModelDirectory{
    
    @Expose
    public Folder folder;
    @Expose
    public double flotante;
    @Expose
    public int peso;
    
    public Config(){
    
    }
    
}
