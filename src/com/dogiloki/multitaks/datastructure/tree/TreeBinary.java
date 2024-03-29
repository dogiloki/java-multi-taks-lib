package com.dogiloki.multitaks.datastructure.tree;

import com.dogiloki.multitaks.datastructure.Nodes;
import com.dogiloki.multitaks.datastructure.tree.enums.TraversalType;
import java.util.Iterator;
import com.dogiloki.multitaks.callbacks.OnCallback;
import com.dogiloki.multitaks.callbacks.OnCallbackNotReturn;
import java.util.List;

/**
 *
 * @author dogi_
 */

public class TreeBinary<T> implements Iterator<T>{
    
    private NodeBinary<T> root_node=null;
    private TraversalType traversal_type;
    private Nodes<NodeBinary<T>,T> nodes=new Nodes();
    private OnCallback<T> on_saving=(item)->item;
    private OnCallback<T> on_evaluate=(item)->item;
    private OnCallbackNotReturn<T> on_order=(item)->{};
    private NodeBinary<T> current=null;
    private int index=0;
    
    public TreeBinary(){
        
    }
    
    public void add(T value){
        NodeBinary<T> node=new NodeBinary(this.onSaving().run(value));
        node.onEvaluate(this.onEvaluate());
        if(this.root_node==null){
            this.root_node=node;
        }else{
            this.root_node.onEvaluate(this.onEvaluate()).add(node);
        }
    }
    
    public void addAll(List<T> values){
        for(T value:values){
            this.add(value);
        }
    }
    
    public TreeBinary order(TraversalType traversal_type){
        this.index=0;
        this.nodes().clear();
        this.current=this.root_node;
        this.traversal_type=traversal_type;
        this.order(this.rootNode());
        return this;
    }
    
    private void order(NodeBinary<T> node){
        if(node==null){
            return;
        }
        switch(this.traversal_type){
            case IN_ORDER:{
                this.order(node.leftNode());
                this.nodes().add(node);
                this.onOrder().run(node.getValue());
                this.order(node.rightNode());
                break;
            }
            case PRE_ONDER:{
                this.nodes().add(node);
                this.onOrder().run(node.getValue());
                this.order(node.leftNode());
                this.order(node.rightNode());
                break;
            }
            case POST_ORDER:{
                this.order(node.leftNode());
                this.order(node.rightNode());
                this.nodes().add(node);
                this.onOrder().run(node.getValue());
                break;
            }
            case IN_ORDER_REVERSE:{
                this.order(node.rightNode());
                this.nodes().add(node);
                this.onOrder().run(node.getValue());
                this.order(node.leftNode());
                break;
            }
        }
    }
    
    public TreeBinary inOrden(){
        this.order(TraversalType.IN_ORDER);
        return this;
    }
    
    public TreeBinary preOrden(){
        this.order(TraversalType.PRE_ONDER);
        return this;
    }
    
    public TreeBinary postOrden(){
        this.order(TraversalType.POST_ORDER);
        return this;
    }
    
    public TreeBinary inOrdenReverse(){
        this.order(TraversalType.IN_ORDER_REVERSE);
        return this;
    }
    
    @Override
    public boolean hasNext(){
        return this.index<this.nodes().size();
    }
    
    @Override
    public T next(){
        this.current=this.nodes().get(this.index);
        this.index++;
        return this.current.getValue();
    }
    
    public NodeBinary<T> rootNode(){
        return this.root_node;
    }
    
    public Nodes<NodeBinary<T>,T> nodes(){
        return this.nodes;
    }
    
    public TreeBinary onSaving(OnCallback<T> action){
        this.on_saving=action;
        return this;
    }
    
    public OnCallback onSaving(){
        return this.on_saving;
    }
    
    public TreeBinary onEvaluate(OnCallback<T> action){
        this.on_evaluate=action;
        return this;
    }
    
    public OnCallback onEvaluate(){
        return this.on_evaluate;
    }
    
    public TreeBinary onOrder(OnCallbackNotReturn<T> action){
        this.on_order=action;
        return this;
    }
    
    public OnCallbackNotReturn onOrder(){
        return this.on_order;
    }
    
}
