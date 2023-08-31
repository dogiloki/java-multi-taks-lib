package com.dogiloki.multitaks.datastructure;

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
    
    public abstract void add(Node<T> node);
    public abstract void remove(Node<T> node);
    
    public void setValue(T value){
        this.value=value;
    }
    
    public T getValue(){
        return this.value;
    }
    
}
