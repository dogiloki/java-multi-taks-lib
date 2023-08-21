package multitaks.socket;

import multitaks.socket.handles.SocketHandle;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 *
 * @author dogi_
 */

public class SocketClient extends SocketHandle implements Runnable{
    
    private SocketChannel socket;
    private ByteBuffer buffer;
    
    public SocketClient(){
    
    }
    
    public SocketClient(String ip, int port){
        this.init(ip,port);
    }
    
    private void init(String ip, int port){
        this.ip=ip;
        this.port=port;
    }
    
    public void on(String channel_name, SocketHandle.onMessage action){
        this.getMapOn().put(channel_name,action);
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
    
    public void emit(String channel_name, Object message){
        this.getMapEmit().put(channel_name,message);
        this.write(channel_name,message);
    }
    
    public void start(String ip, int port)throws IOException{
        this.init(ip,port);
        this._start();
    }
    
    public void start()throws IOException{
        this._start();
    }
    
    private void _start()throws IOException{
        this.socket=SocketChannel.open();
        this.socket.connect(new InetSocketAddress(this.getIP(),this.getPort()));
        this.buffer=ByteBuffer.allocate(1024);
        new Thread(this).start();
    }
    
    public void close()throws IOException{
        this.start=false;
        this.socket.close();
        this.buffer.clear();
    }
    
    @Override
    public void run(){
        this.start=true;
        try{
            while(this.isStart()){
                this.buffer.clear();
                int bytes_read=this.socket.read(this.buffer);
                if(bytes_read<=0){
                    continue;
                }
                this.buffer.flip();
                byte[] data=new byte[bytes_read];
                this.buffer.get(data);
                SocketData message=new Gson().fromJson(new String(data),SocketData.class);
                this.buffer.clear();
                /*
                if(this.getMapEmit().containsKey(message.getChannel())){
                    this.write(message.getChannel(),this.getMapEmit().get(message.getChannel()));
                }
                */
                SocketHandle.onMessage on_action=this.getMapOn().get(message.getChannel());
                if(on_action!=null){
                    on_action.run(message.getMessage().toString());
                }
            }
        }catch(Exception ex){
            
        }
    }
    
    public SocketChannel getSocket(){
        return this.socket;
    }
    
}
