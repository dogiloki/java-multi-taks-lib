package com.dogiloki.multitaks.network;

import java.util.ArrayList;

/**
 *
 * @author _dogi
 */

public class IPRange extends ArrayList<IP>{
    
    public String getGateway(){
        return this.get(0).getAddress();
    }
    
    public String broadcast(){
        return this.get(this.size()-1).getAddress();
    }
    
    public String mask(){
        return this.get(0).getMask();
    }
    
}
