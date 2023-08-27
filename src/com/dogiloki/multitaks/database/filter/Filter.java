package com.dogiloki.multitaks.database.filter;

import com.dogiloki.multitaks.database.filter.enums.OpImpl;
import com.dogiloki.multitaks.database.record.Record;

/**
 *
 * @author dogi_
 */

public abstract class Filter implements FilterImpl{
    
    private Record record;
    
    @Override
    public boolean apply(Record record){
        this.record(record);
        return this.logic();
    }
    
    @Override
    public void record(Record record){
        this.record=record;
    }
    
    @Override
    public abstract boolean logic();
    
    @Override
    public Record getRecord(){
        return this.record;
    }
    
    @Override
    public abstract OpImpl getOperator();
    
}
