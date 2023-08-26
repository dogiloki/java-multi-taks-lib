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
        String text="dsadsadç`´´`+´´çç+`+'0-";
        //System.out.println(Code.textToBinary(text));
        /*String[] array=Code.splitSegment("0110010001110011011000010110010001110011011000010110010011100111011000001011010010110100011000000010101110110100101101001110011111100111001010110110000000101011001001110011000000101101",8,(text_block,count)->{
            System.out.println(text_block);
        });*/
        //System.out.println(String.valueOf(array));
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}
