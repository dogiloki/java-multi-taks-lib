package com.dogiloki.multitaks.socket;

import com.google.gson.Gson;

/**
 *
 * @author dogi_
 */

public class SocketData{
    
    private String channel;
    private Object message;
    
    public SocketData(String channel, Object message){
        this.channel=channel;
        this.message=message;
    }
    
    @Override
    public String toString(){
        return new Gson().toJson(this);
    }
    
    // Getters
    public String getChannel(){
        return this.channel;
    }
    public Object getMessage(){
        return this.message;
    }
    
}
