package com.dogiloki.multitaks.datastructure.binary;

import com.dogiloki.multitaks.datastructure.Nodes;
import com.dogiloki.multitaks.datastructure.callbacks.OnEvaluate;
import com.dogiloki.multitaks.datastructure.callbacks.OnEvaluateNotReturn;

/**
 *
 * @author dogi_
 */

public class TreeBinary<T>{
    
    private NodeBinary root_node=null;
    public Nodes<NodeBinary<T>> nodes=new Nodes();
    public OnEvaluate<T> on_saving=(item)->item;
    public OnEvaluate<T> on_evaluate=(item)->item;
    public OnEvaluateNotReturn<T> on_orden=(item)->{};
    
    public TreeBinary(){
        
    }
    
    public void add(T value){
        NodeBinary<T> node=new NodeBinary(this.on_saving.run(value));
        node.on_evaluate=this.on_evaluate;
        if(this.root_node==null){
            this.root_node=node;
        }else{
            this.root_node.on_evaluate=this.on_evaluate;
            this.root_node.add(node);
        }
    }
    
    public void postOrden(){
        this.postOrden(this.root_node);
    }
    
    private void postOrden(NodeBinary<T> node){
        if(node==null){
            return;
        }
        this.postOrden(node.leftNode());
        this.postOrden(node.rightNode());
        this.nodes.add(node);
        this.on_orden.run(node.getValue());
    }
    
    public void preOrden(){
        this.preOrden(this.root_node);
    }
    
    private void preOrden(NodeBinary<T> node){
        if(node==null){
            return;
        }
        this.nodes.add(node);
        this.on_orden.run(node.getValue());
        this.preOrden(node.leftNode());
        this.preOrden(node.rightNode());
    }
    
    public void inOrden(){
        this.inOrden(this.root_node);
    }
    
    private void inOrden(NodeBinary<T> node){
        if(node==null){
            return;
        }
        this.inOrden(node.leftNode());
        this.nodes.add(node);
        this.on_orden.run(node.getValue());
        this.inOrden(node.rightNode());
    }
    
}
