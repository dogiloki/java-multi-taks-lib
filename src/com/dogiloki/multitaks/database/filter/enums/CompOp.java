package com.dogiloki.multitaks.database.filter.enums;

/**
 *
 * @author dogi_
 */

// Comparison Operators
public enum CompOp{
    
    EQ("="),
    NE("!="),
    GT(">"),
    LT("<"),
    GTE(">="),
    LTE("<=");
    
    private String text;
    
    private CompOp(String text){
        this.text=text; 
    }
    
    @Override
    public String toString(){
        return this.text;
    }
    
}
