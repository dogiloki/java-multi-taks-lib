package com.dogiloki.multitaks.socket.handles;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import com.dogiloki.multitaks.socket.SocketData;

/**
 *
 * @author dogi_
 */

public class SocketHandle{
    
    public interface onMessage{
        public void run(String message);
    }
    
    protected String ip;
    protected int port;
    protected boolean start=false;
    protected final OnHandle map_on=new OnHandle();
    protected final EmitHandle map_emit=new EmitHandle();
    
    public SocketHandle(){
        
    }
    
    public void on(String channel_name, SocketHandle.onMessage action){
        this.getMapOn().put(channel_name,action);
    }
    
    public void emit(String channel_name, Object message){
        this.getMapEmit().put(channel_name,message);
    }
    
    public void send(Socket socket, String channel_name, Object message){
        try{
            PrintWriter writer=new PrintWriter(socket.getOutputStream(),true);
            writer.println(new SocketData(channel_name,message).toString());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public String getIP(){
        return this.ip;
    }
    
    public int getPort(){
        return this.port;
    }
    
    public String getAddress(){
        return this.ip+":"+this.port;
    }
    
    public boolean isStart(){
        return this.start;
    }
    
    public OnHandle getMapOn(){
        return this.map_on;
    }
    
    public EmitHandle getMapEmit(){
        return this.map_emit;
    }
    
}
