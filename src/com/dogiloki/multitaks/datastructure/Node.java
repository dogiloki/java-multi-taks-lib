package com.dogiloki.multitaks.datastructure;

import com.dogiloki.multitaks.Function;

/**
 *
 * @author dogi_
 */

public class Node<T>{

    private T value;
    protected Nodes<Node<T>> nodes=new Nodes();
    
    public Node(T value){
        this.value=value;
    }
    
    public void add(Node<T> node){
        this.nodes.add(node);
    }
    
    public void remove(Node<T> node){
        this.nodes.remove(node);
    }
    
    public void clear(){
        this.nodes.clear();
    }
    
    public boolean equals(Node<T> node){
        return Function.compareTo(this.getValue(),node.getValue())==0;
    }
    
    public void setValue(T value){
        this.value=value;
    }
    
    public T getValue(){
        return this.value;
    }
    
}
