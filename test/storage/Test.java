package storage;

import com.dogiloki.multitaks.directory.compressed.CompressedList;
import com.dogiloki.multitaks.directory.compressed.CompressedStorage;
import net.lingala.zip4j.model.FileHeader;

/**
 *
 * @author dogi_
 */

public class Test{
    
    public Test(){
        try{
            CompressedStorage zip=new CompressedStorage("hola.zip");
            CompressedList list=zip.listDirectory();
            FileHeader header=zip.getFileHeader("prueba.txt");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}
