package com.dogiloki.multitaks.datastructure.graph;

import com.dogiloki.multitaks.datastructure.graph.callbacks.OnWeight;
import com.dogiloki.multitaks.datastructure.Node;
import com.dogiloki.multitaks.datastructure.Nodes;

/**
 *
 * @author dogi_
 */

public class Graph<T>{
    
    private Nodes<Node<T>> vertices=new Nodes();
    private Edges<T> edges=new Edges();
    
    public Graph(){
        
    }
    
    public void add(T value){
        Node node=new Node(value);
        this.vertices().add(node);
    }
    
    public void unidirectionalEdge(T val1, T val2, OnWeight<T> weight, boolean directed){
        Edge<T> edge=new Edge(new Node(val1),new Node(val2));
        edge.onWeight(weight);
        edge.directed(directed);
        this.edges.add(edge);
    }
    
    public void bidirectionalEdge(T val1, T val2, OnWeight<T> weight, boolean directed){
        this.unidirectionalEdge(val1,val2,weight,directed);
        this.unidirectionalEdge(val2,val1,weight,directed);
    }
    
    public ListAdjacency<T> adjacents(T value){
        Node<T> node=new Node(value);
        ListAdjacency<T> adjacents=new ListAdjacency();
        for(Edge<T> edge:this.edges()){
            if(edge.source().equals(node)){
                adjacents.add(edge.opposite(node));
            }
        }
        return adjacents;
    }
    
    public Nodes<Node<T>> vertices(){
        return this.vertices;
    }
    
    public Edges<T> edges(){
        return this.edges;
    }
    
}
