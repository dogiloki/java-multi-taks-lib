package server;

import com.dogiloki.multitaks.server.web.ServerWeb;

/**
 *
 * @author _dogi
 */

public class Test{
    
    public Test(){
        
        ServerWeb server=new ServerWeb();
        try{
            server.listen();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}