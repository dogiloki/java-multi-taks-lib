package com.dogiloki.multitaks.database.filter;

import com.dogiloki.multitaks.database.record.Record;

/**
 *
 * @author dogi_
 */

public class Expression{
    
    protected Record record;
    
    public Expression(){
    
    }
    
    public void record(Record record){
        this.record=record;
    }
    
    public boolean logic(){
        return false;
    }
    
    public Record getRecord(){
        return this.record;
    }
    
}
