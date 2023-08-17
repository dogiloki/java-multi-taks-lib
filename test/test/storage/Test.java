package test.storage;

import java.util.Base64;
import multitaks.directory.FileBlock;
import multitaks.directory.Storage;

/**
 *
 * @author dogi_
 */

public class Test{
    
    public Test(){
        Storage s=new Storage("VID-20230210-WA0004.mp4");
        FileBlock file=s.readBloks(1024);
        byte[] b;
        while((b=file.read())!=null){
            String hola=Base64.getEncoder().encodeToString(b);
            System.out.println(b);
        }
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}
