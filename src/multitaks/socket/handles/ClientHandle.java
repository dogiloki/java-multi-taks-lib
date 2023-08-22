package multitaks.socket.handles;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.channels.SocketChannel;
import multitaks.socket.SocketData;
import multitaks.socket.SocketServer;

/**
 *
 * @author dogi_
 */

public final class ClientHandle{

    private final SocketServer server;
    private final SocketChannel socket;
    private final OnHandle map_on=new OnHandle();
    private final EmitHandle map_emit=new EmitHandle();
    private final String address;
    
    public ClientHandle(SocketServer server, SocketChannel socket) throws IOException{
        this.server=server;
        this.socket=socket;
        this.address=this.socket.getLocalAddress().toString();
    }
    
    public void listener()throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(this.socket.socket().getInputStream()));
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
        reader.close();
        this.socket.close();
    }
    
    public void on(String channel_name, SocketHandle.onMessage action){
        this.getMapOn().put(channel_name,action);
    }
    
    public void emit(String channel_name, Object message){
        try{
            this.getMapEmit().put(channel_name,message);
            PrintWriter writer=new PrintWriter(this.socket.socket().getOutputStream(),true);
            writer.println(new SocketData(channel_name,message).toString());
            writer.flush();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    // Getters
    public SocketServer getServer(){
        return this.server;
    }
    public SocketChannel getSocket(){
        return this.socket;
    }
    public OnHandle getMapOn(){
        return this.map_on;
    }
    public EmitHandle getMapEmit(){
        return this.map_emit;
    }
    public String getAddress(){
        return this.address;
    }
    
}
