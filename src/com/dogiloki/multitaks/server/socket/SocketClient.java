package com.dogiloki.multitaks.server.socket;

import com.dogiloki.multitaks.server.socket.handles.SocketHandle;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author dogi_
 */

public class SocketClient extends SocketHandle implements Runnable{

    private Socket socket;
    
    public SocketClient(){
        
    }
    
    public SocketClient(String ip, int port){
        this.init(ip,port);
    }
    
    private void init(String ip, int port){
        this.ip=ip;
        this.port=port;
    }
    
    @Override
    public void emit(String channel_name, Object message){
        super.emit(channel_name,message);
        this.send(this.socket,channel_name,message);
    }
    
    public void start(String ip, int port)throws IOException{
        this.init(ip,port);
        this._start();
    }
    
    public void start()throws IOException{
        this._start();
    }
    
    private void _start()throws IOException{
        this.socket=new Socket(this.ip,this.port);
        new Thread(this).start();
    }
    
    public void close()throws IOException{
        this.start=false;
    }
    
    @Override
    public void run(){
        this.start=true;
        try{
            BufferedReader reader=new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            String message;
            while((message=reader.readLine())!=null && this.isStart()){
                SocketData data=new Gson().fromJson(message,SocketData.class);
                SocketHandle.onMessage on_action=this.getMapOn().get(data.getChannel());
                if(on_action!=null){
                    on_action.run(data.getMessage().toString());
                }
            }
            reader.close();
            this.socket.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public Socket getSocket(){
        return this.socket;
    }
    
}
