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
    
    public boolean hasNext(){
        if(this.record_list!=null){
            return this.record_list.hasNext();
        }
        if(!this.iterator.hasNext()){
            return false;
        }
        return true;
    }
    
    public T next(){
        String json;
        if(this.record_list==null){
            json=this.iterator.nextLine();
        }else{
            json=this.record_list.next().getJson();
        }
        try{
            
            Map<String,Object> fields=new Gson().fromJson(json,new TypeToken<HashMap<String,Object>>(){}.getType());
            this.current=new Gson().fromJson(json,this.clazz);
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
