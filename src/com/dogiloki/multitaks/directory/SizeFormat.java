package com.dogiloki.multitaks.directory;

/**
 *
 * @author dogi_
 */

public class SizeFormat{
    
    private long size;
    
    public SizeFormat(){
        
    }
    
    public long size(){
        return this.size;
    }
    
    public SizeFormat size(Object size){
        this.size=(long)size;
        return this;
    }
    
    @Override
    public String toString(){
        return Storage.convertSize(this.size);
    }
    
}
