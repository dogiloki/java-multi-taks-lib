package com.dogiloki.multitaks.dataformat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.dogiloki.multitaks.directory.ListFields;
import com.dogiloki.multitaks.dataformat.contracts.DataFormat;

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
                .excludeFieldsWithoutExposeAnnotation()
                .serializeNulls()
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
    protected ListFields format(String text){
        ListFields fields=new ListFields();
        if(text.isEmpty()){
            return fields;
        }
        fields=JSON.builder().fromJson(text,ListFields.class);
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
