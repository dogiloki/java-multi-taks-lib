package com.dogiloki.multitaks.datastructure.graph;

import com.dogiloki.multitaks.datastructure.AbstractNode;
import com.dogiloki.multitaks.datastructure.graph.callbacks.OnWeight;
import com.dogiloki.multitaks.datastructure.graph.dijkstra.DijkstraAlgorithm;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dogi_
 */

public class Graph<T> extends AbstractNode<NodeGraph,T>{
    
    private Map<T,NodeGraph<T>> nodes=new HashMap<>();
    private NodesGraph<T> vertices=new NodesGraph();
    private Edges<T> edges=new Edges();
    
    public Graph(){
        
    }
    
    @Override
    public NodeGraph newNode(T value){
        return new NodeGraph(value);
    }
    
    public void addNode(T value){
        NodeGraph<T> node=this.newNode(value);
        this.nodes.put(value,node);
        this.vertices.add(node);
    }
    
    public NodeGraph<T> getNode(T value){
        return this.nodes.get(value);
    }
    
    public void add(T value){
        this.addNode(value);
    }
    
    public void oneWayEdge(T val1, T val2){
        this._oneWayEdge(val1,val2,null);
    }
    
    public void oneWayEdge(T val1, T val2, OnWeight<T> weight){
        this._oneWayEdge(val1,val2,weight);
    }
    
    public void oneWayEdge(T val1, T val2, double weight){
        this._oneWayEdge(val1,val2,(edge)->weight);
    }
    
    public void bothWaysEdge(T val1, T val2){
        this._bothWaysEdge(val1,val2,null);
    }
    
    public void bothWaysEdge(T val1, T val2, OnWeight<T> weight){
        this._bothWaysEdge(val1,val2,weight);
    }
    
    public void bothWaysEdge(T val1, T val2, double weight){
        this._bothWaysEdge(val1,val2,(edge)->weight);
    }
    
    private void _oneWayEdge(T val1, T val2, OnWeight<T> weight){
        Edge<T> edge=new Edge(this.getNode(val1),this.getNode(val2));
        edge.onWeight(weight);
        edge.directed(weight!=null);
        this.edges().add(edge);
    }
    
    private void _bothWaysEdge(T val1, T val2, OnWeight<T> weight){
        this.oneWayEdge(val1,val2,weight);
        this.oneWayEdge(val2,val1,weight);
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
        NodeGraph<T> node=this.getNode(value);
        ListAdjacency<T> adjacents=new ListAdjacency();
        edge_loop:for(Edge<T> edge:this.edges()){
            if(edge.source().equals(node) && edge.directed()){
                NodeGraph<T> opposite_node=edge.opposite(node);
                for(NodeGraph<T> exception_node:exception_nodes){
                    if(opposite_node.equals(exception_node)){
                        continue edge_loop;
                    }
                }
                adjacents.add(edge);
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
    
    public DijkstraAlgorithm<T> dijkstra(){
        return new DijkstraAlgorithm(this);
    }
    
}
