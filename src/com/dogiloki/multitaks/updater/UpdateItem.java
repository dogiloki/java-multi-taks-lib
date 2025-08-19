package com.dogiloki.multitaks.updater;

import com.dogiloki.multitaks.directory.Storage;

/**
 *
 * @author _dogi
 */

public class UpdateItem{
    
    public final Storage current;
    public final Storage backup;
    public final Storage last;
    
    public UpdateItem(Storage current, Storage backup, Storage last){
        this.current=current;
        this.backup=backup;
        this.last=last;
    }
    
    public void apply()throws Exception{
        Storage.copyFile(this.current.getSrc(),this.backup.getSrc());
        Storage.copyFile(this.last.getSrc(),this.current.getSrc());
    }
    
}
