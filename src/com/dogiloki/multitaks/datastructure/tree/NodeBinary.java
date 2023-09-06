package com.dogiloki.multitaks.datastructure.tree;

import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.datastructure.Node;
import com.dogiloki.multitaks.callbacks.OnCallback;

/**
 *
 * @author dogi_
 */

public final class NodeBinary<T> extends Node<T>{
    
    public static final int LEFT_INDEX=0;
    public static final int RIGHT_INDEX=1;
    
    private OnCallback<T> on_evaluate=(item)->item;
    
    public NodeBinary(T value){
        super(value);
        this.nodes.add(null);
        this.nodes.add(null);
    }

    @Override
    public void add(Node<T> node){
        if(Function.compareTo(this.onEvaluate().run(node.getValue()),this.onEvaluate().run(this.getValue()))<0){
            if(this.leftNode()==null){
                this.leftNode(node);
            }else{
                this.leftNode().add(node);
            }
        }else{
            if(this.rightNode()==null){
                this.rightNode(node);
            }else{
                this.rightNode().add(node);
            }
        }
    }
    
    public NodeBinary<T> leftNode(){
        return (NodeBinary)this.nodes.get(NodeBinary.LEFT_INDEX);
    }
    
    public void leftNode(Node node){
        this.nodes.set(NodeBinary.LEFT_INDEX,node);
    }
    
    public NodeBinary<T> rightNode(){
        return (NodeBinary)this.nodes.get(NodeBinary.RIGHT_INDEX);
    }
    
    public void rightNode(Node node){
        this.nodes.set(NodeBinary.RIGHT_INDEX,node);
    }
    
    public NodeBinary onEvaluate(OnCallback<T> action){
        this.on_evaluate=action;
        return this;
    }
    
    public OnCallback onEvaluate(){
        return this.on_evaluate;
    }
    
}
