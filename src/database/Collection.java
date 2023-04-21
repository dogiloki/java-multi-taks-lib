package database;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import multitaks.directory.ModelDirectory;
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
            this.write(new Gson().toJson(record.getFields()));
        }
    }
    
    public List<Record> find(Record record){
        super.aim(this.src+"/"+this.name,DirectoryType.FOLDER);
        List<Record> records=new ArrayList<>();
        for(String file:this.listFiles()){
            ModelDirectory model=new ModelDirectory();
            model.aim(this.src+"/"+file,DirectoryType.FILE);
            String json=model.read();
            Map<String,Object> fields=new Gson().fromJson(json,new TypeToken<HashMap<String,Object>>(){}.getType());
            record.getFields((key,value)->{
                for(Map.Entry<String,Object> entry:fields.entrySet()){
                    if(entry.getKey().equals(key) && entry.getValue().equals(value)){
                        Record record_found=new Record(fields);
                        record_found.aim(this.src+"/"+file,DirectoryType.FILE);
                        records.add(record_found);
                    }
                }
            });
        }
        return records;
    }
    
    public List<Record> all(){
        super.aim(this.src+"/"+this.name,DirectoryType.FOLDER);
        List<Record> records=new ArrayList<>();
        for(String file:this.listFiles()){
            ModelDirectory model=new ModelDirectory();
            model.aim(this.src+"/"+file,DirectoryType.FILE);
            String json=model.read();
            Map<String,Object> fields=new Gson().fromJson(json,new TypeToken<HashMap<String,Object>>(){}.getType());
            Record record_found=new Record(fields);
            record_found.aim(this.src+"/"+file,DirectoryType.FILE);
            records.add(record_found);
        }
        return records;
    }
    
}
