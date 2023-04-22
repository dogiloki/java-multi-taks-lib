package multitaks.database;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import multitaks.annotations.directory.Directory;
import multitaks.directory.Storage;
import multitaks.enums.DirectoryType;

/**
 *
 * @author dogi_
 */

@Directory(type=DirectoryType.JSON)
public class Record extends Storage{
    
    public interface getField{
        public void execute(String key, Object value);
    }
    
    public String field_id="_id";
    private Map<String,Object> fields=new HashMap<>();
    
    public Record(){
        this.fields.put(this.field_id,ObjectId.generate());
    }
    
    public Record(Map<String,Object> fields){
        this.fields=fields;
    }
    
    public Map<String,Object> getFields(){
        return this.fields;
    }
    
    public void getFields(getField action){
        int index=0;
        for(Map.Entry<String,Object> entry:this.fields.entrySet()){
            action.execute(entry.getKey(),entry.getValue());
            index++;
        }
    }
    
    public String getJson(){
        return new Gson().toJson(this.getFields());
    }
    
    public String getId(){
        return (String)this.fields.get(this.field_id);
    }
    
    public Record setId(Object value){
        this.fields.put(this.field_id,value);
        return this;
    }
    
    public String get(String key){
        return (String)this.fields.get(key);
    }
    
    public Record set(String key, Object value){
        this.fields.put(key,value);
        return this;
    }
    
    public Record remove(String key){
        this.fields.remove(key);
        return this;
    }
    
}
