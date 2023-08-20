package multitaks.database;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
    private boolean with_trashed;
    
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
            String src=(String)GlobalVar.group("db").get("name");
            this.db=new Database(src);
        }
    }
    
    private Database getConnection(){
        return this.db;
    }
    
    protected Object getInstance(){
        return this;
    }
    
    protected boolean deleteSave(){
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
    
    public ModelDB withTrashed(){
        this.with_trashed=true;
        return this;
    }
    
    public boolean restore(){
        Collection collection=this.getCollection();
        Record record=new Record();
        record.setId(this.getId());
        record.set("delete_at",null);
        return collection.update(new Record().setId(record.getId()),record);
    }
    
    public Cursor find(Record record){
        if(this.deleteSave() && !this.with_trashed){
            record.setOperation(new Operation("delete_at",null));
        }
        this.with_trashed=false;
        try{
            Object instance=this.getInstance();
            Collect annot_table=instance.getClass().getAnnotation(Collect.class);
            if(annot_table==null){
                return null;
            }
            Collection collection=this.getConnection().collection(annot_table.src());
            RecordList records_find=collection.find(record);
            collection.close();
            return new Cursor(records_find,instance.getClass());
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public Cursor all(){
        if(this.deleteSave() && !this.with_trashed){
            return this.find(new Record());
        }
        this.with_trashed=false;
        try{
            Object instance=this.getInstance();
            Collect annot_table=instance.getClass().getAnnotation(Collect.class);
            if(annot_table==null){
                return null;
            }
            Collection collection=this.getCollection();
            RecordList records=collection.all();
            collection.close();
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
