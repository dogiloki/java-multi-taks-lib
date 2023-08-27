package com.dogiloki.multitaks.database.filter;

import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.database.filter.enums.CompOp;
import com.dogiloki.multitaks.database.record.Record;
import java.util.Objects;

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
    
    public ComparisonExpression(String key, Object value){
        this.key=key;
        this.operator=CompOp.EQ;
        this.value=value;
    }

    @Override
    public boolean logic(){
        Record record=this.getRecord();
        if(record==null){
            return false;
        }
        Object value=record.get(this.key);
        switch(this.operator){
            case EQ:{
                return Objects.equals(value,this.value);
            }
            case NE:{
                return !Objects.equals(value,this.value);
            }
            case GT:{
                return Function.compareTo(value,this.value)>0;
            }
            case LT:{
                return Function.compareTo(value,this.value)<0;
            }
            case GTE:{
                return Function.compareTo(value,this.value)>0 && Objects.equals(value,this.value);
            }
            case LTE:{
                return Function.compareTo(value,this.value)<0 && Objects.equals(value,this.value);
            }
        }
        return true;
    }

    @Override
    public CompOp getOperator(){
        return this.operator;
    }
    
}
