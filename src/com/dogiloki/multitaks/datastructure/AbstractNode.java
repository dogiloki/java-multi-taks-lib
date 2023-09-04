package com.dogiloki.multitaks.datastructure;

import java.lang.reflect.Constructor;

/**
 *
 * @author dogi_
 */

public class AbstractNode<T,N>{
    
    private Class clazz;
    
    public AbstractNode(Class clazz){
        this.clazz=clazz;
    }
    
    public T newNode(N value){
        try{
            Constructor<T> constructor=this.clazz.getConstructor(value.getClass());
            return constructor.newInstance(value);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
}
