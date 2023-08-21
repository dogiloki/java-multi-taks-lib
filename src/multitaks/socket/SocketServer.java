package multitaks.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import multitaks.socket.contracts.SocketServerImpl;

/**
 *
 * @author dogi_
 */

public class SocketServer extends SocketHandle implements Runnable, SocketServerImpl{
    
    public interface onConnect{
        public void run(ClientHandle client);
    }
    
    public interface onDisconnect{
        public void run(ClientHandle client);
    }
    
    private ServerSocketChannel socket;
    private List<ClientHandle> clients=new ArrayList<>();
    private ExecutorService executor;
    public onConnect onConnect=(client)->{};
    public onDisconnect onDisconnect=(client)->{};
    
    public SocketServer(){
        
    }
    
    public SocketServer(int port){
        this.init(port);
    }
    
    private void init(int port){
        this.port=port;
    }
    
    public void on(String channel_name, SocketHandle.onMessage action){
        this.getChannels().put(channel_name, new ChannelHandle(channel_name,action));
    }
    
    public void emit(String channel_name, Object message)throws IOException{
        for(ClientHandle client:this.getClients()){
            client.emit(channel_name,message);
        }
    }
    
    public void emit(List<ClientHandle> clients, String channel_name, Object message)throws IOException{
        for(ClientHandle client:clients){
            client.emit(channel_name,message);
        }
    }
    
    public void emit(ClientHandle client, String channel_name, Object message)throws IOException{
        client.emit(channel_name,message);
    }
    
    public void start(int port)throws IOException{
        this.init(port);
        this._start();
    }
    
    public void start()throws IOException{
        this._start();
    }
    
    private void _start()throws IOException{
        this.socket=ServerSocketChannel.open();
        this.socket.bind(new InetSocketAddress("192.168.1.73",this.port));
        this.socket.configureBlocking(false);
        this.executor=Executors.newFixedThreadPool(10);
        this.ip=this.socket.getLocalAddress().toString().split(":")[0];
        new Thread(this).start();
    }
    
    public void close()throws IOException{
        this.start=false;
        this.socket.close();
    }
    
    @Override
    public void run(){
        this.start=true;
        try{
            while(this.isStart()){
                SocketChannel client=this.socket.accept();
                if(client==null){
                    continue;
                }
                this.executor.submit(()->{
                    try{
                        ClientHandle client_handle=new ClientHandle(this,client);
                        this.onConnect.run(client_handle);
                        this.getClients().add(client_handle);
                        client_handle.listener();
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                });
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public String getAddress(){
        return this.getIP()+":"+this.getPort();
    }
    
    @Override
    public String getIP(){
        return this.ip;
    }
    
    public List<ClientHandle> getClients(){
        return this.clients;
    }
    
    public ServerSocketChannel getSocket(){
        return this.socket;
    }
    
}
