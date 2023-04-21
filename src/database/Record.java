package database;

import java.util.HashMap;
import java.util.Map;
import multitaks.annotations.directory.Directory;
import multitaks.enums.DirectoryType;

/**
 *
 * @author dogi_
 */

@Directory(type=DirectoryType.JSON)
public class Record{
    
    public String field_id="_id";
    private Map<String,Object> fields=new HashMap<>();
    
    public Record(String... fields){
        this.fields.put(this.field_id,ObjectId.generate());
        for(String field:fields){
            this.fields.put(field,null);
        }
    }
    
    public Record append(Object... values){
        int index=0;
        for(Map.Entry<String,Object> entry:this.fields.entrySet()){
            if(entry.getKey().equals("_id")){
                continue;
            }
            this.fields.put(entry.getKey(),values[index]);
            index++;
        }
        return this;
    }
    
    public String getId(){
        return (String)this.fields.get(this.field_id);
    }
    
    public String get(String key){
        return (String)this.fields.get(key);
    }
    
    public void set(String key, Object value){
        this.fields.put(key,value);
    }
    
}
