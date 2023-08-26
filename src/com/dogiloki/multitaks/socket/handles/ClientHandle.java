package com.dogiloki.multitaks.socket.handles;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import com.dogiloki.multitaks.socket.SocketData;
import com.dogiloki.multitaks.socket.SocketServer;

/**
 *
 * @author dogi_
 */

public final class ClientHandle extends SocketHandle{

    private final SocketServer server;
    private final Socket socket;
    
    public ClientHandle(SocketServer server, Socket socket) throws IOException{
        this.server=server;
        this.socket=socket;
        this.ip=socket.getInetAddress().getHostAddress();
        this.port=socket.getLocalPort();
    }
    
    public void listener()throws IOException{
        this.start=true;
        BufferedReader reader=new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        String message;
        while((message=reader.readLine())!=null && this.server.isStart()){
            SocketData data=new Gson().fromJson(message,SocketData.class);
            // Ejecutar callback de entrada de mensaje del cliente
            SocketHandle.onMessage on_client=this.getMapOn().get(data.getChannel());
            if(on_client!=null){
                on_client.run(data.getMessage().toString());
            }
            SocketHandle.onMessage on_server=this.server.getMapOn().get(data.getChannel());
            if(on_server!=null){
                on_server.run(data.getMessage().toString());
            }
        }
        this.start=false;
        reader.close();
        this.socket.close();
    }
    
    @Override
    public void emit(String channel_name, Object message){
        super.emit(channel_name,message);
        this.send(this.socket,channel_name,message);
    }
    
    // Getters
    public SocketServer getServer(){
        return this.server;
    }
    public Socket getSocket(){
        return this.socket;
    }
    public OnHandle getMapOn(){
        return this.map_on;
    }
    public EmitHandle getMapEmit(){
        return this.map_emit;
    }
    
}
