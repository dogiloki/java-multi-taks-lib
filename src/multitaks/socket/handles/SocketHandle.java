package multitaks.socket.handles;

/**
 *
 * @author dogi_
 */

public class SocketHandle{
    
    public interface onMessage{
        public void run(String message);
    }
    
    protected String ip;
    protected int port;
    protected boolean start=false;
    protected final OnHandle map_on=new OnHandle();
    protected final EmitHandle map_emit=new EmitHandle();
    
    public SocketHandle(){
        
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
    
    public OnHandle getMapOn(){
        return this.map_on;
    }
    
    public EmitHandle getMapEmit(){
        return this.map_emit;
    }
    
}
