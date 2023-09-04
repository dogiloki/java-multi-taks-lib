package com.dogiloki.multitaks.datastructure.graph;

import com.dogiloki.multitaks.datastructure.Node;

/**
 *
 * @author dogi_
 */

public class NodeGraph<T> extends Node<T>{
    
    private double weight=0;
    
    public NodeGraph(T value){
        super(value);
    }
    
    public double weight(double weight){
        return this.weight=weight;
    }
    
    public double weight(){
        return this.weight;
    }
    
}
