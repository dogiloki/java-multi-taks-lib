package database;

import com.google.gson.Gson;
import multitaks.directory.Storage;
import multitaks.enums.DirectoryType;

/**
 *
 * @author dogi_
 */

public class Collection extends Storage{
    
    private String name;
    
    public Collection(){
        
    }
    
    public Collection(String name, String src){
        this.name=name;
        this.src=src;
        Storage.exists(src+"/"+name,DirectoryType.FOLDER,true);
    }
    
    public void insert(Record... records){
        for(Record record:records){
            super.aim(this.src+"/"+this.name+"/"+record.getId()+".json",DirectoryType.FILE);
            this.write(new Gson().toJson(record));
        }
    }
    
}
