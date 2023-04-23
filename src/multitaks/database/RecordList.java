package multitaks.database;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author dogi_
 */

public class RecordList{
    
    private final Scanner iterator;
    private Record current;
    private Record record_find;
    private int line_number=0;
    
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
    
    public boolean hasNext(){
        if(!this.iterator.hasNextLine()){
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
        if(this.record_find==null){
            this.current=record_found;
        }else{
            int count_found=0;
            for(Map.Entry<String,Object> entry_find:this.record_find.getFields().entrySet()){
                for(Map.Entry<String,Object> entry:fields.entrySet()){
                    if(entry_find.getValue()==null || entry.getValue()==null){
                        continue;
                    }
                    if(entry_find.getKey().equals(entry.getKey()) && entry_find.getValue().equals(entry.getValue())){
                        count_found++;
                    }
                }
            }
            if(count_found==this.record_find.getFields().size()){
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
    
}
