package com.dogiloki.multitaks.database.record;

import com.dogiloki.multitaks.database.filter.Filter;
import com.google.gson.Gson;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author dogi_
 */

public class RecordList implements Iterator<Record>{
    
    private final Scanner iterator;
    private Record current;
    private Filter filter;
    private long line_number=0;
    
    public RecordList(Scanner iterator, Filter filter){
        this.iterator=iterator;
        this.filter=filter;
    }
    
    public RecordList(Scanner iterator){
        this.iterator=iterator;
    }
    
    public Scanner iterator(){
        return this.iterator;
    }
    
    @Override
    public boolean hasNext(){
        if(!this.iterator.hasNextLine()){
            this.iterator.close();
            return false;
        }
        return true;
    }
    
    @Override
    public Record next(){
        if(!this.hasNext()){
            return null;
        }
        this.line_number++;
        this.current=null;
        String json=this.iterator.nextLine();
        RecordField fields=new Gson().fromJson(json,RecordField.class);
        
        Record record=new Record(fields,this.line_number);
        if(this.filter==null){
            this.current=record;
        }else{
            this.current=this.filter.apply(record)?record:null;
        }
        
        return this.current==null?this.next():this.current;
    }
    
    public Record first(){
        this.current=null;
        Record record=this.next();
        if(record==null){
            return null;
        }
        String json=record.toJson();
        RecordField fields=new Gson().fromJson(json,RecordField.class);
        Record record_found=new Record(fields,record.getLineNumber());
        this.current=record_found;
        return this.current;
    }
    
    public Scanner getIterator(){
        return this.iterator;
    }
    
}
