package multitaks.database;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import multitaks.directory.ModelDirectory;
import multitaks.enums.DirectoryType;

/**
 *
 * @author dogi_
 */

public class RecordList{
    
    private final Iterator<Path> iterator;
    private Record current;
    
    public RecordList(Iterator<Path> iterator){
        this.iterator=iterator;
    }
    
    public Iterator<Path> iterator(){
        return this.iterator;
    }
    
    public boolean hasNext(){
        if(!this.iterator.hasNext()){
            return false;
        }
        return true;
    }
    
    public Record next(){
        String src=this.iterator.next().toString();
        ModelDirectory model=new ModelDirectory();
        model.aim(src,DirectoryType.FILE);
        String json=model.read();
        Map<String,Object> fields=new Gson().fromJson(json,new TypeToken<HashMap<String,Object>>(){}.getType());
        Record record_found=new Record(fields);
        record_found.aim(src,DirectoryType.FILE);
        this.current=record_found;
        //this.current=new Gson().fromJson(json,this.clazz);
        return this.current;
    }
    
}
