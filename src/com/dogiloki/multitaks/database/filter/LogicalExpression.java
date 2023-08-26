package com.dogiloki.multitaks.database.filter;

import com.dogiloki.multitaks.database.filter.enums.LogicalOp;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author dogi_
 */

public class LogicalExpression extends Filter{
    
    private final LogicalOp operator;
    private List<Filter> expressions;
    
    public LogicalExpression(LogicalOp operator){
        this.operator=operator;
    }
    
    public LogicalExpression(LogicalOp operator, Filter... expressions){
        this.operator=operator;
        this.expressions=Arrays.asList(expressions);
    }
    
    public LogicalExpression add(Filter expression){
        this.expressions.add(expression);
        return this;
    }
    
    @Override
    public boolean logic(){
        for(Filter expression:this.expressions){
            expression.record(this.getRecord());
            switch(this.operator){
                case AND:{
                    if(!expression.logic()){
                        return false;
                    }
                    break;
                }
                case OR:{
                    if(expression.logic()){
                        return true;
                    }
                    break;
                }
                case NOT:{
                    return !expression.logic();
                }
            }
        }
        return true;
    }
    
    @Override
    public LogicalOp getOperator(){
        return this.operator;
    }
    
}
