package com.dogiloki.multitaks.network;

import static com.dogiloki.multitaks.network.NetworkUtils.longToIp;
import com.dogiloki.multitaks.network.enums.IPClassification;
import java.net.InetAddress;

/**
 *
 * @author _dogi
 */

public class IP{
    
    private String address;
    private String mask;
    private byte[] ip;
    private IPClassification classification;
    
    public IP(String address, String mask){
        this.mask=mask;
        this.address(address);
    }
    
    private void address(String address){
        this.address=address;
        try{
            this.ip=InetAddress.getByName(address).getAddress();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public String getAddress(){
        return this.address;
    }
    
    public String getMask(){
        return this.mask;
    }
    
    public IP add(int count){
        this.address(NetworkUtils.longToIp(NetworkUtils.ipToLong(this.ip)+count));
        return this;
    }
    
    public IP sub(int count){
        this.address(NetworkUtils.longToIp(NetworkUtils.ipToLong(this.ip)-count));
        return this;
    }
    
    @Override
    public String toString(){
        return this.getAddress();
    }
    
}
