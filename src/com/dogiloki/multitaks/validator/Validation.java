package com.dogiloki.multitaks.validator;

/**
 *
 * @author _dogi
 */

public class Validation{
    
    private MapValues values;
    private MapErrors errors;
    
    public Validation(MapValues values, MapErrors errors){
        this.values=values;
        this.errors=errors;
    }
    
    public MapValues values(){
        return this.values;
    }
    
    public MapErrors errors(){
        return this.errors;
    }
    
    public boolean fails(){
        return !this.errors.isEmpty();
    }
    
}
