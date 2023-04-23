package multitaks.database;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.Map;
import multitaks.directory.DirectoryList;
import multitaks.directory.ModelDirectory;
import multitaks.directory.Storage;
import multitaks.enums.DirectoryType;

/**
 *
 * @author dogi_
 */

public class Collection<T> extends Storage{
    
    public Collection(){
        
    }
    
    public Collection(String src){
        this.src=src;
        Storage.exists(src,DirectoryType.FOLDER,true);
    }
    
    public void aim(String src){
        this.src=src;
        Storage.exists(src,DirectoryType.FOLDER,true);
    }
    
    public boolean insert(Record... records){
        for(Record record:records){
            super.aim(this.src+"/"+record.getId()+".json",DirectoryType.FILE);
            this.write(new Gson().toJson(record.getFields()));
        }
        return true;
    }
    
    public boolean update(Record record_find, Record record_operations){
        record_find=this.find(record_find);
        if(record_find==null || record_operations==null){
            return false;
        }
        for(Map.Entry<String,Object> entry:record_operations.getFields().entrySet()){
            if(entry.getKey().equals(record_find.field_id)){
                continue;
            }
            record_find.set(entry.getKey(),entry.getValue());
        }
        return record_find.write(new Gson().toJson(record_find.getFields()));
    }
    
    public Record find(Record record){
        super.aim(this.src,DirectoryType.FOLDER);
        Record record_found=null;
        DirectoryList files=this.listFiles();
        out_files:while(files.hasNext()){
            String file=files.next().getFileName().toString();
            ModelDirectory model=new ModelDirectory();
            model.aim(this.src+"/"+file,DirectoryType.FILE);
            String json=model.read();
            Map<String,Object> fields=new Gson().fromJson(json,new TypeToken<HashMap<String,Object>>(){}.getType());
            int count_found=0;
            for(Map.Entry<String,Object> entry_find:record.getFields().entrySet()){
                for(Map.Entry<String,Object> entry:fields.entrySet()){
                    if(entry_find.getKey().equals(entry.getKey()) && entry_find.getValue().equals(entry.getValue())){
                        count_found++;
                    }
                }
                if(count_found!=record.getFields().size()){
                    continue;
                }
                record_found=new Record(fields);
                record_found.aim(this.src+"/"+file,DirectoryType.FILE);
                break out_files;
            }
        }
        return record_found;
    }
    
    public RecordList all(){
        super.aim(this.src,DirectoryType.FOLDER);
        DirectoryList files=this.listFiles();
        return new RecordList(files.iterator());
    }
    
}
