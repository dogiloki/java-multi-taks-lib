package storage;

import com.dogiloki.multitaks.directory.compressed.CompressedList;
import com.dogiloki.multitaks.directory.compressed.CompressedStorage;

/**
 *
 * @author dogi_
 */

public class Test{
    
    public Test(){
        try{
            CompressedStorage zip=new CompressedStorage("hola.zip");
            CompressedList list=zip.listDirectory();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}
