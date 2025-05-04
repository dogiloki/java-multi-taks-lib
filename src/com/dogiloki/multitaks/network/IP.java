package com.dogiloki.multitaks.network;

import com.dogiloki.multitaks.network.enums.IPClassification;

/**
 *
 * @author _dogi
 */

public class IP{
    
    private String address;
    private String mask;
    private IPClassification classification;
    
    public IP(String address, String mask){
        this.address=address;
        this.mask=mask;
    }
    
    public String getAddress(){
        return this.address;
    }
    
    public String getMask(){
        return this.mask;
    }
    
    @Override
    public String toString(){
        return this.getAddress();
    }
    
}
