package com.dogiloki.multitaks.graph;

/**
 *
 * @author dogi_
 */

public class Table{

    private Node vertex_node;
    private int final_weight;
    private int temporary_weight;
    
    public Table(){
        
    }
    
    public Node getVertexNode(){
        return this.vertex_node;
    }
    
    public int getFinalWeight(){
        return this.final_weight;
    }
    
    public int getTemporaly(){
        return this.temporary_weight;
    }
    
    public void setVertexNode(Node node){
        this.vertex_node=node;
    }
    
    public void setFinalWeight(int weight){
        this.final_weight=weight;
    }
    
    public void setTemporaryWeight(int weight){
        this.temporary_weight=weight;
    }
    
}
