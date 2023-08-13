package multitaks.graph;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dogi_
 */

public class Graph{
    
    private List<Node> nodes=new ArrayList<>();
    private List<Edge> edges=new ArrayList<>();
    
    public Graph(){
        
    }
    
    public List<Node> getNodes(){
        return this.nodes;
    }
    
    public List<Edge> getEdges(){
        return this.edges;
    }
    
    public void addNode(Node node){
        this.nodes.add(node);
    }
    
    public void linkNodes(Node node1, Node node2, int weight, boolean bidirectional){
        this._linkNodes(node1,node2,weight,bidirectional);
    }
    public void linkNodes(Node node1, Node node2, int weight){
        this._linkNodes(node1,node2,weight,Edge.DEFAULT_BIDIRECTIONAL);
    }
    private void _linkNodes(Node node1, Node node2, int weight, boolean bidirectional){
        this.edges.add(new Edge(node1,node2,weight,bidirectional));
        node1.addNode(node2);
        if(bidirectional){
            node2.addNode(node2);
        }
    }
    
    public List<Node> getAdjacentNodes(Node node){
        return node.getNodes();
    }
    
    public List<Table> generateTable(Node initial_node, List<Node> exception_nodes, List<Table> tables){
        if(initial_node==null){
            initial_node=this.nodes.get(0);
        }
        if(exception_nodes==null){
            exception_nodes=new ArrayList<>();
        }
        if(tables==null){
            tables=new ArrayList<>();
            for(Node node:this.nodes){
                node.setWeight(0);
                if(node.getValue()!=initial_node.getValue()){
                    Table table=new Table();
                    table.setVertexNode(node);
                    table.setFinalWeight(node.getValue()==initial_node.getValue()?0:-1);
                    table.setTemporaryWeight(table.getFinalWeight());
                    tables.add(table);
                }
            }
            Table table=new Table();
            table.setVertexNode(initial_node);
            table.setFinalWeight(0);
            table.setTemporaryWeight(0);
            tables.add(0,table);
        }
        if(this.finished()){
            return tables;
        }
        List<Node> adjacent_nodes=initial_node.getAdjacentNodes(exception_nodes);
        if(adjacent_nodes!=null){
           int adjacent_node_index=0;
           for(Node adjacent_node:adjacent_nodes){
               for(Table table:tables){
                   //adjacent_node
               }
               adjacent_node_index++;
           } 
        }
        return null;
    }
    
    private boolean finished(){
        return false;
    }
    
}
