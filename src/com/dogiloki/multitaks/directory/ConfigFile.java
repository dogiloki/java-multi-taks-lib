package com.dogiloki.multitaks.directory;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dogi_
 */

public class ConfigFile{
    
    private static Map<Class,Object> instance=new HashMap<>();
    
    public static <T> T load(Class<T> clazz){
        return ConfigFile._load(clazz,null);
    }
    
    public static <T> T load(Class<T> clazz, String src){
        return ConfigFile._load(clazz,src);
    }
    
    private static <T> T _load(Class<T> clazz, String src){
        try{
            T empty_model=clazz.getDeclaredConstructor().newInstance();
            ModelDirectory directory=new ModelDirectory();
            directory.referenceClass(clazz);
            
            T model=directory.aim(empty_model,src).builder();
            
            if(model!=null && !directory.isFromJar()){
                directory.save();
            }
            directory.close();
            
            ConfigFile.instance.put(clazz,model);
            return model;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public static <T> void save(Class<T> clazz){
        try{
            T model=clazz.cast(ConfigFile.instance.get(clazz));
            if(model==null) return;
            ModelDirectory directory=new ModelDirectory();
            directory.referenceClass(clazz);
            directory.aim(model,null);
            
            // Nunca intentar escribir un recurso dentro del JAR
            if(!directory.isFromJar()){
                directory.save();
            }
            directory.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
