package multitaks.socket;

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
    
    private Map<String,ChannelHandle> channels=new HashMap<>();
    
    public SocketHandle(){
        
    }
    
    public Map<String,ChannelHandle> getChannels(){
        return this.channels;
    }
    
    public String getIP(){
        return this.ip;
    }
    
    public int getPort(){
        return this.port;
    }
    
    public String getAddress(){
        return this.ip+":"+this.port;
    }
    
    public boolean isStart(){
        return this.start;
    }
    
}
