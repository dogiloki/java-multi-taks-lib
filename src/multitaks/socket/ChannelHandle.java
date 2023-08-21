
package multitaks.socket;

/**
 *
 * @author dogi_
 */

public class ChannelHandle{
    
    private String name;
    private SocketHandle.onMessage action;
    
    public ChannelHandle(String name, SocketHandle.onMessage action){
        this.name=name;
        this.action=action;
    }
    
    // Getters
    public String getName(){
        return this.name;
    }
    public SocketHandle.onMessage getAction(){
        return this.action;
    }
    
}
