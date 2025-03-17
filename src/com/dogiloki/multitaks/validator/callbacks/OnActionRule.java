package com.dogiloki.multitaks.validator.callbacks;

import com.dogiloki.multitaks.validator.MapValues;

/**
 *
 * @author _dogi
 */

public interface OnActionRule{
   
    public boolean run(Object key, Object value, MapValues values, Object[] params);
    
}
