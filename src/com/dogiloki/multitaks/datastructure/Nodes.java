package com.dogiloki.multitaks.datastructure;

import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author dogi_
 */

public class Nodes<T extends Node<N>,N> extends Stack<T>{
    
    public Nodes(){
        
    }
    
    public Iterable<N> values(){
        return new Iterable<N>(){
            @Override
            public Iterator<N> iterator(){
                return new Iterator<N>(){
                    private int index=size()-1;
                    @Override
                    public boolean hasNext(){
                        return index>=0;
                    }
                    @Override
                    public N next(){
                        if(hasNext()){
                            return get(index--).getValue();
                        }else{
                            throw new java.util.NoSuchElementException();
                        }
                    }
                };
            }
        };
    }
    
}
