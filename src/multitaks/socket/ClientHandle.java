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
            ChannelHandle on_action=this.server.getChannels().get(message.getChannel());
            if(on_action!=null){
                on_action.getAction().run(message.getMessage().toString());
            }
            this.buffer.clear();
        }
        this.socket.close();
    }
    
    public void on(String channel_name, SocketHandle.onMessage action)throws IOException{
        this.getChannels().put(channel_name,new ChannelHandle(channel_name,action));
    }
    
    public void emit(String channel_name, Object message)throws IOException{
        if(!this.getChannels().containsKey(channel_name)){
            return;
        }
        this.buffer.put(new SocketData(channel_name,message).toString().getBytes());
        this.buffer.flip();
        this.socket.write(this.buffer);
        this.buffer.clear();
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
