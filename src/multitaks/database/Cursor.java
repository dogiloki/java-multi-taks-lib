package multitaks.database;

import com.google.gson.Gson;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Map;
import multitaks.directory.ModelDirectory;
import multitaks.enums.DirectoryType;

/**
 *
 * @author dogi_
 */

public class Cursor<T>{
    
    private Iterator<Path> iterator;
    private Class<T> clazz;
    private T current;
    
    public Cursor(){
        this.iterator=null;
        this.clazz=null;
    }
    
    public Cursor(Iterator<Path> iterator, Class<T> clazz){
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
            String src=this.iterator.next().toString();
            ModelDirectory model=new ModelDirectory();
            model.aim(src,DirectoryType.FILE);
            String json=model.read();
            this.current=new Gson().fromJson(json,this.clazz);
            this.current.getClass().getMethod("setFields",Map.class).invoke(this.current,model.getFields());
            this.current.getClass().getMethod("aim",String.class,DirectoryType.class).invoke(this.current,model.getSrc(),model.getType());
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return this.current;
    }
    
}
