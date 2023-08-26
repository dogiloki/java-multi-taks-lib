package com.dogiloki.multitaks.database;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author dogi_
 */

public class RecordList{
    
    private final Scanner iterator;
    private Record current;
    private Record record_find;
    private long line_number=0;
    
    public RecordList(Scanner iterator, Record record){
        this.iterator=iterator;
        this.record_find=record;
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
        this.line_number++;
        if(!this.hasNext()){
            return null;
        }
        this.current=null;
        String json=this.iterator.nextLine();
        Map<String,Object> fields=new Gson().fromJson(json,new TypeToken<HashMap<String,Object>>(){}.getType());
        
        Record record_found=new Record(fields,this.line_number);
        if(this.record_find==null || (this.record_find.getOperations().isEmpty() && this.record_find.getFields().isEmpty())){
            this.current=record_found;
        }else{
            int count_found=0;
            // Convertir los fields en operaciones de igual
            for(Map.Entry<String,Object> entry_find:this.record_find.getFields().entrySet()){
                this.record_find.setOperation(new Operation(entry_find.getKey(),entry_find.getValue()));
            }
            // Validad si las operaciones se cumplen
            for(Operation operation:this.record_find.getOperations()){
                Object value=fields.get(operation.field);
                switch(operation.operator){
                    case "=":{
                        if(Objects.equals(value,operation.value)){
                            count_found++;
                        }
                        break;
                    }
                    case "!=":{
                        if(!Objects.equals(value,operation.value)){
                            count_found++;
                        }
                        break;
                    }
                }
            }
            if(count_found==this.record_find.getOperations().size()){
                this.current=record_found;
            }
        }
        return this.current==null?this.next():this.current;
    }
    
    public Record first(){
        this.current=null;
        Record record=this.next();
        if(record==null){
            return null;
        }
        String json=record.getJson();
        Map<String,Object> fields=new Gson().fromJson(json,new TypeToken<HashMap<String,Object>>(){}.getType());
        Record record_found=new Record(fields,record.getLineNumber());
        this.current=record_found;
        return this.current;
    }
    
    public Scanner getIterator(){
        return this.iterator;
    }
    
}
