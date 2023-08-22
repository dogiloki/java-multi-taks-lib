package multitaks.socket;

import multitaks.socket.handles.SocketHandle;
import multitaks.socket.handles.ClientHandle;
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
        this.getMapOn().put(channel_name,action); 
    }
    
    public void emit(String channel_name, Object message){
        this.getMapEmit().put(channel_name,message);
        this.getClients().forEach((client)->{
            client.emit(channel_name,message);
        });
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
        this.socket.bind(new InetSocketAddress("0.0.0.0",this.port));
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
                        this.getClients().add(client_handle);
                        this.onConnect.run(client_handle);
                        client_handle.listener();
                        this.getClients().remove(client_handle);
                        this.onDisconnect.run(client_handle);
                    }catch(Exception ex){
                        
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
