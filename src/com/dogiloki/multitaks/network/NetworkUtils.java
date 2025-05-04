package com.dogiloki.multitaks.network;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dogi_
 */

public class NetworkUtils{

    /**
     * Obtener IPv4 del dispositivo actual
     * @return String con la dirección IPv4
     */
    public static String getIp(){
        try{
            InetAddress address=InetAddress.getLocalHost();
            return address.getHostAddress();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return "";
    }
    
    /**
     * Obtener nombre host del dispositivo actual
     * @return String con el nombre host
     */
    public static String getHost(){
        try{
            InetAddress address=InetAddress.getLocalHost();
            return address.getHostName();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return "";
    }
    
    /**
     * Obtener dirección física MAC del dispositivo actual
     * @return String la MAC
     */
    public static String getMac(){
        try{
            InetAddress address=InetAddress.getLocalHost();
            NetworkInterface network=NetworkInterface.getByInetAddress(address);
            if(network!=null){
                byte[] mac=network.getHardwareAddress();
                if(mac!=null){
                    StringBuilder mac_builder=new StringBuilder();
                    for(int index=0; index<mac.length; index++){
                        mac_builder.append(String.format("%02X%s",mac[index],(index<mac.length-1)?"-":""));
                    }
                    return mac_builder.toString();
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return "";
    }
    
    public static IPRange getIpRange(String gateway, String netmask) throws Exception{
        byte[] ip=InetAddress.getByName(gateway).getAddress();
        byte[] mask=InetAddress.getByName(netmask).getAddress();
        byte[] network=new byte[4];
        byte[] broadcast=new byte[4];
        for(int a=0; a<4; a++){
            network[a]=(byte)(ip[a]&mask[a]);
            broadcast[a]=(byte)(network[a]|~mask[a]);
        }
        long start=ipToLong(network)+1;
        long end=ipToLong(broadcast)-1;
        IPRange result=new IPRange();
        for(long a=start; a<=end; a++){
            result.add(new IP(longToIp(a),netmask));
        }
        return result;
    }
    
    public static long ipToLong(byte[] ip){
        long result=0;
        for(byte b:ip){
            result=(result<<8)|(b&0XFF);
        }
        return result;
    }
    
    public static String longToIp(long ip){
        return String.format("%d.%d.%d.%d",
                (ip>>24)&0XFF,
                (ip>>16)&0XFF,
                (ip>>8)&0XFF,
                ip&0XFF);
    }
    
}
