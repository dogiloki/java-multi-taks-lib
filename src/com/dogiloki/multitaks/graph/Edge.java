package com.dogiloki.multitaks.graph;

/**
 *
 * @author dogi_
 */

public class Edge{
    
    public static boolean DEFAULT_BIDIRECTIONAL=true;
    
    private int weight;
    private Node node1;
    private Node node2;
    private boolean bidirectional;
    
    public Edge(Node node1, Node node2, int weight, boolean bidirectional){
        this.node1=node1;
        this.node2=node2;
        this.weight=weight;
        this.bidirectional=bidirectional;
    }
    
    public Edge(Node node1, Node node2, int weight){
        this.node1=node1;
        this.node2=node2;
        this.weight=weight;
        this.bidirectional=Edge.DEFAULT_BIDIRECTIONAL;
    }
    
    public Edge(Node node1, Node node2, boolean bidirectional){
        this.node1=node1;
        this.node2=node2;
        this.weight=0;
        this.bidirectional=bidirectional;
    }
    
    public Edge(Node node1, Node node2){
        this.node1=node1;
        this.node2=node2;
        this.weight=0;
        this.bidirectional=Edge.DEFAULT_BIDIRECTIONAL;
    }
    
}
