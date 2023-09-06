package com.dogiloki.multitaks.callbacks;

/**
 *
 * @author dogi_
 */

public interface OnEvaluateWithResult<T,N>{
    
    public N run(T item);
    
}
