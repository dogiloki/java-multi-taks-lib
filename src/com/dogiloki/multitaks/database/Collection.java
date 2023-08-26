package com.dogiloki.multitaks.database;

import com.dogiloki.multitaks.database.record.Record;
import com.dogiloki.multitaks.database.record.RecordList;
import com.dogiloki.multitaks.database.filter.Filter;
import com.google.gson.Gson;
import java.util.Scanner;
import com.dogiloki.multitaks.directory.Storage;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import java.util.Map;

/**
 *
 * @author dogi_
 */

public class Collection extends Storage{
    
    public Collection(){
        
    }
    
    public Collection(String src){
        this.setSrc(src);
        Storage.exists(src,DirectoryType.FILE,true);
        this.aim(this.getSrc(),DirectoryType.FILE);
    }
    
    public void aim(String src){
        this.setSrc(src);
        Storage.exists(src,DirectoryType.FILE,true);
        this.aim(this.getSrc(),DirectoryType.FILE);
    }
    
    public boolean insert(Record... records){
        for(Record record:records){
            this.aim(this.getSrc(),DirectoryType.FILE);
            record.generateId();
            this.append(new Gson().toJson((Map)record.getFields())+"\n");
        }
        return true;
    }
    
    public boolean update(Filter filter, Record record){
        Record record_find=this.find(filter).first();
        if(record_find==null || record==null){
            return false;
        }
        for(Map.Entry<String,Object> entry:record.getFields().entrySet()){
            if(entry.getKey().equals(record_find.fieldId())){
                continue;
            }
            record_find.set(entry.getKey(),entry.getValue());
        }
        return this.writeLine(new Gson().toJson(record_find.getFields()),record_find.getLineNumber());
    }
    
    public boolean delete(Filter filter){
        Record record_find=this.find(filter).first();
        if(record_find==null){
            return false;
        }
        return this.writeLine("",record_find.getLineNumber());
    }
    
    public RecordList find(Filter filter){
        Scanner lines=this.readIterator();
        return new RecordList(lines,filter);
    }
    
    public RecordList all(){
        Scanner lines=this.readIterator();
        return new RecordList(lines);
    }
    
}
