package com.dogiloki.multitaks.dataformat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.dogiloki.multitaks.dataformat.contracts.DataFormat;
import com.dogiloki.multitaks.directory.HashFields;

/**
 *
 * @author dogi_
 */

public class JSON extends DataFormat{
    
    public static Gson gson(){
        return new Gson();
    }
    
    public static Gson builder(){
        return new GsonBuilder()
                .excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT)
                .excludeFieldsWithoutExposeAnnotation()
                .serializeNulls()
                .serializeSpecialFloatingPointValues()
                .setLenient()
                .create();
    }
    
    public static Gson builderNotNulls(){
        return new GsonBuilder()
                .excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT)
                .excludeFieldsWithoutExposeAnnotation()
                .serializeSpecialFloatingPointValues()
                .setLenient()
                .create();
    }
    
    
    public static Gson builderDefault(){
        return new Gson();
    }
    
    public JSON(String text){
        super(text);
    }
    
    public JSON(Object instace){
        super(instace);
    }
    
    @Override
    protected HashFields format(String text){
        HashFields fields=new HashFields();
        if(text==null || text.isEmpty()){
            return fields;
        }
        fields=JSON.builder().fromJson(text,HashFields.class);
        return fields;
    }
    
    @Override
    public String toString(){
        return this.json;
    }
    
    public JSON toJson(String key){
        return new JSON(this.getValue(key));
    }
    
}
