package multitaks.graph;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dogi_
 */

public class Node{

    private Object value;
    private Object alias;
    private int weight;
    private List<Node> nodes=new ArrayList<>();
    
    public Node(Object value, Object alias){
        this.value=value;
        this.alias=alias;
    }
    
    public Node(Object value){
        this.value=value;
        this.alias=value;
    }
    
    public Object getValue(){
        return this.value;
    }
    
    public int getWeight(){
        return this.weight;
    }
    
    public List<Node> getNodes(){
        return this.nodes;
    }
    
    public List<Node> getAdjacentNodes(List<Node> exception_nodes){
        if(this.nodes==null){
            return null;
        }
        List<Node> adjacent_nodes=new ArrayList<>();
        this.nodes.forEach((adjacent_node)->{
            for(Node exception_node:exception_nodes){
                if(exception_node.getValue()==exception_node.getValue()){
                    continue;
                }
                adjacent_nodes.add(adjacent_node);
            }
        });
        return adjacent_nodes;
    }
    
    public void addNode(Node node){
        this.nodes.add(node);
    }
    
    public void removeNode(Node node){
        this.nodes.remove(node);
    }
    
    public void setWeight(int weight){
        this.weight=weight;
    }
    
    public void setValue(Object value){
        this.value=value;
    }
    
}
