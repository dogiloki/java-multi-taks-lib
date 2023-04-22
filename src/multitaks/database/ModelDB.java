package multitaks.database;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import multitaks.database.annotations.Table;
import java.util.Map;
import multitaks.Function;
import multitaks.GlobalVar;
import multitaks.directory.ModelDirectory;

/**
 *
 * @author dogi_
 */

public class ModelDB{
    
    private static Database db=null;
    
    public ModelDB(){
        
    }
    
    public static Database getConnection(){
        if(ModelDB.db==null){
            String src=Function.assign((String)GlobalVar.group("db").get("name"),"db");
            ModelDB.db=new Database(src);
        }
        return ModelDB.db;
    }
    
    public Object getInstance(){
        return this;
    }
    
    public Collection getCollection(){
        Table annot_table=this.getInstance().getClass().getAnnotation(Table.class);
        if(annot_table==null){
            return null;
        }
        return ModelDB.getConnection().collection(annot_table.src());
    }
    
    public boolean save(){
        Collection collection=this.getCollection();
        ModelDirectory model=new ModelDirectory(this.getInstance());
        Record record=new Record();
        for(Map.Entry<String,Object> entry:model.getFields().entrySet()){
            record.set(entry.getKey(),entry.getValue());
        }
        if(collection.update(new Record().setId(record.getId()),record)){
            return true;
        }else{
            return collection.insert(record);
        }
    }
    
    public static Object find(Record record, Class clazz){
        try{
            Object instance=clazz.newInstance();
            Table annot_table=instance.getClass().getAnnotation(Table.class);
            if(annot_table==null){
                return null;
            }
            Collection collection=ModelDB.getConnection().collection(annot_table.src());
            Record record_find=collection.find(record);
            return new Gson().fromJson(record_find.getJson(),instance.getClass());
        }catch(Exception ex){
            return null;
        }
    }
    
    public static List<Object> all(Class clazz){
        List<Object> objects=new ArrayList<>();
        try{
            Object instance=clazz.newInstance();
            Table annot_table=instance.getClass().getAnnotation(Table.class);
            if(annot_table==null){
                return null;
            }
            Collection collection=ModelDB.getConnection().collection(annot_table.src());
            for(Record record:collection.all()){
                objects.add(new Gson().fromJson(record.getJson(),instance.getClass()));
            }
        }catch(Exception ex){
            ex.printStackTrace();;
        }
        return objects;
    }
    
}
