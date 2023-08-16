package multitaks.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import multitaks.socket.SocketServer.onMessage;
import multitaks.socket.contracts.SocketServerImpl;

/**
 *
 * @author dogi_
 */

public class SocketHandle implements SocketServerImpl{
    
    private Map<String,SocketServer.onMessage> channels=new HashMap<>();
    
    public SocketHandle(){
        this.on("connected",(data)->{});
        this.on("disconnected",(data)->{});
    }
    
    public void on(String channel, onMessage action){
        this.getChannels().put(channel,action);
    }
    
    public void emit(String channel, Object message) throws IOException{
        
    }
    
    public void send(Socket socket, String message) throws IOException{
        PrintWriter writer=new PrintWriter(socket.getOutputStream(),true);
        writer.println(message);
    }
    
    public void start() throws IOException{
        
    }
    
    public void close() throws IOException{
        
    }
    
    public Map<String,SocketServer.onMessage> getChannels(){
        return this.channels;
    }

    @Override
    public List<Socket> getClients(){
        return new ArrayList<>();
    }
    
}
