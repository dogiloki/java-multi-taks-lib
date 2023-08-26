package com.dogiloki.multitaks.socket;

import com.dogiloki.multitaks.socket.handles.SocketHandle;
import com.dogiloki.multitaks.socket.handles.ClientHandle;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.dogiloki.multitaks.socket.contracts.SocketServerImpl;

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
    
    private ServerSocket socket;
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
    
    @Override
    public void emit(String channel_name, Object message){
        super.emit(channel_name,message);
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
        this.socket=new ServerSocket(this.port);
        this.executor=Executors.newFixedThreadPool(10);
        this.ip=this.socket.getInetAddress().getHostAddress();
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
                Socket client=this.socket.accept();
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
    
    public ServerSocket getSocket(){
        return this.socket;
    }
    
}
