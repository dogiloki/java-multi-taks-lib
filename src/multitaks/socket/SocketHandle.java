package multitaks.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dogi_
 */

public class SocketHandle{
    
    public interface onMessage{
        public void run(String message);
    }
    
    private Map<String,SocketHandle.onMessage> channels=new HashMap<>();
    
    public SocketHandle(){
        
    }
    
    public void on(String channel, onMessage action){
        this.getChannels().put(channel,action);
    }
    
    public void emit(String channel, Object message) throws IOException{
        
    }
    
    public void emit(List<Socket> clients, String channel, Object message) throws IOException{
        
    }
    
    public void emit(Socket socket, String channel, Object message) throws IOException{
        
    }
    
    public void send(Socket socket, String message) throws IOException{
        PrintWriter writer=new PrintWriter(socket.getOutputStream(),true);
        writer.println(message);
    }
    
    public void start() throws IOException{
        
    }
    
    public void close() throws IOException{
        
    }
    
    public Map<String,SocketHandle.onMessage> getChannels(){
        return this.channels;
    }
    
}
