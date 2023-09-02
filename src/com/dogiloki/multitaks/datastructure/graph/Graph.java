package com.dogiloki.multitaks.datastructure.graph;

import com.dogiloki.multitaks.datastructure.Node;
import com.dogiloki.multitaks.datastructure.Nodes;
import com.dogiloki.multitaks.datastructure.callbacks.OnEvaluate;
import java.util.List;

/**
 *
 * @author dogi_
 */

public class Graph<T>{
    
    private Nodes<Node<T>> vertices=new Nodes();
    private Edges edges=new Edges();
    
    public Graph(){
        
    }
    
    public void add(T value){
        Node node=new Node(value);
        this.vertices().add(node);
    }
    
    public void unidirectionalEdge(T val1, T val2, OnEvaluate<Edge<T>> weight, boolean directed){
        Edge<T> edge=new Edge(new Node(val1),new Node(val2));
        edge.onWeight(weight);
        edge.directed(directed);
        this.edges.add(edge);
    }
    
    public void bidirectionalEdge(T val1, T val2, OnEvaluate<Edge<T>> weight, boolean directed){
        this.unidirectionalEdge(val1,val2,weight,directed);
        this.unidirectionalEdge(val2,val1,weight,directed);
    }
    
    public ListNeigbors<T> neighbors(T value){
        Node<T> node=new Node(value);
        ListNeigbors<T> neighbors=new ListNeigbors();
        for(Edge<T> edge:this.edges()){
            System.out.println(edge.source().getValue()+" - "+edge.destination().getValue());
            if(edge.hasNode(node)){
                neighbors.add(edge.opposite(node).getValue());
            }
        }
        return neighbors;
    }
    
    public Nodes vertices(){
        return this.vertices;
    }
    
    public Edges edges(){
        return this.edges;
    }
    
}
