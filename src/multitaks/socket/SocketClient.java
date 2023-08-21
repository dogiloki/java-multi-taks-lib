package multitaks.socket;

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
    
    public void on(String channel_name, SocketHandle.onMessage action)throws IOException{
        this.getChannels().put(channel_name, new ChannelHandle(channel_name,action));
        this.emit(channel_name,"");
    }
    
    public void emit(String channel_name, Object message)throws IOException{
        this.buffer.put(new SocketData(channel_name,message).toString().getBytes());
        this.buffer.flip();
        this.socket.write(this.buffer);
        this.buffer.clear();
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
                ChannelHandle on_action=this.getChannels().get(message.getChannel());
                if(on_action!=null){
                    on_action.getAction().run(message.getMessage().toString());
                }
            }
        }catch(Exception ex){
            
        }
    }
    
    public SocketChannel getSocket(){
        return this.socket;
    }
    
}
