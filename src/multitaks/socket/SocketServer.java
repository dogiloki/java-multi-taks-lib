package multitaks.socket;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import multitaks.Network;
import multitaks.socket.contracts.SocketServerImpl;

/**
 *
 * @author dogi_
 */

public class SocketServer extends SocketHandle implements Runnable, SocketServerImpl{
    
    public interface onConnect{
        public void run(Socket client);
    }
    
    public interface onDisconnect{
        public void run(Socket client);
    }
    
    private ServerSocket socket;
    private Map<String,List<Socket>> clients=new HashMap<>();
    public onConnect onConnect=(client)->{};
    public onDisconnect onDisconnect=(client)->{};
    
    public SocketServer(){
        
    }
    
    public SocketServer(int port) throws IOException{
        this.init(port);
    }
    
    private void init(int port) throws IOException{
        this.port=port;
        this.socket=new ServerSocket(this.port);
    }
    
    public void on(String channel, onMessage action){
        this.getChannels().put(channel,action);
    }
    
    public void emit(String channel, Object message) throws IOException{
        List<Socket> clients=this.getClients().get(channel);
        if(clients==null){
            clients=new ArrayList<>();
        }
        for(Socket client:clients){
            this.send(client,new SocketData(channel,message).toString());
        }
    }
    
    /*
    @Override
    public void emit(List<Socket> clients, String channel, Object message) throws IOException{
        this._emit(clients,channel,message);
    }
    
    @Override
    public void emit(Socket client, String channel, Object message) throws IOException{
        this._emit(Collections.singletonList(client),channel,message);
    }
    
    private void _emit(List<Socket> clients, String channel, Object message) throws IOException{
        clients=this.getClients().get(channel);
        for(Socket client:clients){
            this.send(client,new SocketData(channel,message).toString());
        }
    }
    */
    
    public void start(int port) throws IOException{
        this.init(port);
        this._start();
    }
    
    public void start(){
        this._start();
    }
    
    private void _start(){
        this.ip=Network.getIp();
        new Thread(this).start();
    }
    
    public void close() throws IOException{
        this.socket.close();
    }
    
    @Override
    public void run(){
        while(true){
            try{
                Socket client=this.socket.accept();
                this.onConnect.run(client);
                BufferedReader reader=new BufferedReader(new InputStreamReader(client.getInputStream()));
                String data;
                while((data=reader.readLine())!=null){
                    try{
                        SocketData message=new Gson().fromJson(data,SocketData.class);
                        this.setClient(message.getChannel(),client);
                        SocketServer.onMessage on_message=this.getChannels().get(message.getChannel());
                        if(on_message!=null){
                            on_message.run(message.getMessage());
                        }
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
                this.onDisconnect.run(client);
                reader.close();
                this.getClients().remove(client);
                client.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
            
        }
    }
    
    @Override
    public Map<String,List<Socket>> getClients(){
        return this.clients;
    }
    
    @Override
    public void setClient(String channel, Socket client){
        List<Socket> sockets=this.getClients().get(channel);
        if(sockets==null){
            sockets=new ArrayList<>();
        }
        sockets.add(client);
        this.getClients().put(channel,sockets);
    }
    
    @Override
    public String getAddress(){
        return this.getIP()+":"+this.getPort();
    }
    
    @Override
    public String getIP(){
        return this.ip;
    }
    
    public ServerSocket getSocket(){
        return this.socket;
    }
    
}
