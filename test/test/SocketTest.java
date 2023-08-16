package test;

import multitaks.socket.SocketServer;

/**
 *
 * @author dogi_
 */
public class SocketTest{
    
    public SocketTest(){
        try{
            SocketServer server=new SocketServer(1234);
            server.start();
            server.on("test",(data)->{
                System.out.println(data.getMessage());
            });
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public static void main(String args[]){
        new SocketTest();
    }
    
}
