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
    
}
