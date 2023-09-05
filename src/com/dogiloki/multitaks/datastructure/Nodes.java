package com.dogiloki.multitaks.datastructure;

import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author dogi_
 * @param <T> Typed node
 * @param <N> Node data typing
 */

public class Nodes<T extends Node<N>,N> extends Stack<T>{
    
    public Nodes(){
        
    }
    
    public Iterable<N> values(){
        return ()->new Iterator<N>(){
            private int index=0;
            @Override
            public boolean hasNext(){
                return index<size();
            }
            @Override
            public N next(){
                if(hasNext()){
                    return get(index++).getValue();
                }else{
                    throw new java.util.NoSuchElementException();
                }
            }
        };
    }
    
}
