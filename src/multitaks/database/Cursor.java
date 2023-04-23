package multitaks.database;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import multitaks.directory.ModelDirectory;
import multitaks.enums.DirectoryType;

/**
 *
 * @author dogi_
 */

public class Cursor<T>{
    
    private Scanner iterator;
    private Class<T> clazz;
    private T current;
    
    public Cursor(){
        this.iterator=null;
        this.clazz=null;
    }
    
    public Cursor(Scanner iterator, Class<T> clazz){
        this.iterator=iterator;
        //this.clazz=(Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.clazz=clazz;
    }
    
    public boolean hasNext(){
        if(!this.iterator.hasNext()){
            return false;
        }
        return true;
    }
    
    public T next(){
        try{
            String json=this.iterator.nextLine();
            Map<String,Object> fields=new Gson().fromJson(json,new TypeToken<HashMap<String,Object>>(){}.getType());
            this.current=new Gson().fromJson(json,this.clazz);
            this.current.getClass().getMethod("setFields",Map.class).invoke(this.current,fields);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return this.current;
    }
    
}
