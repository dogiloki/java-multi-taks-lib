package test.storage;

import java.util.Base64;
import com.dogiloki.multitaks.code.Code;
import com.dogiloki.multitaks.directory.FileBlock;
import com.dogiloki.multitaks.directory.Storage;

/**
 *
 * @author dogi_
 */

public class Test{
    
    public Test(){
        Storage s=new Storage("db/personas");
        s.writeLine("hola",6);
        s.flush();
        
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}
