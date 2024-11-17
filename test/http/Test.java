package http;

import com.dogiloki.multitaks.http.Request;

/**
 *
 * @author _dogi
 */

public class Test{
    
    public Test(){
        /*
        Request request=new Request("https://pokeapi.co").uri("api/v2/pokemon/ditto").get();
        request.send().thenAccept(data->{
            System.out.println(data.body());
        }).exceptionally(err->{ 
            System.err.println(err);
            return null;
        }).join();
        */
        
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}
