package com.dogiloki.multitaks.database.order;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dogi_
 */

public abstract class Sorting<T> extends SortingAlgorithm{
    
    private List<T> items=new ArrayList<>();
    
    public Sorting(){
        
    }
    
    public abstract List<T> sort();
    
    public List<T> items(){
        return this.items;
    }
    
    public Sorting items(List<T> items){
        this.items=items;
        return this;
    }
    
}
