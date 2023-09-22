package com.dogiloki.multitaks.database.filter;

import com.dogiloki.multitaks.database.filter.enums.CompOp;
import com.dogiloki.multitaks.database.filter.enums.LogicalOp;
import com.dogiloki.multitaks.database.filter.enums.OpImpl;
import com.dogiloki.multitaks.database.record.Record;

/**
 *
 * @author dogi_
 */

public abstract class Filter{
    
    public static LogicalExpression and(Filter... filters){
        return new LogicalExpression(LogicalOp.AND,filters);
    }
    
    public static LogicalExpression or(Filter... filters){
        return new LogicalExpression(LogicalOp.OR,filters);
    }
    
    public static LogicalExpression not(Filter... filters){
        return new LogicalExpression(LogicalOp.NOT,filters);
    }
    
    public static ComparisonExpression eq(String key, Object value){
        return new ComparisonExpression(key,CompOp.EQ,value);
    }
    
    public static ComparisonExpression ne(String key, Object value){
        return new ComparisonExpression(key,CompOp.NE,value);
    }
    
    public static ComparisonExpression gt(String key, Object value){
        return new ComparisonExpression(key,CompOp.GT,value);
    }
    
    public static ComparisonExpression lt(String key, Object value){
        return new ComparisonExpression(key,CompOp.LT,value);
    }
    
    public static ComparisonExpression gte(String key, Object value){
        return new ComparisonExpression(key,CompOp.GTE,value);
    }
    
    public static ComparisonExpression lte(String key, Object value){
        return new ComparisonExpression(key,CompOp.LTE,value);
    }
    
    public static ComparisonExpression like(String key, Object value){
        return new ComparisonExpression(key,CompOp.LIKE,value);
    }
    
    private Record record;
    
    public abstract boolean logic();
    
    public boolean apply(Record record){
        this.record(record);
        return this.logic();
    }
    
    // Getters
    
    public void record(Record record){
        this.record=record;
    }
    
    public abstract OpImpl getOperator();
    
    public Record getRecord(){
        return this.record;
    }
    
}
