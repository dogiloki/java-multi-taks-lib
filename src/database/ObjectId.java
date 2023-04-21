package database;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import multitaks.Function;

/**
 *
 * @author dogi_
 */

public class ObjectId{
    
    private static long count=0;
    
    public ObjectId(){
        
    }
    
    public static String generate(){
        try{
            ByteArrayOutputStream out_array=new ByteArrayOutputStream();
            out_array.write(ObjectId.getTimestamp());
            out_array.write(ObjectId.getMac());
            out_array.write(ObjectId.getCount());
            byte[] hash=MessageDigest.getInstance("SHA-256").digest(out_array.toByteArray());
            StringBuilder hex=new StringBuilder();
            for(int index=0; index<hash.length; index++){
                hex.append(Integer.toHexString(0xFF & hash[index]));
            }
            return hex.toString();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    private static byte[] getTimestamp(){
        long timestamp=System.currentTimeMillis();
        byte[] timestamp_bytes={
            (byte)((timestamp>>24) & 0xFF),
            (byte)((timestamp>>16) & 0xFF),
            (byte)((timestamp>>8) & 0xFF),
            (byte)((timestamp) & 0xFF)
        };
        return timestamp_bytes;
    }
    
    private static byte[] getMac(){
        byte[] mac=Function.getMac().getBytes();
        return Arrays.copyOfRange(mac,0,3);
    }
    
    private static byte[] getCount(){
        ObjectId.count++;
        byte[] count=String.valueOf(ObjectId.count).getBytes();
        return Arrays.copyOfRange(count,0,3);
    }
    
}
