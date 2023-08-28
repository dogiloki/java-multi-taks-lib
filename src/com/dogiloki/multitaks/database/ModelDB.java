package com.dogiloki.multitaks.database;

import com.dogiloki.multitaks.database.record.Record;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.dogiloki.multitaks.GlobalVar;
import com.dogiloki.multitaks.dataformat.JSON;
import com.dogiloki.multitaks.database.annotations.Collect;
import com.dogiloki.multitaks.database.filter.ComparisonExpression;
import com.dogiloki.multitaks.database.filter.Filter;
import com.dogiloki.multitaks.database.record.RecordField;
import com.google.gson.annotations.Expose;
/**
 *
 * @author dogi_
 */

public class ModelDB extends Record{
    
    @Expose
    public String _id;
    @Expose
    public String created_at;
    @Expose
    public String update_at;
    @Expose
    public String delete_at;
    
    private String date_format="dd-MM-yyyy HH:mm:ss";
    private boolean with_trashed;
    private Database db=null;
    
    public ModelDB(){
        
    }
    
    public ModelDB(String src){
        this.db=new Database(src);
    }
    
    public boolean save(){
        Collection collection=this.getCollection();
        Object instance=this.getInstance();
        String json=JSON.builder().toJson(instance);
        RecordField fields=new Gson().fromJson(json,RecordField.class);
        Record record=new Record();
        record.setFields(fields);
        record.setId(this.getId());
        boolean status;
        Filter filter=new ComparisonExpression(record.fieldId(),record.getId());
        if(collection.find(filter).first()==null){
            record.generateId();
            record.set("created_at",this.getDateTime());
            record.set("update_at","");
            status=collection.insert(record);
        }else{
            record.set("update_at",this.getDateTime());
            status=collection.update(filter,record);
        }
        this.setFields(record.getFields());
        return status;
    }
    
    protected boolean deleteSave(){
        return false;
    }
    
    public Object getInstance(){
        return this;
    }
    
    public Database getConnection(){
        if(this.db==null){
            String src=(String)GlobalVar.group("db").get("name");
            this.db=new Database(src);
        }
        return this.db;
    }
    
    public Collection getCollection(){
        Collect annot_table=this.getInstance().getClass().getAnnotation(Collect.class);
        if(annot_table==null){
            return null;
        }
        return this.getConnection().collection(annot_table.src(),this.getInstance().getClass());
    }
    
    public String getDateTime(){
        return new SimpleDateFormat(this.date_format).format(new Date());
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
        Filter filter=new ComparisonExpression(record.fieldId(),record.getId());
        return collection.update(filter,record);
    }
    
    public boolean delete(){
        Object instance=this.getInstance();
        Collect annot_table=instance.getClass().getAnnotation(Collect.class);
        if(annot_table==null){
            return false;
        }
        Collection collection=this.getConnection().collection(annot_table.src());
        Record record=new Record().setId(this.getId());
        Filter filter=new ComparisonExpression(record.fieldId(),record.getId());
        if(this.deleteSave()){
            record.set("delete_at",this.getDateTime());
            return collection.update(filter,record);
        }else{
            return collection.delete(filter);
        }
    }
    
}
