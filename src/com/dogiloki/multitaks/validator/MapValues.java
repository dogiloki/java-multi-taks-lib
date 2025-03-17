package com.dogiloki.multitaks.validator;

import com.dogiloki.multitaks.directory.HashFields;

/**
 *
 * @author _dogi
 */

public class MapValues extends HashFields<String,Object>{
    
    public MapValues(){
        super();
    }
    
    public MapValues(Object[] values){
        for(int index=0; index<values.length; index++){
            this.append(String.valueOf(index),values[index]);
        }
    }
    
}