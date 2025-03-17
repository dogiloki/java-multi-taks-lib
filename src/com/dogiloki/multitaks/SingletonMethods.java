package com.dogiloki.multitaks;

/**
 *
 * @author _dogi
 */

public enum SingletonMethods{
    
    MAKE("make"),
    RULES("rules");
    
    private final String str;
    
    private SingletonMethods(String str){
        this.str=str;
    }
    
    @Override
    public String toString(){
        return this.str;
    }
    
}
