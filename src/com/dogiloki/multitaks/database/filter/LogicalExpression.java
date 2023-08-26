package com.dogiloki.multitaks.database.filter;

import com.dogiloki.multitaks.database.filter.enums.LogicalOp;
import java.util.List;

/**
 *
 * @author dogi_
 */

public class LogicalExpression extends Expression{
    
    private final LogicalOp operator;
    private List<Expression> expressions;
    
    public LogicalExpression(LogicalOp operator){
        this.operator=operator;
    }
    
    public LogicalExpression add(Expression expression){
        this.expressions.add(expression);
        return this;
    }
    
    @Override
    public boolean logic(){
        for(Expression expression:this.expressions){
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
    
}
