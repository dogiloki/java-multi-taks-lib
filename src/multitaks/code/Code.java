package multitaks.code;

import java.util.Base64;

/**
 *
 * @author dogi_
 */

public class Code{
    
    public static String encode64(String text){
        return Base64.getEncoder().encodeToString(text.getBytes());
    }
    
    public static String decode64(String text){
        return new String(Base64.getDecoder().decode(text));
    }
    
}
