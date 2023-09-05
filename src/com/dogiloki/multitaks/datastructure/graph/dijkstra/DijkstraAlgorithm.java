package com.dogiloki.multitaks.datastructure.graph.dijkstra;

import com.dogiloki.multitaks.datastructure.graph.Edge;
import com.dogiloki.multitaks.datastructure.graph.Graph;
import com.dogiloki.multitaks.datastructure.graph.ListAdjacency;
import com.dogiloki.multitaks.datastructure.graph.NodeGraph;
import com.dogiloki.multitaks.datastructure.graph.NodesGraph;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author dogi_
 */

public class DijkstraAlgorithm<T>{
    
    private TableWeight table=new TableWeight();
    private Graph<T> graph;
    private int index=0;
    
    public DijkstraAlgorithm(Graph<T> graph){
        this.graph=graph;
    }
    
    private void generateTable(NodeGraph<T> vertex, NodeGraph<T> end_vertex, NodesGraph<T> exception_nodes){
        ListAdjacency<T> adjacents=this.graph().adjacents(vertex.getValue(),exception_nodes);
        if(adjacents.isEmpty()){
            exception_nodes.add(vertex);
            return;
        }
        int row_vertex=this.table().getIndexVertex(vertex);
        adjacents.forEach((edge)->{
            NodeGraph<T> adjacent=edge.destination();
            int row_adjacent=this.table().getIndexVertex(adjacent);
            double temp_weight=this.table().tempWeight(row_adjacent);
            double temp_weight_new=this.table().finalWeight(row_vertex)+edge.weight().doubleValue();
            if(temp_weight_new<temp_weight || temp_weight==0){
                this.table().tempWeight(row_adjacent,temp_weight_new);
            }
            adjacent.weight(this.table().tempWeight(row_adjacent));
        });
        NodeGraph<T> node_minor=this.minorWeight(this.graph().vertices(),exception_nodes);
        int row_node_minor=this.table().getIndexVertex(node_minor);
        this.table().finalWeight(row_node_minor,this.table().tempWeight(row_node_minor));
        exception_nodes.add(vertex);
        this.generateTable(node_minor,end_vertex,exception_nodes);
    }
    
    private NodeGraph<T> minorWeight(NodesGraph<T> vertices, NodesGraph<T> exception_nodes){
        NodeGraph<T> node=null;
        vertices_loop:for(NodeGraph<T> vertex:vertices){
            for(NodeGraph<T> exception_node:exception_nodes){
                if(vertex.equals(exception_node)){
                    continue vertices_loop;
                }
            }
            NodeGraph node_aux=vertex;
            double weight_aux=vertex.weight();
            if(node==null){
                node=node_aux;
                continue;
            }
            if(weight_aux!=0 && weight_aux<node.weight() || node.weight()==0){
                node=node_aux;
            }
        }
        return node;
    }
    
    private NodesGraph<T> generateShortest(NodeGraph<T> vertex, NodeGraph<T> final_vertex, NodesGraph<T> exception_nodes, NodesGraph<T> nodes){
        ListAdjacency<T> adjacents=this.graph().adjacents(vertex.getValue(),exception_nodes);
        int row_vertex=this.table().getIndexVertex(vertex);
        if(this.table().finalWeight(row_vertex)<=0){
            this.table().finalWeight(row_vertex,this.table().tempWeight(row_vertex));
        }
        if(adjacents.isEmpty()){
            return nodes;
        }
        NodeGraph<T> adjacent=null;
        for(Edge<T> edge:adjacents){
            adjacent=edge.destination();
            int row_adjacent=this.table().getIndexVertex(adjacent);
            double weight=this.table().finalWeight(row_vertex)-edge.weight().doubleValue();
            if(weight==this.table().finalWeight(row_adjacent)){
                nodes.add(adjacent);
                exception_nodes.add(vertex);
                break;
            }
        }
        if(!final_vertex.equals(adjacent)){
            this.generateShortest(adjacent,final_vertex,exception_nodes,nodes);
        }
        return nodes;
    }
    
    public NodesGraph<T> shortest(T start_value, T end_value){
        NodeGraph<T> start_node=this.graph().getNode(start_value);
        NodeGraph<T> end_node=this.graph().getNode(end_value);
        this.table().addRow(start_node,0,0);
        this.graph().vertices().forEach((vertice)->{
            if(!vertice.equals(start_node) && !vertice.equals(end_node)){
                this.table().addRow(vertice,0,0);
            }
        });
        this.table().addRow(end_node,0,0);
        this.generateTable(start_node,end_node,new NodesGraph());
        NodesGraph<T> nodes=new NodesGraph();
        nodes.add(end_node);
        return this.generateShortest(end_node,start_node,new NodesGraph(),nodes);
    }
    
    public TableWeight<T> table(){
        return this.table;
    }
    
    public Graph<T> graph(){
        return this.graph;
    }
    
}
