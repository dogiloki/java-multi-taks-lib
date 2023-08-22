package multitaks.socket;

import multitaks.socket.handles.SocketHandle;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 *
 * @author dogi_
 */

public class SocketClient extends SocketHandle implements Runnable{

    private SocketChannel socket;
    
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
    
    public void emit(String channel_name, Object message){
        try{
            this.getMapEmit().put(channel_name,message);
            PrintWriter writer=new PrintWriter(socket.socket().getOutputStream(),true);
            writer.println(new SocketData(channel_name,message).toString());
            writer.flush();
        }catch(Exception ex){
            ex.printStackTrace();
        }
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
        new Thread(this).start();
    }
    
    public void close()throws IOException{
        this.start=false;
    }
    
    @Override
    public void run(){
        this.start=true;
        try{
            BufferedReader reader=new BufferedReader(new InputStreamReader(this.socket.socket().getInputStream()));
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
    
    public SocketChannel getSocket(){
        return this.socket;
    }
    
}
