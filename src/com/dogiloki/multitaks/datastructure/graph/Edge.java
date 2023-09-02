package com.dogiloki.multitaks.datastructure.graph;

import com.dogiloki.multitaks.datastructure.Node;
import com.dogiloki.multitaks.datastructure.callbacks.OnEvaluate;

/**
 *
 * @author dogi_
 */

public class Edge<T>{
    
    private OnEvaluate<Edge<T>> weight=(edge)->null;
    private boolean directed=true;
    private Node<T> source;
    private Node<T> destination;
    
    
    public Edge(Node source, Node destination){
        this.source=source;
        this.destination=destination;
    }
    
    public void onWeight(OnEvaluate<Edge<T>> weight){
        this.weight=weight;
    }
    
    public Object weight(){
        return this.weight.run(this);
    }
    
    public boolean hasNode(Node<T> node){
        return this.source().equals(node) || this.destination().equals(node);
    }
    
    public Node<T> opposite(Node<T> node){
        return this.source().equals(node)?this.destination():this.source();
    }
    
    public Node<T> source(){
        return this.source;
    }
    
    public Node<T> destination(){
        return this.destination;
    }
    
    public boolean directed(boolean directed){
        return this.directed=directed;
    }
    
    public boolean directed(){
        return this.directed;
    }
    
}
