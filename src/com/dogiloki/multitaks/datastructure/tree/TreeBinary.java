package com.dogiloki.multitaks.datastructure.tree;

import com.dogiloki.multitaks.datastructure.Nodes;
import com.dogiloki.multitaks.datastructure.callbacks.OnEvaluate;
import com.dogiloki.multitaks.datastructure.callbacks.OnEvaluateNotReturn;
import com.dogiloki.multitaks.datastructure.tree.enums.TraversalType;
import java.util.Iterator;

/**
 *
 * @author dogi_
 */

public class TreeBinary<T> implements Iterator<T>{
    
    private NodeBinary<T> root_node=null;
    private TraversalType traversal_type;
    private Nodes<NodeBinary<T>> nodes=new Nodes();
    private OnEvaluate<T> on_saving=(item)->item;
    private OnEvaluate<T> on_evaluate=(item)->item;
    private OnEvaluateNotReturn<T> on_order=(item)->{};
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
    
    public TreeBinary inOrden(){
        this.index=0;
        this.current=this.root_node;
        this.traversal_type=TraversalType.IN_ORDER;
        this.inOrden(this.root_node);
        return this;
    }
    
    public void inOrden(NodeBinary<T> node){
        if(node==null){
            return;
        }
        this.inOrden(node.leftNode());
        this.nodes.add(node);
        this.onOrder().run(node.getValue());
        this.inOrden(node.rightNode());
    }
    
    public TreeBinary preOrden(){
        this.index=0;
        this.current=this.root_node;
        this.traversal_type=TraversalType.PRE_ONDER;
        this.preOrden(this.root_node);
        return this;
    }
    
    public void preOrden(NodeBinary<T> node){
        if(node==null){
            return;
        }
        this.nodes.add(node);
        this.onOrder().run(node.getValue());
        this.preOrden(node.leftNode());
        this.preOrden(node.rightNode());
    }
    
    public TreeBinary postOrden(){
        this.index=0;
        this.current=this.root_node;
        this.traversal_type=TraversalType.POST_ORDER;
        this.postOrden(this.root_node);
        return this;
    }
    
    public void postOrden(NodeBinary<T> node){
        if(node==null){
            return;
        }
        this.postOrden(node.leftNode());
        this.postOrden(node.rightNode());
        this.nodes.add(node);
        this.onOrder().run(node.getValue());
    }
    
    public TreeBinary inOrdenReverse(){
        this.index=0;
        this.current=this.root_node;
        this.traversal_type=TraversalType.IN_ORDER_REVERSE;
        this.inOrdenReverse(this.root_node);
        return this;
    }
    
    public void inOrdenReverse(NodeBinary<T> node){
        if(node==null){
            return;
        }
        this.inOrdenReverse(node.rightNode());
        this.nodes.add(node);
        this.onOrder().run(node.getValue());
        this.inOrdenReverse(node.leftNode());
    }
    
    @Override
    public boolean hasNext(){
        return this.index<this.nodes.size();
    }
    
    @Override
    public T next(){
        this.current=this.nodes.get(this.index);
        this.index++;
        return this.current.getValue();
    }
    
    public Nodes<NodeBinary<T>> nodes(){
        return this.nodes;
    }
    
    public TreeBinary onSaving(OnEvaluate<T> action){
        this.on_saving=action;
        return this;
    }
    
    public OnEvaluate onSaving(){
        return this.on_saving;
    }
    
    public TreeBinary onEvaluate(OnEvaluate<T> action){
        this.on_evaluate=action;
        return this;
    }
    
    public OnEvaluate onEvaluate(){
        return this.on_evaluate;
    }
    
    public TreeBinary onOrder(OnEvaluateNotReturn<T> action){
        this.on_order=action;
        return this;
    }
    
    public OnEvaluateNotReturn onOrder(){
        return this.on_order;
    }
    
}
