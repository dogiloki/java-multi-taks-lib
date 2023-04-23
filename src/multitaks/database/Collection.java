package multitaks.database;

import com.google.gson.Gson;
import java.util.Map;
import java.util.Scanner;
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
        Storage.exists(src,DirectoryType.FILE,true);
        this.aim(this.getSrc(),DirectoryType.FILE);
    }
    
    public void aim(String src){
        this.src=src;
        Storage.exists(src,DirectoryType.FILE,true);
        this.aim(this.getSrc(),DirectoryType.FILE);
    }
    
    public boolean insert(Record... records){
        for(Record record:records){
            this.aim(this.getSrc(),DirectoryType.FILE);
            record.generateId();
            this.append(new Gson().toJson(record.getFields())+"\n");
        }
        return true;
    }
    
    public boolean update(Record record_find, Record record_operations){
        record_find=this.find(record_find).first();
        if(record_find==null || record_operations==null){
            return false;
        }
        for(Map.Entry<String,Object> entry:record_operations.getFields().entrySet()){
            if(entry.getKey().equals(record_find.field_id)){
                continue;
            }
            record_find.set(entry.getKey(),entry.getValue());
        }
        return this.writeLine(new Gson().toJson(record_find.getFields()),record_find.getLineNumber(),true);
    }
    
    public boolean delete(Record record_find){
        record_find=this.find(record_find).first();
        if(record_find==null){
            return false;
        }
        return this.writeLine("",record_find.getLineNumber(),false);
    }
    
    public RecordList find(Record record){
        Scanner lines=this.readIterator();
        return new RecordList(lines,record);
    }
    
    public RecordList all(){
        Scanner lines=this.readIterator();
        return new RecordList(lines);
    }
    
}
