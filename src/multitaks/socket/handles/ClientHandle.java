package multitaks.socket.handles;

import com.google.gson.Gson;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import multitaks.socket.SocketData;
import multitaks.socket.SocketServer;

/**
 *
 * @author dogi_
 */

public final class ClientHandle{
    
    private ByteBuffer buffer=ByteBuffer.allocate(1024);
    private int bytes_read;
    private SocketServer server;
    private SocketChannel socket;
    private final OnHandle map_on=new OnHandle();
    private final EmitHandle map_emit=new EmitHandle();
    private String address;
    
    public ClientHandle(SocketServer server, SocketChannel socket) throws IOException{
        this.server=server;
        this.socket=socket;
        this.address=this.socket.getLocalAddress().toString();
    }
    
    public void listener()throws IOException{
        while((this.bytes_read=this.socket.read(this.buffer))>0 && this.server.isStart()){
            this.buffer.flip();
            byte[] data=new byte[this.bytes_read];
            this.buffer.get(data);
            SocketData message=new Gson().fromJson(new String(data),SocketData.class);
            this.buffer.clear();
            // Enviar mensaje al cliente
            /*
            if(this.getMapEmit().containsKey(message.getChannel())){
                this.write(message.getChannel(),this.getMapEmit().get(message.getChannel()));
            }
            */
            // Ejecutar callback de entrada de mensaje del cliente
            SocketHandle.onMessage on_client=this.getMapOn().get(message.getChannel());
            if(on_client!=null){
                on_client.run(message.getMessage().toString());
            }
            SocketHandle.onMessage on_server=this.server.getMapOn().get(message.getChannel());
            if(on_server!=null){
                on_server.run(message.getMessage().toString());
            }
        }
        this.socket.close();
    }
    
    public void on(String channel_name, SocketHandle.onMessage action){
        this.getMapOn().put(channel_name,action);
    }
    
    public void emit(String channel_name, Object message){
        this.getMapEmit().put(channel_name,message);
        this.write(channel_name,message);
    }
    
    public void write(String channel_name, Object message){
        try{
            this.buffer.clear();
            this.buffer.put(new SocketData(channel_name,message).toString().getBytes());
            this.buffer.flip();
            this.socket.write(this.buffer);
            this.buffer.clear();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    // Getters
    public ByteBuffer getBuffer(){
        return this.buffer;
    }
    public int getBytesRead(){
        return this.bytes_read;
    }
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
    
}
