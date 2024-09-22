package com.dogiloki.multitaks;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dogi_
 */

public class GlobalVar{
    
    private static GlobalVar instance=null;
    private Map<String,Object> fields=new HashMap<>();
    private Map<String,GlobalVar> groups=new HashMap<>();
    private GlobalVar sele_group=null;
    
    private GlobalVar(){
        
    }
    
    public static GlobalVar singleton(){
        if(GlobalVar.instance==null){
            GlobalVar.instance=new GlobalVar();
            // Configuraciones por defecto para uso de librería más eficiente
            // Nombre de la carpeta en que se usará database.ModelDB
            GlobalVar.group("db").set("name","db");
            // Nombre de la carpeta en que se usará Storage.store() y Storage.get()
            GlobalVar.group("storage").set("store","store");
            // Nombre de la carpeta en que se usará server.ServerWeb
            GlobalVar.group("server").set("name","server");
        }
        return GlobalVar.instance;
    }
    
    public static GlobalVar group(String key){
        GlobalVar global=GlobalVar.singleton();
        GlobalVar global_group=global.groups.get(key);
        if(global_group==null){
            global_group=new GlobalVar();
            global.groups.put(key,global_group);
        }
        global.sele_group=global_group;
        return global_group;
    }
    
    public static Object get(String key){
        GlobalVar global=GlobalVar.singleton().sele_group;
        if(global==null){
            global=GlobalVar.singleton();
        }
        return global.fields.get(key);
    }
    
    public static void set(String key, Object value){
        GlobalVar global=GlobalVar.singleton().sele_group;
        if(global==null){
            global=GlobalVar.singleton();
        }
        global.fields.put(key,value);
    }
    
    public static Map<String,GlobalVar> groups(){
        GlobalVar global=GlobalVar.singleton();
        return global.groups;
    }
    
    public static Map<String,Object> all(){
        GlobalVar global=GlobalVar.singleton().sele_group;
        if(global==null){
            global=GlobalVar.singleton();
        }
        return global.fields;
    }
    
}
