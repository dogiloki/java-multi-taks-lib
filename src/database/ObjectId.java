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
    
    private long count;
    
    public static String generate(){
        return new ObjectId().get();
    }
    
    public static String generate(long count){
        return new ObjectId(count).get();
    }
    
    public ObjectId(){
        this.count=0;
    }
    
    public ObjectId(long count){
        this.count=count;
    }
    
    public String get(){
        try{
            ByteArrayOutputStream out_array=new ByteArrayOutputStream();
            out_array.write(this.getTimestamp());
            out_array.write(this.getMac());
            out_array.write(this.getCount());
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
    
    private byte[] getTimestamp(){
        long timestamp=System.currentTimeMillis();
        byte[] timestamp_bytes={
            (byte)((timestamp>>24) & 0xFF),
            (byte)((timestamp>>16) & 0xFF),
            (byte)((timestamp>>8) & 0xFF),
            (byte)((timestamp) & 0xFF)
        };
        return timestamp_bytes;
    }
    
    private byte[] getMac(){
        byte[] mac=Function.getMac().getBytes();
        return Arrays.copyOfRange(mac,0,3);
    }
    
    private byte[] getCount(){
        this.count++;
        byte[] count=String.valueOf(this.count).getBytes();
        return Arrays.copyOfRange(count,0,3);
    }
    
}
