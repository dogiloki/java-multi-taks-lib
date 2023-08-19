package multitaks.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dogi_
 */

public class SocketHandle{
    
    protected String ip;
    protected int port;
    protected boolean start=false;
    
    public interface onMessage{
        public void run(String message);
    }
    
    private Map<String,SocketHandle.onMessage> channels=new HashMap<>();
    
    public SocketHandle(){
        
    }
    
    public void send(Socket socket, Object message) throws IOException{
        OutputStream out=socket.getOutputStream();
        PrintWriter writer=new PrintWriter(out,true);
        if(message instanceof byte[]){
            out.write((byte[])message);
            out.flush();
        }else{
            writer.println(message);
            out.flush();
        }
    }
    
    public Map<String,SocketHandle.onMessage> getChannels(){
        return this.channels;
    }
    
    public int getPort(){
        return this.port;
    }
    
    public String getAddress(){
        return this.ip+":"+this.port;
    }
    
    public String getIP(){
        return this.ip;
    }
    
    public boolean isStart(){
        return this.start;
    }
    
}
