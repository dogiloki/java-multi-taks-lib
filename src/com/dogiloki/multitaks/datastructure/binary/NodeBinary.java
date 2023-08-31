package com.dogiloki.multitaks.datastructure.binary;

import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.datastructure.Node;
import com.dogiloki.multitaks.datastructure.callbacks.WhenEvaluate;

/**
 *
 * @author dogi_
 */

public final class NodeBinary<T> extends Node<T>{
    
    public static final int LEFT_INDEX=0;
    public static final int RIGHT_INDEX=1;
    
    public WhenEvaluate<T> when_evaluate=(item)->item;
    
    public NodeBinary(T value){
        super(value);
        this.nodes.add(null);
        this.nodes.add(null);
    }

    @Override
    public void add(Node<T> node){
        if(Function.compareTo(this.when_evaluate.run(node.getValue()),this.when_evaluate.run(this.getValue()))<0){
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

    @Override
    public void remove(Node node){
        
    }
    
    public NodeBinary leftNode(){
        return (NodeBinary)this.nodes.get(NodeBinary.LEFT_INDEX);
    }
    
    public void leftNode(Node node){
        this.nodes.set(NodeBinary.LEFT_INDEX,node);
    }
    
    public NodeBinary rightNode(){
        return (NodeBinary)this.nodes.get(NodeBinary.RIGHT_INDEX);
    }
    
    public void rightNode(Node node){
        this.nodes.set(NodeBinary.RIGHT_INDEX,node);
    }
    
}
