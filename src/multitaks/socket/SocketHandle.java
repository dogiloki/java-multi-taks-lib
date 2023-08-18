package multitaks.socket;

import java.io.IOException;
import java.io.OutputStream;
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
        public void run(Object message);
    }
    
    private Map<String,SocketHandle.onMessage> channels=new HashMap<>();
    
    public SocketHandle(){
        
    }
    
    public void on(String channel, onMessage action){
        
    }
    
    public void emit(String channel, Object message) throws IOException{
        
    }
    
    public void send(Socket socket, Object message) throws IOException{
        OutputStream out=socket.getOutputStream();
        PrintWriter writer=new PrintWriter(out,true);
        if(message instanceof byte[]){
            out.write((byte[])message);
        }else{
            writer.println(message);
        }
    }
    
    public void start() throws IOException{
        
    }
    
    public void close() throws IOException{
        
    }
    
    public Map<String,SocketHandle.onMessage> getChannels(){
        return this.channels;
    }
    
}
