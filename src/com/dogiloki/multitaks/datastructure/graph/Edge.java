package com.dogiloki.multitaks.datastructure.graph;

import com.dogiloki.multitaks.datastructure.graph.callbacks.OnWeight;

/**
 *
 * @author dogi_
 */

public class Edge<T>{
    
    private OnWeight<T> weight=null;
    private boolean directed=true;
    private NodeGraph<T> source;
    private NodeGraph<T> destination;
    
    
    public Edge(NodeGraph source, NodeGraph destination){
        this.source=source;
        this.destination=destination;
    }
    
    public void onWeight(OnWeight<T> weight){
        this.weight=weight;
    }
    
    public Number weight(){
        return this.weight.run(this);
    }
    
    public boolean hasNode(NodeGraph<T> node){
        return this.source().equals(node) || this.destination().equals(node);
    }
    
    public NodeGraph<T> opposite(NodeGraph<T> node){
        return this.source().equals(node)?this.destination():this.source();
    }
    
    public NodeGraph<T> source(){
        return this.source;
    }
    
    public NodeGraph<T> destination(){
        return this.destination;
    }
    
    public boolean directed(boolean directed){
        return this.directed=directed;
    }
    
    public boolean directed(){
        return this.directed;
    }
    
}
