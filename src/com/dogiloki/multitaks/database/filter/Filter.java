package com.dogiloki.multitaks.database.filter;

import com.dogiloki.multitaks.database.record.Record;

/**
 *
 * @author dogi_
 */

public class Filter{
    
    public interface onLogical{
        public void run(Filter expression);
    }
    
    private Expression expression=new Expression();
    
    public Filter expression(Expression expression){
        this.expression=expression;
        return this;
    }
    
    public boolean apply(Record record){
        this.expression.record(record);
        return this.expression.logic();
    }
    
}
