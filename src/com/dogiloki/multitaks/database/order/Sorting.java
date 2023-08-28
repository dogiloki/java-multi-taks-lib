package com.dogiloki.multitaks.database.order;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dogi_
 */

public abstract class Sorting<T> extends SortingAlgorithm{
    
    public interface orderWith<T>{
        public Object run(T item);
    }
    
    private List<T> items=new ArrayList<>();
    private orderWith<T> order_with=(item)->item;
    
    public Sorting(){
        
    }
    
    public abstract List<T> sort();
    
    public orderWith<T> orderWith(){
        return this.order_with;
    }
    
    public Sorting orderWith(orderWith<T> action){
        this.order_with=action;
        return this;
    }
    
    public List<T> items(){
        return this.items;
    }
    
    public Sorting items(List<T> items){
        this.items=items;
        return this;
    }
    
}
