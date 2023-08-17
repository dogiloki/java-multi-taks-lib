package test.storage;

import java.io.File;
import multitaks.directory.Storage;

/**
 *
 * @author dogi_
 */

public class Test{
    
    public Test(){
        Storage s=new Storage("hola.txt");
        s.write("Hola");
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}
