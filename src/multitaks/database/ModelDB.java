package multitaks.database;

import com.google.gson.Gson;
import java.util.Iterator;
import multitaks.database.annotations.Table;
import java.util.Map;
import multitaks.Function;
import multitaks.GlobalVar;
import multitaks.directory.ModelDirectory;
import multitaks.enums.DirectoryType;

/**
 *
 * @author dogi_
 */

public class ModelDB extends Record{
    
    private Database db=null;
    
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
    
    public Collection getCollection(){
        Table annot_table=this.getInstance().getClass().getAnnotation(Table.class);
        if(annot_table==null){
            return null;
        }
        return this.getConnection().collection(annot_table.src());
    }
    
    public boolean save(){
        Collection collection=this.getCollection();
        Object instance=this.getInstance();
        ModelDirectory model=new ModelDirectory(instance);
        Record record=new Record();
        for(Map.Entry<String,Object> entry:model.getFields().entrySet()){
            record.set(entry.getKey(),entry.getValue());
        }
        boolean status;
        if(collection.update(new Record().setId(record.getId()),record)){
            status=true;
        }else{
            status=collection.insert(record);
        }
        this.setFields(record.getFields());
        return status;
    }
    
    public <T> T find(Record record){
        try{
            Object instance=this.getInstance();
            Table annot_table=instance.getClass().getAnnotation(Table.class);
            if(annot_table==null){
                return null;
            }
            Collection collection=this.getConnection().collection(annot_table.src());
            Record record_find=collection.find(record).first();
            return (T)new Gson().fromJson(record_find.getJson(),instance.getClass());
        }catch(Exception ex){
            return null;
        }
    }
    
    public Cursor all(){
        try{
            Object instance=this.getInstance();
            Table annot_table=instance.getClass().getAnnotation(Table.class);
            if(annot_table==null){
                return null;
            }
            Collection collection=this.getCollection();
            RecordList records=collection.all();
            return new Cursor(records.iterator(),instance.getClass());
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new Cursor(new Iterator(){
            @Override
            public boolean hasNext(){
                return false;
            }

            @Override
            public Object next(){
                return null;
            }
        },null);
    }
    
}
