package com.dogiloki.multitaks.datastructure.graph;

import com.dogiloki.multitaks.datastructure.Node;

/**
 *
 * @author dogi_
 */

public class NodeGraph<T> extends Node<T>{
    
    private Double weight=0.0;
    
    public NodeGraph(T value){
        super(value);
    }
    
    public Double weight(Double w){
        return this.weight=w;
    }
    
    public Double weight(){
        return this.weight;
    }
    
}
