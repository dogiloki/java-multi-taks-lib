package format;

import com.dogiloki.multitaks.dataformat.JSON;

/**
 *
 * @author dogi_
 */

public class Test{
    
    public Test(){
        JSON j=new JSON("{\"folder\":{\"path\":\"dsa/gfd/sa\",\"name\":\"Hola\"},\"flotante\":1.7,\"peso\":21}");
        System.out.println(j.toJson("folder").getValue("path"));
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}