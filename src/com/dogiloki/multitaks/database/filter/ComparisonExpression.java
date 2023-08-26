package com.dogiloki.multitaks.database.filter;

import com.dogiloki.multitaks.database.filter.enums.CompOp;
import com.dogiloki.multitaks.database.record.Record;

/**
 *
 * @author dogi_
 */

public class ComparisonExpression extends Filter{
    
    private final String key;
    private final CompOp operator;
    private final Object value;
    private Record record;
    
    public ComparisonExpression(String key, CompOp operator, Object value){
        this.key=key;
        this.operator=operator;
        this.value=value;
    }

    @Override
    public boolean logic(){
        Record record=this.getRecord();
        if(record==null){
            return false;
        }
        switch(this.operator){
            case EQ:{
                return this.value.equals(record.get(this.key));
            }
            case NE:{
                return !this.value.equals(record.get(this.key));
            }
            case GT:{
                return this.value.toString().compareTo(record.get(this.key).toString())>0;
            }
            case LT:{
                return this.value.toString().compareTo(record.get(this.key).toString())<0;
            }
            case GTE:{
                return this.value.toString().compareTo(record.get(this.key).toString())>0 && this.value.equals(record.get(this.key));
            }
            case LTE:{
                return this.value.toString().compareTo(record.get(this.key).toString())<0 && this.value.equals(record.get(this.key));
            }
        }
        return true;
    }

    @Override
    public CompOp getOperator(){
        return this.operator;
    }
    
}
