package com.dogiloki.multitaks.dataformat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.dogiloki.multitaks.StorageOld;
import com.dogiloki.multitaks.directory.ListFields;
import com.dogiloki.multitaks.directory.Storage;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import com.dogiloki.multitaks.directory.interfaces.DataFormat;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import java.lang.reflect.Type;

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
    
}
