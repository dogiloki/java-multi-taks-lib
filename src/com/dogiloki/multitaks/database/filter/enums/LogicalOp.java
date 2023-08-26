package com.dogiloki.multitaks.database.filter.enums;

/**
 *
 * @author dogi_
 */
public enum LogicalOp{
    
    AND("&"),
    OR("|"),
    NOT("!");
    
    private String text;
    
    private LogicalOp(String text){
        this.text=text; 
    }
    
    @Override
    public String toString(){
        return this.text;
    }
    
}
