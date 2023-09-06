package com.dogiloki.multitaks.callbacks;

/**
 *
 * @author dogi_
 */

public interface OnCallbackWithResult<T,N>{
    
    public N run(T item);
    
}
