package format;

import com.dogiloki.multitaks.dataformat.JSON;

/**
 *
 * @author dogi_
 */

public class Test{
    
    public Test(){
        Config c=(Config)new Config().builder();
        /*
        c.folder="Hola que tal";
        c.flotante=1.70;
        c.peso=21;
        c.save();
        */
        System.out.println(c.toString());
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}