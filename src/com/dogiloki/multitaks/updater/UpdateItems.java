package com.dogiloki.multitaks.updater;

import com.dogiloki.multitaks.directory.ListFields;

/**
 *
 * @author _dogi
 */

public class UpdateItems extends ListFields<UpdateItem>{
    
    public UpdateItems(){
        
    }
    
    public synchronized int getCountApplied(){
        return (int)this.stream()
                .filter(item->item.isApplied())
                .count();
    }
    
}
