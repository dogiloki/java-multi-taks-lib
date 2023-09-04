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
    
    public TableWeight(){
        this.addColumn("Vertice");
        this.addColumn("Final weight");
        this.addColumn("Temporary weight");
    }
    
    public NodeGraph<T> vertice(int row){
        return (NodeGraph<T>)this.getValueAt(COLUMN_VERTICE,row);
    }
    
    public double finalWeight(int row){
        return (double)this.getValueAt(COLUMN_FINAL_WEIGHT,row);
    }
    
    public double tempWeight(int row){
        return (double)this.getValueAt(COLUMN_TEMP_WEIGHT,row);
    }
    
    public NodeGraph<T> vertice(int row, NodeGraph<T> vertice){
        this.setValueAt(this,COLUMN_VERTICE,COLUMN_VERTICE);
        return this.vertice(row);
    }
    
    public double finalWeight(int row, double weight){
        this.setValueAt(weight,COLUMN_FINAL_WEIGHT,row);
        return this.finalWeight(row);
    }
    
    public double tempWeight(int row, double weight){
        this.setValueAt(weight,COLUMN_TEMP_WEIGHT,row);
        return this.tempWeight(row);
    }
    
}
