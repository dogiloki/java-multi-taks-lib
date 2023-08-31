package com.dogiloki.multitaks.datastructure.callbacks;

/**
 *
 * @author dogi_
 */

public interface OnEvaluateWith<T,N>{
    
    public N run(T item);
    
}
