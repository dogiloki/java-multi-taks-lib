package multitaks.socket;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import multitaks.Network;
import multitaks.socket.contracts.SocketServerImpl;

/**
 *
 * @author dogi_
 */

public class SocketServer extends SocketHandle implements Runnable, SocketServerImpl{
    
    public interface onMessage{
        public void run(Object message);
    }
    
    private String ip;
    private int port;
    private ServerSocket socket;
    private List<Socket> clients=new ArrayList<>();
    
    public SocketServer(int port) throws IOException{
        this.port=port;
        this.socket=new ServerSocket(this.port);
    }
    
    @Override
    public void emit(String channel, Object message) throws IOException{
        this._emit(this.getClients(),channel,message);
    }
    
    @Override
    public void emit(List<Socket> clients, String channel, Object message) throws IOException{
        this._emit(clients,channel,message);
    }
    
    @Override
    public void emit(Socket client, String channel, Object message) throws IOException{
        this._emit(Collections.singletonList(client),channel,message);
    }
    
    private void _emit(List<Socket> clients, String channel, Object message) throws IOException{
        for(Socket client:clients){
            this.send(client,new SocketData(channel,message.toString()).toString());
        }
    }
    
    @Override
    public void start(){
        this.ip=Network.getIp();
        new Thread(this).start();
    }
    
    @Override
    public void close() throws IOException{
        this.socket.close();
    }
    
    @Override
    public void run(){
        while(true){
            try{
                Socket client=this.socket.accept();
                this.getClients().add(client);
                this.getChannels().get("connected").run(client);
                BufferedReader reader=new BufferedReader(new InputStreamReader(client.getInputStream()));
                String data;
                while((data=reader.readLine())!=null){
                    try{
                        SocketData message=new Gson().fromJson(data,SocketData.class);
                        SocketServer.onMessage on_message=this.getChannels().get(message.getChannel());
                        if(on_message!=null && !message.getChannel().equals("connected") && !message.getChannel().equals("disconnected")){
                            on_message.run(message.getMessage());
                        }
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
                this.getChannels().get("disconnected").run(client);
                reader.close();
                this.getClients().remove(client);
                client.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
            
        }
    }
    
    public String getAddress(){
        return this.ip+":"+this.port;
    }
    
    public String getIP(){
        return this.ip;
    }
    
    public int getPort(){
        return this.port;
    }
    
    public List<Socket> getClients(){
        return this.clients;
    }
    
}
