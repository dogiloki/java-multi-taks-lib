package com.dogiloki.multitaks.directory;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dogi_
 */

public class ConfigFile{
    
    private static Map<Class,Object> instance=new HashMap<>();
    
    public static void load(Class clazz){
        ConfigFile._load(clazz,null);
    }
    
    public static void load(Class clazz, String src){
        ConfigFile._load(clazz,src);
    }
    
    private static void _load(Class clazz, String src){
        try{
            Object model=new ModelDirectory().aim(clazz.newInstance(),src).builder();
            ModelDirectory model_directory=new ModelDirectory().aim(model,src);
            model_directory.save();
            model_directory.close();
            ConfigFile.instance.put(clazz,model);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
