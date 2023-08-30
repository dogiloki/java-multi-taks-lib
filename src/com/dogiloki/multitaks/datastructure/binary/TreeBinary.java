package com.dogiloki.multitaks.datastructure.binary;

import com.dogiloki.multitaks.datastructure.Nodes;

/**
 *
 * @author dogi_
 */

public class TreeBinary<T extends NodeBinary,N>{
    
    private T root_node=null;
    public Nodes<NodeBinary> nodes=new Nodes();
    
    public TreeBinary(){
        
    }
    
    public void add(N value){
        T node=(T)new NodeBinary(value);
        if(this.root_node==null){
            this.root_node=node;
        }else{
            this.root_node.add(node);
        }
    }
    
    public void postOrden(){
        this.postOrden(this.root_node);
    }
    
    private void postOrden(T node){
        if(node==null){
            return;
        }
        this.postOrden((T)node.leftNode());
        this.postOrden((T)node.rightNode());
        this.nodes.add(node);
    }
    
    public void preOrden(){
        this.preOrden(this.root_node);
    }
    
    private void preOrden(T node){
        if(node==null){
            return;
        }
        this.nodes.add(node);
        this.preOrden((T)node.leftNode());
        this.preOrden((T)node.rightNode());
    }
    
    public void inOrden(){
        this.inOrden(this.root_node);
    }
    
    private void inOrden(T node){
        if(node==null){
            return;
        }
        this.inOrden((T)node.leftNode());
        this.nodes.add(node);
        this.inOrden((T)node.rightNode());
    }
    
}
