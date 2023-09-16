package com.dogiloki.multitaks.database.record;

import com.dogiloki.multitaks.ObjectId;
import com.dogiloki.multitaks.database.filter.Filter;
import com.dogiloki.multitaks.dataformat.JSON;
import java.util.Map;

/**
 *
 * @author dogi_
 */

public class Record{
    
    public interface CallRecordField{
        public void execute(String key, Object value);
    }
    
    private String field_id="_id";
    private RecordField fields=new RecordField();
    private long line_number=0;
    
    public Record(){
        
    }
    
    
    public Record(long line_number){
        this.setLineNumber(line_number);
    }
    
    public Record(RecordField fields){
        this.fields=fields;
    }
    
    public Record(RecordField fields, long number){
        this.fields=fields;
        this.setLineNumber(number);
    }
    
    public boolean filter(Filter filter){
        filter.record(this);
        return filter.logic();
    }
    
    public RecordField getFields(CallRecordField action){
        int index=0;
        for(Map.Entry<String,Object> entry:this.fields.entrySet()){
            action.execute(entry.getKey(),entry.getValue());
            index++;
        }
        return this.fields;
    }
    
    public String toJson(){
        return JSON.builder().toJson(this.getFields());
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
    
    // Setters
    public Record setLineNumber(long line_number){
        this.line_number=line_number;
        return this;
    }
    public Record setFields(RecordField fields){
        this.fields=fields;
        return this;
    }
    public Record fieldId(String field_id){
        this.field_id=field_id;
        return this;
    }
    
    // Getters
    public long getLineNumber(){
        return this.line_number;
    }
    public RecordField getFields(){
        return this.fields;
    }
    public String fieldId(){
        return this.field_id;
    }
    
}
