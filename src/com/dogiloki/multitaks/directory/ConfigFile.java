package com.dogiloki.multitaks.directory;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dogi_
 */

public class ConfigFile{
    
    private static Map<Class,Object> instance=new HashMap<>();
    
    public static <T extends Object> T load(Class clazz){
        return ConfigFile._load(clazz,null);
    }
    
    public static <T extends Object> T load(Class clazz, String src){
        return ConfigFile._load(clazz,src);
    }
    
    private static <T extends Object> T _load(Class clazz, String src){
        T model=null;
        try{
            model=new ModelDirectory().aim(clazz.newInstance(),src).builder();
            ModelDirectory model_directory=new ModelDirectory().aim(model,src);
            model_directory.save();
            model_directory.close();
            ConfigFile.instance.put(clazz,model);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return model;
    }
    
    public static <T> void save(Class<T> clazz){
        try{
            T model=clazz.cast(ConfigFile.instance.get(clazz));
            if(model!=null){
                ModelDirectory md=new ModelDirectory().aim(model,null);
                md.save();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
