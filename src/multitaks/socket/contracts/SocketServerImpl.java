package multitaks.socket.contracts;

import java.net.Socket;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dogi_
 */

public interface SocketServerImpl{
    
    public Map<String,List<Socket>> getClients();
    public void setClient(String channel, Socket client);
    
}
