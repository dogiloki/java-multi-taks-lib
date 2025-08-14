package com.dogiloki.multitaks;

import com.dogiloki.multitaks.network.NetworkUtils;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author dogi_
 */

public class ObjectId{
    
    private static AtomicLong COUNT_STATIC=new AtomicLong(-1);
    private long count;
    
    public static String generate(){
        return new ObjectId(COUNT_STATIC.incrementAndGet()).get();
    }
    
    public static String generate(long count){
        COUNT_STATIC.set(count);
        return new ObjectId(COUNT_STATIC.incrementAndGet()).get();
    }
    
    public static void count(long count){
        COUNT_STATIC.set(count);
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
            MessageDigest md=MessageDigest.getInstance("SHA-256");
            byte[] hash=md.digest(out_array.toByteArray());
            StringBuilder hex=new StringBuilder();
            for(byte b:hash){
                hex.append(String.format("%02x",b & 0XFF));
            }
            return hex.toString();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    private byte[] getTimestamp(){
        long timestamp=System.currentTimeMillis();
        return ByteBuffer.allocate(8).putLong(timestamp).array();
    }
    
    private byte[] getMac(){
        byte[] mac=NetworkUtils.getMac().getBytes();
        return Arrays.copyOfRange(mac,0,Math.min(mac.length,3));
    }
    
    private byte[] getCount(){
        this.count++;
        byte[] count_bytes=ByteBuffer.allocate(8).putLong(this.count).array();
        return Arrays.copyOfRange(count_bytes,0,3);
    }
    
}
