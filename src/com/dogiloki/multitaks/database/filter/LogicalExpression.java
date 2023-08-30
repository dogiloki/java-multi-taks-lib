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
        if(expression!=null){
            this.expressions.add(expression);
        }
        return this;
    }
    
    @Override
    public boolean logic(){
        int count=0;
        boolean logic=false;
        switch(this.operator){
            case AND:{
                for(Filter expression:this.expressions){
                    expression.record(this.getRecord());
                    if(expression.logic()){
                        count++;
                    }
                }
                logic=(count==this.expressions.size() && count!=0);
                break;
            }
            case OR:{
                loopout:for(Filter expression:this.expressions){
                    expression.record(this.getRecord());
                    if(expression.logic()){
                        count++;
                        break loopout;
                    }
                }
                logic=count!=0;
                break;
            }
            case NOT:{
                for(Filter expression:this.expressions){
                    expression.record(this.getRecord());
                    logic=expression.logic();
                }
                logic=this.expressions.size()<=0?true:(logic?false:true);
            }
        }
        return logic;
    }
    
    @Override
    public LogicalOp getOperator(){
        return this.operator;
    }
    
    @Override
    public String toString(){
        String str=" "+this.operator.toString()+"( ";
        int index=0;
        for(Filter expression:this.expressions){
            index++;
            str+=expression.toString()+(index<this.expressions.size()?", ":"");
        }
        str+=" ) ";
        return str;
    }
    
}
