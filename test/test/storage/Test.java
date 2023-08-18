package test.storage;

import java.util.Base64;
import multitaks.code.Code;
import multitaks.directory.FileBlock;
import multitaks.directory.Storage;

/**
 *
 * @author dogi_
 */

public class Test{
    
    public Test(){
        String text="dsadsadç`´´`+´´çç+`+'0-";
        String code=Code.encode64(text);
        System.out.println(text);
        System.out.println(Code.decode64(code));
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}
