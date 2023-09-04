package com.dogiloki.multitaks.datastructure.graph.dijkstra;

import com.dogiloki.multitaks.datastructure.graph.NodeGraph;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dogi_
 */

public class TableWeight<T> extends DefaultTableModel{
    
    private static int COLUMN_VERTICE=0;
    private static int COLUMN_FINAL_WEIGHT=1;
    private static int COLUMN_TEMP_WEIGHT=2;
    
    private int index=0;
    private Map<NodeGraph<T>,Integer> vertex_index=new HashMap<>();
    
    public TableWeight(){
        this.addColumn("Vertex");
        this.addColumn("Final weight");
        this.addColumn("Temporary weight");
    }
    
    public int getIndexVertice(NodeGraph<T> vertex){
        return this.vertex_index.get(vertex);
    }
    
    public void addRow(NodeGraph<T> vertex, double final_weight, double temp_weight){
        this.addRow(new Object[]{vertex,final_weight,temp_weight});
        this.vertex_index.put(vertex,this.index);
        this.index++;
    }
    
    public NodeGraph<T> vertice(int row){
        return (NodeGraph<T>)this.getValueAt(row,COLUMN_VERTICE);
    }
    
    public double finalWeight(int row){
        return (double)this.getValueAt(row,COLUMN_FINAL_WEIGHT);
    }
    
    public double tempWeight(int row){
        return (double)this.getValueAt(row,COLUMN_TEMP_WEIGHT);
    }
    
    public void vertice(int row, NodeGraph<T> vertex){
        this.vertex_index.put(vertex,row);
        this.setValueAt(vertex,row,COLUMN_VERTICE);
    }
    
    public void finalWeight(int row, double weight){
        this.setValueAt(weight,row,COLUMN_FINAL_WEIGHT);
    }
    
    public void tempWeight(int row, double weight){
        this.setValueAt(weight,row,COLUMN_TEMP_WEIGHT);
    }
    
}
