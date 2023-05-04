package multitaks;

import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 *
 * @author dogi_
 */

public class Network{

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
    
}
