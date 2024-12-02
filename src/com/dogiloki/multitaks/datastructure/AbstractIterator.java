package com.dogiloki.multitaks.datastructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author _dogi
 */

public abstract class AbstractIterator<T> implements Iterator<T>{
    
    protected List<T> items;
    protected int index=0;
    
    public AbstractIterator(){
        this.items(new ArrayList<>());
    }
    
    public AbstractIterator(List<T> items){
        this.items(items);
    }
    
    public void reset(){
        this.index=0;
    }
    
    public boolean hasNext(){
        return this.index<this.items.size();
    }
    
    public T next(){
        if(!this.hasNext()){
            throw new NoSuchElementException();
        }
        return this.items.get(this.index++);
    }
    
    protected void items(List<T> items){
        this.items=items;
    }
    
}
