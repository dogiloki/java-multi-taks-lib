package multitaks.socket;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import multitaks.Network;

/**
 *
 * @author dogi_
 */

public class SocketServer extends SocketHandle implements Runnable{
    
    public interface onMessage{
        public void run(SocketData message);
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
    public void emit(SocketData data) throws IOException{
        for(Socket client:this.getClients()){
            this.send(client,data.toString());
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
                this.getChannels().get("connected").run(new SocketData("connected",client));
                BufferedReader reader=new BufferedReader(new InputStreamReader(client.getInputStream()));
                String data;
                while((data=reader.readLine())!=null){
                    try{
                        SocketData message=new Gson().fromJson(data,SocketData.class);
                        SocketServer.onMessage on_message=this.getChannels().get(message.getChannel());
                        if(on_message!=null && !message.getChannel().equals("connected") && !message.getChannel().equals("disconnected")){
                            on_message.run(message);
                        }
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
                this.getChannels().get("disconnected").run(new SocketData("disconnected",client));
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
