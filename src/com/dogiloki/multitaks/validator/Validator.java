package com.dogiloki.multitaks.validator;

import com.dogiloki.multitaks.Singleton;
import com.dogiloki.multitaks.validator.callbacks.OnActionRule;
import com.dogiloki.multitaks.validator.enums.ValidatorRule;

/**
 *
 * @author _dogi
 */

public class Validator extends Singleton{
    
    private MapRules rules=new MapRules();
    
    public void make(String key, OnActionRule action, String message){
        this._make(key,action,message);
    }
    
    public void make(ValidatorRule validator_rule, OnActionRule action, String message){
        this._make(validator_rule.toString(),action,message);
    }
    
    private void _make(String key, OnActionRule action, String message){
        this.rules.put(key,new Rule(key,action,message));
    }
    
    public MapRules rules(){
        return this.rules;
    }
    
}
