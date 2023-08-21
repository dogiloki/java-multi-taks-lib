package multitaks.socket;

import com.google.gson.Gson;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dogi_
 */

public final class ClientHandle{
    
    private ByteBuffer buffer=ByteBuffer.allocate(1024);
    private int bytes_read;
    private SocketServer server;
    private SocketChannel socket;
    private Map<String,ChannelHandle> channels=new HashMap<>();
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
            this.getChannels().put(message.getChannel(),null);
            ChannelHandle channel_server=this.server.getChannels().get(message.getChannel());
            ChannelHandle channel_client=this.getChannels().get(message.getChannel());
            if(channel_server!=null){
                channel_server.getAction().run(message.getMessage().toString());
            }
            if(channel_client!=null){
                channel_client.getAction().run(message.getMessage().toString());
            }
            this.buffer.clear();
        }
        this.socket.close();
    }
    
    public void on(String channel_name, SocketHandle.onMessage action){
        this.getChannels().put(channel_name,new ChannelHandle(channel_name,action));
    }
    
    public void emit(String channel_name, Object message){
        if(!this.getChannels().containsKey(channel_name)){
            return;
        }
        this.write(channel_name,message);
    }
    
    public void write(String channel_name, Object message){
        try{
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
    public Map<String,ChannelHandle> getChannels(){
        return this.channels;
    }
    public String getAdress(){
        return this.address;
    }
    
}
