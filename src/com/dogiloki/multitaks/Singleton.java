package com.dogiloki.multitaks;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author _dogi
 */

public class Singleton<T>{
    
    protected static final Map<Class<?>,Singleton> instances=new HashMap<>();
    
    public static synchronized <T extends Singleton> T instance(Class<T> clazz){
        return clazz.cast(Singleton.instances.get(clazz));
    }
    
    public static synchronized <T extends Singleton> T singleton(Class<T> clazz){
        return (T) Singleton.instances.computeIfAbsent(clazz,key->createInstance(clazz));
    }
    
    private static <T extends Singleton> T createInstance(Class<T> clazz){
        try{
            return clazz.getDeclaredConstructor().newInstance();
        }catch(Exception ex){
            ex.printStackTrace();
            throw new RuntimeException(clazz.getName()+" - "+ex.getMessage(),ex);
        }
    }
    
    private static Class<?>[] getParameterTypes(Object... args){
        return Arrays.stream(args).map(arg->arg==null?Object.class:args.getClass()).toArray(Class<?>[]::new);
    }
    
    public Object callMethod(SingletonMethods method_name, Object... args){
        try{
            Method method=this.getClass().getDeclaredMethod(method_name.toString(),Singleton.getParameterTypes(args));
            method.setAccessible(true);
            return method.invoke(this,args);
        }catch(Exception ex){
            ex.printStackTrace();
            throw new RuntimeException(method_name+" - "+ex.getMessage(),ex);
        }
    }
    
}