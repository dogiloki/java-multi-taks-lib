package com.dogiloki.multitaks.validator.enums;

import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.validator.RuleWithParams;

/**
 *
 * @author _dogi
 */

public enum ValidatorRule{
    
    NULLABLE,
    REQUIRED,
    STRING,
    INTEGER,
    DECIMAL,
    NUMERIC,
    BOOLEAN,
    DATE,
    MIN,
    MAX,
    BETWEEN,
    EMAIL,
    JSON,
    URL,
    SAME,
    REGEX,
    DIFERED,
    CONFIRMED,
    LENGTH,
    MIN_LENGTH,
    MAX_LENGTH,
    IN,
    NOT_IN,
    UNIQUE,
    DATE_TIME_FORMAT,
    CALLBACK;
    
    public static RuleWithParams append(ValidatorRule rule, Object... params){
        return new RuleWithParams().append(rule,params);
    }
    
    private String str=null;
    
    private ValidatorRule(){
        
    }
    
    private ValidatorRule(String str){
        this.str=str;
    }
    
    @Override
    public String toString(){
        return Function.set(this.str,this.name().toLowerCase());
    }
    
}
