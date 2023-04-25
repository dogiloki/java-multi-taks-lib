package multitaks.database;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import multitaks.Function;
import multitaks.GlobalVar;
import multitaks.dataformat.JSON;
import multitaks.database.annotations.Collect;

/**
 *
 * @author dogi_
 */

public class ModelDB extends Record{
    
    private Database db=null;
    private String date_format="dd-MM-yyyy HH:mm:ss";
    
    public static Cursor all(Class<?> clazz){
        try{
            Object instance=clazz.newInstance();
            return (Cursor)instance.getClass().getMethod("all").invoke(instance);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public static <T> T find(Class<?> clazz, Record record){
        try{
            Object instance=clazz.newInstance();
            return (T)instance.getClass().getMethod("find",Record.class).invoke(instance,record);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public ModelDB(){
        if(this.db==null){
            String src=Function.assign((String)GlobalVar.group("db").get("name"),"db");
            this.db=new Database(src);
        }
    }
    
    public Database getConnection(){
        return this.db;
    }
    
    public Object getInstance(){
        return this;
    }
    
    public boolean deleteSave(){
        return false;
    }
    
    public String getDateTime(){
        return new SimpleDateFormat(this.date_format).format(new Date());
    }
    
    public Collection getCollection(){
        Collect annot_table=this.getInstance().getClass().getAnnotation(Collect.class);
        if(annot_table==null){
            return null;
        }
        return this.getConnection().collection(annot_table.src());
    }
    
    public boolean save(){
        Collection collection=this.getCollection();
        Object instance=this.getInstance();
        String json=JSON.builder().toJson(instance);
        Map<String,Object> fields=new Gson().fromJson(json,new TypeToken<HashMap<String,Object>>(){}.getType());
        Record record=new Record();
        record.setFields(fields);
        record.setId(this.getId());
        boolean status;
        if(collection.find(new Record().setId(record.getId())).first()==null){
            record.generateId();
            record.set("created_at",this.getDateTime());
            record.set("update_at","");
            status=collection.insert(record);
        }else{
            record.set("update_at",this.getDateTime());
            status=collection.update(new Record().setId(record.getId()),record);
        }
        this.setFields(record.getFields());
        return status;
    }
    
    public Cursor find(Record record){
        if(this.deleteSave()){
            record.setOperation(new Operation("delete_at",null));
        }
        try{
            Object instance=this.getInstance();
            Collect annot_table=instance.getClass().getAnnotation(Collect.class);
            if(annot_table==null){
                return null;
            }
            Collection collection=this.getConnection().collection(annot_table.src());
            RecordList records_find=collection.find(record);
            return new Cursor(records_find,instance.getClass());
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public Cursor all(){
        if(this.deleteSave()){
            return this.find(new Record());
        }
        try{
            Object instance=this.getInstance();
            Collect annot_table=instance.getClass().getAnnotation(Collect.class);
            if(annot_table==null){
                return null;
            }
            Collection collection=this.getCollection();
            RecordList records=collection.all();
            return new Cursor(records,instance.getClass());
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public boolean delete(){
        Object instance=this.getInstance();
        Collect annot_table=instance.getClass().getAnnotation(Collect.class);
        if(annot_table==null){
            return false;
        }
        Collection collection=this.getConnection().collection(annot_table.src());
        Record record=new Record().setId(this.getId());
        if(this.deleteSave()){
            record.set("delete_at",this.getDateTime());
            return collection.update(new Record().setId(record.getId()),record);
        }else{
            return collection.delete(record);
        }
    }
    
}
