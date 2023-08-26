package com.dogiloki.multitaks.database.filter;

import com.dogiloki.multitaks.database.filter.enums.OpImpl;
import com.dogiloki.multitaks.database.record.Record;

/**
 *
 * @author dogi_
 */

public class Filter implements FilterImpl{
    
    private Record record;
    
    public Filter(){
    
    }
    
    public boolean apply(Record record){
        this.record(record);
        return this.logic();
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
    
    public OpImpl getOperator(){
        return null;
    }
    
}
