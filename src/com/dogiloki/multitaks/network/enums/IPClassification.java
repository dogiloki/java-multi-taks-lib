package com.dogiloki.multitaks.network.enums;

/**
 *
 * @author _dogi
 */

public enum IPClassification{
    
    A("255","255.0.0.0"),
    B("255","255.255.0.0"),
    C("255","255.255.255.0"),
    D("255","255.255.255.255"),
    NONE("Sin determinal","");
    
    public String str;
    public String mask;
    
    private IPClassification(String str, String mask){
        this.str=str;
        this.mask=mask;
    }
    
    public String toStirng(){
        return str;
    }
    
}
