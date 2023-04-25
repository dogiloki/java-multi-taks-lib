package multitaks.database;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import multitaks.dataformat.JSON;

/**
 *
 * @author dogi_
 */

public class Cursor<T>{
    
    private Scanner iterator;
    private Class<T> clazz;
    private T current;
    private RecordList record_list;
    
    public Cursor(){
        this.iterator=null;
        this.clazz=null;
    }
    
    public Cursor(RecordList record_list, Class<T> clazz){
        this.clazz=clazz;
        this.record_list=record_list;
    }
    
    public Cursor(Scanner iterator, Class<T> clazz){
        this.iterator=iterator;
        //this.clazz=(Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.clazz=clazz;
    }
    
    private boolean hasNext(){
        if(this.iterator==null){
            return false;
        }
        if(!this.iterator.hasNext()){
            return false;
        }
        return true;
    }
    
    public T next(){
        String json;
        Record record=null;
        this.current=null;
        if(this.record_list==null){
            json=this.iterator.nextLine();
        }else{
            record=this.record_list.next();
            if(record==null){
                return null;
            }
            json=record.getJson();
        }
        try{
            Map<String,Object> fields;
            if(record==null){
                fields=new Gson().fromJson(json,new TypeToken<HashMap<String,Object>>(){}.getType());
            }else{
                fields=record.getFields();
            }
            System.out.println(record);
            this.current=JSON.builder().fromJson(json,this.clazz);
            this.current.getClass().getMethod("setFields",Map.class).invoke(this.current,fields);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return (T)this.current;
    }
    
    public T firt(){
        return this.hasNext()?this.next():null;
    }
    
}
