package com.dogiloki.multitaks.directory;

import com.dogiloki.multitaks.dataformat.JSON;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dogi_
 */

public class ListFields<T> extends HashMap<T,Object>{
    
    public ListFields<T> append(T key, Object value){
        super.put(key,value);
        return this;
    }
    
    public String toQuery(){
        return this.toQuery(StandardCharsets.UTF_8.toString());
    }
    
    public String toQuery(String charset){
        StringBuilder str=new StringBuilder();
        this.forEach((key,value)->{
            if(str.length()>0){
                str.append("&");
            }
            try{
                str.append(URLEncoder.encode(key.toString(),charset))
                        .append("=")
                        .append(URLEncoder.encode(value.toString(),charset));
            }catch(UnsupportedEncodingException ex) {
                Logger.getLogger(ListFields.class.getName()).log(Level.SEVERE,null,ex);
            }
        });
        return str.toString();
    }
    
    public String toJson(){
        return JSON.gson().toJson(this,ListFields.class);
    }
    
    public ListFields(){
        
    }
    
}
