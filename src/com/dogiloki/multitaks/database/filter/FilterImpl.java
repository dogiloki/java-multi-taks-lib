package com.dogiloki.multitaks.database.filter;

import com.dogiloki.multitaks.database.filter.enums.OpImpl;
import com.dogiloki.multitaks.database.record.Record;

/**
 *
 * @author dogi_
 */

public interface FilterImpl{
    
    public boolean apply(Record record);
    public void record(Record record);
    public boolean logic();
    public Record getRecord();
    public OpImpl getOperator();
    
}
