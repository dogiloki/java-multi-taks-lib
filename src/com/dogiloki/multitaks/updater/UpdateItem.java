package com.dogiloki.multitaks.updater;

import com.dogiloki.multitaks.directory.Storage;

/**
 *
 * @author _dogi
 */

public final class UpdateItem{
    
    public final Storage current;
    public final Storage backup;
    public final Storage last;
    private boolean applied=false;
    
    public UpdateItem(Storage current, Storage backup, Storage last){
        this.current=current;
        this.backup=backup;
        this.last=last;
    }
    
    public synchronized void loadBackup()throws Exception{
        if(this.current.exists()){
            Storage.copyFile(this.current.getSrc(),this.backup.getSrc());
        }
        
    }
    
    public synchronized void applyNewVersion()throws Exception{
        Storage.copyFile(this.last.getSrc(),this.current.getSrc());
    }
    
    public synchronized void applyWithBackup()throws Exception{
        this.loadBackup();
        Storage.copyFile(this.last.getSrc(),this.current.getSrc());
        this.applied=true;
    }
    
    public synchronized boolean isApplied(){
        return this.applied;
    }
    
    public synchronized void restoreBackup()throws Exception{
        if(this.backup.exists()){
            Storage.copyFile(this.backup.getSrc(),this.current.getSrc());
        }
    }
    
}
