package com.dogiloki.multitaks.database.record;

import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.database.filter.Filter;
import com.google.gson.Gson;
import java.util.Scanner;

/**
 *
 * @author dogi_
 */

public class RecordList{
    
    private final Scanner iterator;
    private Record current;
    private Filter filter;
    private long line_number=0;
    private int count=0;
    private Integer limit_index=null;
    private Integer limit_end=null;
    
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
    
    private boolean hasNext(){
        if(!this.iterator.hasNextLine()){
            this.iterator.close();
            return false;
        }
        return true;
    }
    
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
        if(this.current==null){
            return this.next();
        }
        this.count++;
        if(this.current!=null && this.hasLimit()){
            if(!this.withinLimit()){
                return this.next();
            }
        }
        return this.current;
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
    
    public RecordList limit(int index, int end){
        this.limit_index=index;
        this.limit_end=end;
        return this;
    }
    
    public RecordList limit(int end){
        this.limit_index=1;
        this.limit_end=end;
        return this;
    }
    
    public boolean hasLimit(){
        return this.limit_index!=null && this.limit_end!=null;
    }
    
    public boolean withinLimit(){
        return Function.withinRange(this.count,this.limit_index,this.limit_end);
    }
    
    public Scanner getIterator(){
        return this.iterator;
    }
    
}
