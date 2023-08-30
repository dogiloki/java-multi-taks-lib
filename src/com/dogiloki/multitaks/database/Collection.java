package com.dogiloki.multitaks.database;

import com.dogiloki.multitaks.database.record.Record;
import com.dogiloki.multitaks.database.record.RecordList;
import com.dogiloki.multitaks.database.filter.Filter;
import com.dogiloki.multitaks.dataformat.JSON;
import java.util.Scanner;
import com.dogiloki.multitaks.directory.Storage;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dogi_
 */

public class Collection extends Storage{
    
    private Database db;
    private Class clazz;
    
    public Collection(){
        
    }
    
    public Collection db(Database db){
        this.db=db;
        return this;
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
    
    public boolean insert(Record record){
        List<Record> records=new ArrayList<>();
        records.add(record);
        return this._insert(records);
    }
    
    public boolean insert(List<Record> records){
        return this._insert(records);
    }
    
    public boolean _insert(List<Record> records){
        String json="";
        this.aim(this.getSrc(),DirectoryType.FILE);
        int index=0;
        for(Record record:records){
            index++;
            record.generateId();
            json+=JSON.builder().toJson((Map)record.getFields())+"\n";
        }
        this.getDB().LOGGER.info("inserted "+index+" "+(index>1?"records":"record")+" in "+this.getName());
        this.append(json);
        this.flush();
        return true;
    }
    
    public boolean update(Filter filter, Record record){
        Record record_find=(Record)this.find(filter).first();
        if(record_find==null || record==null){
            this.getDB().LOGGER.info("no records matching ("+filter.toString()+") in "+this.getName());
            return false;
        }
        for(Map.Entry<String,Object> entry:record.getFields().entrySet()){
            if(entry.getKey().equals(record_find.fieldId())){
                continue;
            }
            record_find.set(entry.getKey(),entry.getValue());
        }
        boolean done=this.writeLine(record_find.toJson(),record_find.getLineNumber());
        if(done){
            this.getDB().LOGGER.info("updated ("+filter.toString()+") record ("+record.toJson()+") in "+this.getName());
        }else{
            this.getDB().LOGGER.info("could not update ("+filter.toString()+") record ("+record.toJson()+") in "+this.getName());
        }
        this.flush();
        return done;
    }
    
    public boolean delete(Filter filter){
        Record record_find=(Record)this.find(filter).first();
        if(record_find==null){
            this.getDB().LOGGER.info("no records matching ("+filter.toString()+") in "+this.getName());
            return false;
        }
        boolean done=this.writeLine("",record_find.getLineNumber());
        if(done){
            this.getDB().LOGGER.info("deleted ("+filter.toString()+") in "+this.getName());
        }else{
            this.getDB().LOGGER.info("could not deleted ("+filter.toString()+") in "+this.getName());
        }
        this.flush();
        return done;
    }
    
    public RecordList find(Filter filter){
        Scanner lines=this.readIterator();
        this.getDB().LOGGER.info("find records ("+filter.toString()+") in "+this.getName());
        return new RecordList(lines,filter,this.clazz);
    }
    
    public RecordList all(){
        Scanner lines=this.readIterator();
        this.getDB().LOGGER.info("all records in "+this.getName());
        return new RecordList(lines,this.clazz);
    }
    
    public Database getDB(){
        return this.db;
    }
    
    @Override
    public boolean delete(){
        boolean done=super.delete();
        if(done){
            this.getDB().LOGGER.info("deleted "+this.getName()+" collection");
        }else{
            this.getDB().LOGGER.info("could not deleted "+this.getName()+" collection");
        }
        return done;
    }
    
}
