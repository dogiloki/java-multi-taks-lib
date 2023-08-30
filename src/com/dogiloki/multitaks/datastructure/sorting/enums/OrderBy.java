package com.dogiloki.multitaks.datastructure.sorting.enums;

/**
 *
 * @author dogi_
 */

public enum OrderBy{
    
    ASC("asc"),
    DESC("desc");
    
    private String text;
    
    private OrderBy(String text){
        this.text=text;
    }
    
    @Override
    public String toString(){
        return this.text;
    }
    
}
