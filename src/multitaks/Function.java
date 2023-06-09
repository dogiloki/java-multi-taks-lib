package multitaks;

import java.awt.Dimension;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JPanel;

/**
 *
 * @author dogi_
 */

public class Function<T>{
    
    public static String date_format="dd-MM-yyyy HH:mm:ss";
    public static String number_format="#,###.##";
    
    /**
     * Obtener fecha y hora actual con el formato asignado en Function.date_format por defecto (dd-MM-yyyy HH:mm:ss)
     * @return Devuelve String con el fomato asígnado
     */
    public String getDateTime(){
        return new SimpleDateFormat(Function.date_format).format(new Date());
    }
    
    /**
     * Formato de número (dinero) con el formato asignado en Function.number_format por defecto (#,###.##)
     * @param num Número a convertir
     * @return Devuelve String con el fomato asígnado
     */
    public String formatNumber(String num){
        DecimalFormat formato=new DecimalFormat(Function.number_format);
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
    
    /**
     * Verificar si esta dentro del rango
     * @param num Número a verificar
     * @param min Número máximo
     * @param max Númer minímo
     * @return Indicar si esta dentro del rango
     */
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
        Function.resizablePanel(panel1,panel2);
        panel1.removeAll();
        panel1.add(panel2);
        panel1.updateUI();
    }
    
    /**
     * Dimencionar un panel en base a otro panel
     * @param panel1 JPanel principal (obtener las dimenciones)
     * @param panel2 JPanel secundario (asignar las dimenciones)
     */
    public static void resizablePanel(JPanel panel1, JPanel panel2){
        panel2.setBounds(0,0,panel1.getWidth(),panel1.getHeight());
        panel1.updateUI();
    }
    
}
