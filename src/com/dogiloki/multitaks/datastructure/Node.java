package com.dogiloki.multitaks.datastructure;

import com.dogiloki.multitaks.datastructure.callbacks.WhenEvaluate;

/**
 *
 * @author dogi_
 */

public abstract class Node<T>{

    private T value;
    protected Nodes<Node<T>> nodes=new Nodes();
    
    public Node(T value){
        this.value=value;
    }
    
    public abstract void add(Node node);
    public abstract void remove(Node node);
    
    public void setValue(T value){
        this.value=value;
    }
    
    public T getValue(){
        return this.value;
    }
    
}
