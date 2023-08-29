package com.dogiloki.multitaks.dataformat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.dogiloki.multitaks.StorageOld;
import com.dogiloki.multitaks.directory.Storage;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import com.dogiloki.multitaks.directory.interfaces.DataFormat;

/**
 *
 * @author dogi_
 */

public class JSON implements DataFormat{
    
    public static Gson gson(){
        return new Gson();
    }
    
    public static Gson builder(){
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().serializeSpecialFloatingPointValues().setLenient().create();
    }
    
    public static String createJson(Object json){
        return new Gson().toJson(json);
    }
    
    private String json;
    private JsonParser parser;
    private JsonArray array;
    private JsonElement element;
    private JsonObject object;
    private int indice;
    private boolean is_array;
    
     public JSON(String json){
        this.indice=0;
        this.json=json==null || json.trim().equals("")?null:json.trim();
        this.init();
    }
    
    public JSON(String dir, Class _class, DirectoryType type){
        this.indice=0;
        switch(type){
            case FILE: this.json=String.join(" ",StorageOld.readFile(_class,dir.trim())); break;
        }
        this.init();
    }
    
    public JSON(String dir, DirectoryType type){
        this.indice=0;
        switch(type){
            case FILE: this.json=String.join(" ",new Storage(dir.trim()).read()); break;
        }
        this.init();
    }
    
    private void init(){
        if(this.json==null){
            this.object=new JsonObject();
            return;
        }
        this.parser=new JsonParser();
        this.json=(this.json==null || this.json.equals(""))?"[]":this.json;
        this.is_array=this.json.substring(0,1).equals("[") && this.json.substring(this.json.length()-1,this.json.length()).equals("]");
        if(this.is_array){
            this.array=this.parser.parse(this.json).getAsJsonArray();
            //this.element=this.array.get(this.indice);
            //this.object=this.element.getAsJsonObject();
        }else{
            this.object=this.parser.parse(this.json).getAsJsonObject();
        }
    }
    
    public boolean isArray(){
        return this.is_array;
    }
    
    public JSON toJson(String key){
        return new JSON(this.getValue(key).toString());
    }
    
    @Override
    public Object getValue(String key){
        return this.object.get(key);
    }
    
    public Object getValue(int key){
        return this.array.get(key);
    }
    
}
