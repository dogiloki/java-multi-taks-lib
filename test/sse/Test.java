package sse;

import org.eclipse.jetty.server.Server;

/**
 *
 * @author _dogi
 */

public class Test{
    
    public Test(){
        try{
            Server server=new Server();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}
