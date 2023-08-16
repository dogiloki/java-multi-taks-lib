package multitaks.socket.contracts;

import java.net.Socket;
import java.util.List;

/**
 *
 * @author dogi_
 */

public interface SocketServerImpl{
    
    public List<Socket> getClients();
    
}
