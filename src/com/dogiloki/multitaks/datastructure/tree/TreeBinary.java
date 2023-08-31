package com.dogiloki.multitaks.datastructure.tree;

import com.dogiloki.multitaks.datastructure.Nodes;
import com.dogiloki.multitaks.datastructure.callbacks.OnEvaluate;
import com.dogiloki.multitaks.datastructure.callbacks.OnEvaluateNotReturn;
import com.dogiloki.multitaks.datastructure.tree.enums.TraversalType;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author dogi_
 */

public class TreeBinary<T> implements Iterator<T>{
    
    private NodeBinary<T> root_node=null;
    private TraversalType traversal_type;
    public Nodes<NodeBinary<T>> nodes=new Nodes();
    public OnEvaluate<T> on_saving=(item)->item;
    public OnEvaluate<T> on_evaluate=(item)->item;
    public OnEvaluateNotReturn<T> on_order=(item)->{};
    private NodeBinary<T> current=null;
    private int index=0;
    
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
        this.on_order.run(node.getValue());
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
        this.on_order.run(node.getValue());
        this.inOrden(node.leftNode());
        this.inOrden(node.rightNode());
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
        this.inOrden(node.leftNode());
        this.inOrden(node.rightNode());
        this.nodes.add(node);
        this.on_order.run(node.getValue());
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
    
}
