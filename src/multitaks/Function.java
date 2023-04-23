package multitaks;

import java.awt.Dimension;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DecimalFormat;
import javax.swing.JPanel;

/**
 *
 * @author dogi_
 */

public class Function<T>{
    
    //  Convertir bytes en KB, MB, GB y TB
    public static String convertSize(long bytes){
        double kilobytes=bytes/Math.pow(1024,1);
        double megas=bytes/Math.pow(1024,2);
        double gigas=bytes/Math.pow(1024,3);
        double teras=bytes/Math.pow(1024,4);
        if(kilobytes<1024){
            return new DecimalFormat("#").format(kilobytes)+" KB";
        }else
        if(megas<1024){
            return new DecimalFormat("#.##").format(megas)+" MB";
        }else
        if(gigas<1024){
            return new DecimalFormat("#.##").format(gigas)+" GB";
        }else
        if(teras<1024){
            return new DecimalFormat("#.##").format(teras)+" TB";
        }else
        return "";
    }
    
    // Formato de número (dinero)
    public String formatNumber(String num){
        DecimalFormat formato=new DecimalFormat("#,###.##");
        return formato.format(Float.parseFloat(num));
    }
    
    // Asignar el primer valor diferente a nulo, dentro de un array
    public T set(T... values){
        for(T value:values){
            if(value!=null){
                return value;
            }
        }
        return null;
    }
    public static <T> T assign(T... values){
        for(T value:values){
            if(value!=null){
                if(!value.equals("")){
                    return value;
                }
            }
        }
        return null;
    }
    public static <T> T assignNotNull(T... values){
        for(T value:values){
            if(value!=null){
                return value;
            }
        }
        return null;
    }
    
    // Saber si esta dentro del rango
    public static boolean isRange(int num, int min, int max){
        return num>=min && num<=max;
    }
    
    // Convertir dos parámetro numéricos en dimenciones
    public static Dimension createDimencion(float ancho, float alto){
        return new Dimension((int)ancho,(int)alto);
    }
    
    // Agregar un panle dentro de otro panel
    public static void setPanel(JPanel panel1, JPanel panel2){
        panel2.setVisible(true);
        panel2.setBounds(0,0,panel1.getWidth(),panel1.getHeight());
        panel1.removeAll();
        panel1.add(panel2);
        panel1.updateUI();
    }
    
    // Dimencionar un panel en base a otro panel
    public static void resizablePanel(JPanel panel1, JPanel panel2){
        panel2.setBounds(0,0,panel1.getWidth(),panel1.getHeight());
        panel1.updateUI();
    }
    
    // Obtener ip
    public static String getIp(){
        try{
            InetAddress address=InetAddress.getLocalHost();
            return address.getHostAddress();
        }catch(Exception ex){
            System.out.println(ex);
            return "";
        }
    }
    public static String getIp(String ip){
        try{
            InetAddress address=InetAddress.getByName(ip);
            return address.getHostAddress();
        }catch(Exception ex){
            System.out.println(ex);
            return "";
        }
    }
    public static String getHost(){
        try{
            InetAddress address=InetAddress.getLocalHost();
            return address.getHostName();
        }catch(Exception ex){
            System.out.println(ex);
            return "";
        }
    }
    public static String getHost(String host){
        try{
            InetAddress address=InetAddress.getByName(host);
            return address.getHostName();
        }catch(Exception ex){
            System.out.println(ex);
            return "";
        }
    }
    
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
            System.out.println(ex);
        }
        return "";
    }
    
}
