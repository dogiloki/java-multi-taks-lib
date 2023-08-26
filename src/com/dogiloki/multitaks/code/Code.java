package com.dogiloki.multitaks.code;

import java.util.Base64;

/**
 *
 * @author dogi_
 */

public class Code{
    
    public interface onSplitSegment{
        public void run(String text, int count);
    }
    
    public static String encode64(String text){
        return Base64.getEncoder().encodeToString(text.getBytes());
    }
    
    public static String decode64(String text){
        return new String(Base64.getDecoder().decode(text));
    }
    
    public static String textToBinary(String text){
        String binary="";
        for(char c:text.toCharArray()){
            String number=Integer.toBinaryString(c);
            while(number.length()<8){
                number="0"+number;
            }
            binary+=number;
        }
        return binary;
    }
    
    public static String binaryToText(String binary){
        String text="";
        
        return text;
    }
    
    public static String[] splitSegment(String text, int number, onSplitSegment action){
        int each=(int)Math.ceil(text.length()/number);
        String[] array=new String[each];
        int count=0;
        int end=0;
        for(int index=1; index<=text.length(); index++){
            if(index%number!=0 || index-1==number){
                continue;
            }
            end=text.length()-index;
            String text_block=text.substring(count*number,end); // Pendiente a optimizar
            array[count]=text_block;
            count++;
            action.run(text_block,count);
        }
        return array;
    }
    
    public static byte[] byteArrayToString(String text, int byte_size){
        byte[] b=new byte[byte_size];
        if(text.isEmpty()){
            return b;
        }
        String[] parts=text.replace("[","").replace("]","").split(",");
        for(int index=0; index<parts.length; index++){
            int num=(int)Double.parseDouble(parts[index].trim());
            b[index]=Byte.parseByte(String.valueOf(num));
        }
        return b;
    }
    
}
