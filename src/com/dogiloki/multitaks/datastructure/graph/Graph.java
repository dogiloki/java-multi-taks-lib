package com.dogiloki.multitaks.datastructure.graph;

import com.dogiloki.multitaks.datastructure.graph.dijkstra.TableWeight;
import com.dogiloki.multitaks.datastructure.AbstractNode;
import com.dogiloki.multitaks.datastructure.graph.callbacks.OnWeight;

/**
 *
 * @author dogi_
 */

public class Graph<T> extends AbstractNode<NodeGraph,T>{
    
    private NodesGraph<T> vertices=new NodesGraph();
    private Edges<T> edges=new Edges();
    
    public Graph(){
        super(NodeGraph.class);
    }
    
    public void add(T value){
        this.vertices().add(this.newNode(value));
    }
    
    public void unidirectionalEdge(T val1, T val2, OnWeight<T> weight, boolean directed){
        Edge<T> edge=new Edge(this.newNode(val1),this.newNode(val2));
        edge.onWeight(weight);
        edge.directed(directed);
        this.edges().add(edge);
    }
    
    public void bidirectionalEdge(T val1, T val2, OnWeight<T> weight, boolean directed){
        this.unidirectionalEdge(val1,val2,weight,directed);
        this.unidirectionalEdge(val2,val1,weight,directed);
    }
    
    public ListAdjacency<T> adjacents(T value){
        return this._adjacents(value,null);
    }
    
    private ListAdjacency<T> _adjacents(T value, NodesGraph<T> exception_nodes){
        NodeGraph<T> node=this.newNode(value);
        ListAdjacency<T> adjacents=new ListAdjacency();
        edge_loop:for(Edge<T> edge:this.edges()){
            if(edge.source().equals(node) && edge.directed()){
                NodeGraph<T> opposite_node=edge.opposite(node);
                for(NodeGraph<T> exception_node:exception_nodes){
                    if(opposite_node.equals(exception_node)){
                        continue edge_loop;
                    }
                }
                adjacents.add(opposite_node);
            }
        }
        return adjacents;
    }
    
    public NodesGraph<T> vertices(){
        return this.vertices;
    }
    
    public Edges<T> edges(){
        return this.edges;
    }
    
}
