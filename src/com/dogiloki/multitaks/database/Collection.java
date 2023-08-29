package com.dogiloki.multitaks.database;

import com.dogiloki.multitaks.database.record.Record;
import com.dogiloki.multitaks.database.record.RecordList;
import com.dogiloki.multitaks.database.filter.Filter;
import com.dogiloki.multitaks.dataformat.JSON;
import java.util.Scanner;
import com.dogiloki.multitaks.directory.Storage;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import java.util.Map;

/**
 *
 * @author dogi_
 */

public class Collection extends Storage{
    
    private Class clazz;
    
    public Collection(){
        
    }
    
    public Collection(String src, Class clazz){
        this.clazz=clazz;
        this.setSrc(src);
        new Storage(src,DirectoryType.FILE).exists(true);
        this.aim(this.getSrc(),DirectoryType.FILE);
    }
    
    public void aim(String src){
        this.setSrc(src);
        new Storage(src,DirectoryType.FILE).exists(true);
        this.aim(this.getSrc(),DirectoryType.FILE);
    }
    
    public boolean insert(Record... records){
        for(Record record:records){
            this.aim(this.getSrc(),DirectoryType.FILE);
            record.generateId();
            this.append(JSON.builder().toJson((Map)record.getFields())+"\n");
        }
        return true;
    }
    
    public boolean update(Filter filter, Record record){
        Record record_find=(Record)this.find(filter).first();
        if(record_find==null || record==null){
            return false;
        }
        for(Map.Entry<String,Object> entry:record.getFields().entrySet()){
            if(entry.getKey().equals(record_find.fieldId())){
                continue;
            }
            record_find.set(entry.getKey(),entry.getValue());
        }
        return this.writeLine(record_find.toJson(),record_find.getLineNumber());
    }
    
    public boolean delete(Filter filter){
        Record record_find=(Record)this.find(filter).first();
        if(record_find==null){
            return false;
        }
        return this.writeLine("",record_find.getLineNumber());
    }
    
    public RecordList find(Filter filter){
        Scanner lines=this.readIterator();
        return new RecordList(lines,filter,this.clazz);
    }
    
    public RecordList all(){
        Scanner lines=this.readIterator();
        return new RecordList(lines,this.clazz);
    }
    
}
