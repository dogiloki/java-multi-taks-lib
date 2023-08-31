package com.dogiloki.multitaks.datastructure.binary;

import com.dogiloki.multitaks.datastructure.Nodes;
import com.dogiloki.multitaks.datastructure.callbacks.WhenEvaluate;

/**
 *
 * @author dogi_
 */

public class TreeBinary<T>{
    
    private NodeBinary root_node=null;
    public Nodes<NodeBinary<T>> nodes=new Nodes();
    public WhenEvaluate<T> when_saving=(item)->item;
    public WhenEvaluate<T> when_evaluate=(item)->item;
    
    public TreeBinary(){
        
    }
    
    public void add(T value){
        NodeBinary<T> node=new NodeBinary(this.when_saving.run(value));
        node.when_evaluate=this.when_evaluate;
        if(this.root_node==null){
            this.root_node=node;
        }else{
            this.root_node.when_evaluate=this.when_evaluate;
            this.root_node.add(node);
        }
    }
    
    public void postOrden(){
        this.postOrden(this.root_node);
    }
    
    private void postOrden(NodeBinary node){
        if(node==null){
            return;
        }
        this.postOrden(node.leftNode());
        this.postOrden(node.rightNode());
        this.nodes.add(node);
    }
    
    public void preOrden(){
        this.preOrden(this.root_node);
    }
    
    private void preOrden(NodeBinary node){
        if(node==null){
            return;
        }
        this.nodes.add(node);
        this.preOrden(node.leftNode());
        this.preOrden(node.rightNode());
    }
    
    public void inOrden(){
        this.inOrden(this.root_node);
    }
    
    private void inOrden(NodeBinary node){
        if(node==null){
            return;
        }
        this.inOrden(node.leftNode());
        this.nodes.add(node);
        this.inOrden(node.rightNode());
    }
    
}
