package com.dogiloki.multitaks.datastructure.graph;

/**
 *
 * @author dogi_
 */

public interface OnWeight<T>{

    public Object run(T source, T destination);
    
}
