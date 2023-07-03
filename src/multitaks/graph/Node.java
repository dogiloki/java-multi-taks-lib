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
    private List<Node> nodes=new ArrayList<>();
    
    public Node(Object value, Object alias){
        this.value=value;
        this.alias=alias;
    }
    
    public Node(Object value){
        this.value=value;
        this.alias=value;
    }
    
    public List<Node> getNodes(){
        return this.nodes;
    }
    
    public void addNode(Node node){
        this.nodes.add(node);
    }
    
    public void removeNode(Node node){
        this.nodes.remove(node);
    }
    
}
