package com.dogiloki.multitaks.validator;

import com.dogiloki.multitaks.Singleton;
import com.dogiloki.multitaks.validator.callbacks.OnActionRule;
import java.util.Map;

/**
 *
 * @author _dogi
 */

public class Validator extends Singleton{
    
    private MapRules rules=new MapRules();
    
    public void make(String key, OnActionRule action, String message){
        this.rules.put(key,new Rule(key,action,message));
    }
    
    public Map<String,Rule> rules(){
        return this.rules;
    }
    
}
