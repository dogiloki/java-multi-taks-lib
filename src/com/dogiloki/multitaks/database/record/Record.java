package com.dogiloki.multitaks.database.record;

import com.dogiloki.multitaks.ObjectId;
import com.dogiloki.multitaks.database.filter.Filter;
import com.google.gson.Gson;
import java.util.Map;

/**
 *
 * @author dogi_
 */

public class Record{
    
    public interface getField{
        public void execute(String key, Object value);
    }
    
    public String field_id="_id";
    private RecordField fields;
    private long line_number=0;
    
    public Record(){
        
    }
    
    public Record(long number){
        this.setLineNumber(number);
    }
    
    public Record(RecordField fields){
        this.fields=fields;
    }
    
    public Record(RecordField fields, long number){
        this.fields=fields;
        this.setLineNumber(number);
    }
    
    public void setLineNumber(long number){
        this.line_number=number;
    }
    
    public long getLineNumber(){
        return this.line_number;
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
    
    public Record setFields(RecordField fields){
        this.fields=fields;
        return this;
    }
    
    public String toJson(){
        return new Gson().toJson(this.getFields());
    }
    
    public String getId(){
        return (String)this.fields.get(this.field_id);
    }
    
    public Record setId(Object value){
        this.fields.put(this.field_id,value);
        return this;
    }
    
    public Record generateId(){
        this.fields.put(this.field_id,ObjectId.generate());
        return this;
    }
    
    public Object get(String key){
        return this.fields.get(key);
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
