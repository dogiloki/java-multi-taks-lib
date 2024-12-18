package com.dogiloki.multitaks.server.sse;

/**
 *
 * @author _dogi
 */

public class SSEMessage{
    
    private String id=String.valueOf(System.currentTimeMillis());
    private String event;
    private String data;
    private int retry;
    
    public SSEMessage(){
        
    }
    
    public String build(){
        StringBuilder str=new StringBuilder();
        str.append("id: ").append(this.getId());
        str.append("event: ").append(this.getEvent());
        str.append("data: ").append(this.getData());
        str.append("retry: ").append(this.getRetry());
        str.append("\n\n");
        return str.toString();
    }
    
    // Setters
    public SSEMessage setEvent(String event){
        this.event=event;
        return this;
    }
    public SSEMessage setData(String data){
        this.data=data;
        return this;
    }
    public SSEMessage setRetry(int retry){
        this.retry=retry;
        return this;
    }
    
    // Getters
    public String getId(){
        return this.id;
    }
    public String getEvent(){
        return this.event;
    }
    public String getData(){
        return this.data;
    }
    public int getRetry(){
        return this.retry;
    }
    
}
