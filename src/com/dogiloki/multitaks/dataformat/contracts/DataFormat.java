package com.dogiloki.multitaks.dataformat.contracts;

import com.dogiloki.multitaks.dataformat.JSON;
import com.dogiloki.multitaks.directory.ListFields;
import com.dogiloki.multitaks.validator.MapValues;
import java.util.Map;

/**
 *
 * @author dogiloki
 */

public abstract class DataFormat{
    
    public static String messageFormat(String text, MapValues args){
        String str=text;
        for(Map.Entry<String,Object> entry:args.entrySet()){
            str=str.replaceAll(":"+entry.getKey(),entry.getValue().toString());
        }
        return str;
    }
    
    protected ListFields<String> fields=new ListFields();
    protected String json="";
    protected String text="";
    protected Object instace;
    
    public DataFormat(String text){
        this.text=text;
        this.fields=this.format(text);
        if(this.fields!=null){
            this.json=JSON.builder().toJson(this.fields);
        }
    }
    
    public DataFormat(Object instance){
        this.instace=instance;
        this.json=JSON.builder().toJson(instance);
        this.fields=JSON.builder().fromJson(this.json,ListFields.class);
    }
    
    protected abstract ListFields<String> format(String text);
    
    public <T extends Object> T from(Class clazz){
        this.instace=JSON.builder().fromJson(this.json,clazz);
        return (T)this.instace;
    }
    
    public Object getValue(String key){
        return this.fields.get(key);
    }
    
}
