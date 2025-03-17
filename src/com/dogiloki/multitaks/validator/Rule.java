package com.dogiloki.multitaks.validator;

import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.validator.callbacks.OnActionRule;

/**
 *
 * @author _dogi
 */

public class Rule{
    
    protected String key=null;
    protected OnActionRule action=null;
    protected String message=null;
    
    public Rule(String key, OnActionRule action, String message){
        this.key=Function.set(this.key,(key==null)?key:this.getClass().toString().toLowerCase());
        this.action=action;
        this.message=message;
    }
    
    public boolean passes(Object key, Object value, MapValues values, Object[] params){
        return this.action.run(key,value,values,params);
    }
    
    public String message(){
        return this.message;
    }
    
}
