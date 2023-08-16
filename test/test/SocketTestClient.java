package test;

import multitaks.socket.SocketClient;
import multitaks.socket.SocketData;

/**
 *
 * @author dogi_
 */

public class SocketTestClient{
    
    public SocketTestClient(){
        try{
            SocketClient server=new SocketClient("192.168.1.73",1234);
            server.start();
            server.emit(new SocketData("test","Hola"));
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public static void main(String args[]){
        new SocketTestClient();
    }
    
}
