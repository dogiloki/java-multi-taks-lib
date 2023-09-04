package com.dogiloki.multitaks.datastructure.graph;

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
        
    }
    
    @Override
    public NodeGraph newNode(T value){
        return new NodeGraph(value);
    }
    
    public void add(T value){
        this.vertices().put(value,this.newNode(value));
    }
    
    public void oneWayEdge(T val1, T val2, OnWeight<T> weight, boolean directed){
        Edge<T> edge=new Edge(this.vertices().get(val1),this.vertices().get(val2));
        edge.onWeight(weight);
        edge.directed(directed);
        this.edges().add(edge);
    }
    
    public void bothWaysEdge(T val1, T val2, OnWeight<T> weight, boolean directed){
        this.oneWayEdge(val1,val2,weight,directed);
        this.oneWayEdge(val2,val1,weight,directed);
    }
    
    public ListAdjacency<T> adjacents(T value){
        return this._adjacents(value,null);
    }
    
    public ListAdjacency<T> adjacents(T value, NodesGraph<T> exception_nodes){
        return this._adjacents(value,exception_nodes);
    }
    
    private ListAdjacency<T> _adjacents(T value, NodesGraph<T> exception_nodes){
        if(exception_nodes==null){
            exception_nodes=new NodesGraph();
        }
        NodeGraph<T> node=this.vertices().get(value);
        ListAdjacency<T> adjacents=new ListAdjacency();
        edge_loop:for(Edge<T> edge:this.edges()){
            if(edge.source().equals(node) && edge.directed()){
                NodeGraph<T> opposite_node=edge.opposite(node);
                for(NodeGraph<T> exception_node:exception_nodes.values()){
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
