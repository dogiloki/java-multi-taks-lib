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
        this.graph().vertices().forEach((vertice)->{
            this.table().addRow(vertice,0,0);
        });
    }
    
    private void generateTable(NodeGraph<T> vertex, NodesGraph<T> exception_nodes){
        ListAdjacency<T> adjacents=this.graph().adjacents(vertex.getValue(),exception_nodes);
        if(adjacents.isEmpty()){
            return;
        }
        Map<NodeGraph<T>,Double> adjacents_temp_weight=new HashMap();
        int row_vertex=this.table().getIndexVertice(vertex);
        adjacents.forEach((edge)->{
            NodeGraph<T> adjacent=edge.destination();
            int row_adjacent=this.table().getIndexVertice(adjacent);
            double temp_weight=this.table().tempWeight(row_adjacent);
            double temp_weight_new=this.table().finalWeight(row_vertex)+edge.weight().doubleValue();
            if(temp_weight_new<temp_weight || temp_weight==0){
                this.table().tempWeight(row_adjacent,temp_weight_new);
            }
            adjacents_temp_weight.put(adjacent,this.table().tempWeight(row_adjacent));
        });
        NodeGraph<T> node_minor=this.minorWeight(adjacents_temp_weight);
        int row_node_minor=this.table().getIndexVertice(node_minor);
        this.table().finalWeight(row_node_minor,this.table().tempWeight(row_node_minor));
        exception_nodes.add(vertex);
        this.generateTable(node_minor,exception_nodes);
    }
    
    private NodeGraph<T> minorWeight(Map<NodeGraph<T>,Double> vertices){
        NodeGraph<T> node=null;
        for(Entry<NodeGraph<T>,Double> entry:vertices.entrySet()){
            NodeGraph node_aux=entry.getKey();
            double weight_aux=entry.getValue();
            if(node==null){
                node=node_aux;
                continue;
            }
            if(weight_aux<vertices.get(node)){
                node=node_aux;
            }
        }
        return node;
    }
    
    private NodesGraph<T> generateShortest(NodeGraph<T> vertex, NodeGraph<T> final_vertex, NodesGraph<T> exception_nodes, NodesGraph<T> nodes){
        ListAdjacency<T> adjacents=this.graph().adjacents(vertex.getValue(),exception_nodes);
        if(adjacents.isEmpty()){
            return nodes;
        }
        int row_vertex=this.table().getIndexVertice(vertex);
        NodeGraph<T> adjacent=null;
        for(Edge<T> edge:adjacents){
            adjacent=edge.destination();
            int row_adjacent=this.table().getIndexVertice(adjacent);
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
        this.generateTable(start_node,new NodesGraph());
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
