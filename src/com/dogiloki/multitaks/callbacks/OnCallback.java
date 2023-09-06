package com.dogiloki.multitaks.callbacks;

/**
 *
 * @author dogi_
 */

public interface OnCallback<T>{
    
    public Object run(T items);
    
}
