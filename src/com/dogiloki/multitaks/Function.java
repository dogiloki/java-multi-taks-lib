package com.dogiloki.multitaks;

import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
     * @param min Número mínímo
     * @param max Númer máximo
     * @return Indicar si esta dentro del rango
     */
    public static boolean withinRange(int num, int min, int max){
        return num>=min && num<=max;
    }
    
    /**
     * Hace uso de Objects.compareTo, antes comprobando si es nulo, es el primer valor es null sera menor al segundo y viceversa.
     * @param v1 Primer valor
     * @param v2 Segundo valor
     * @return Indicar si el primer valor es mayor al segundo con un 1 si es lo contrario con un -1 si son iguales con un 0
     */
    public static int compareTo(Object v1, Object v2){
        if(v1==null && v2!=null){
            return -1;
        }
        if(v1!=null && v2==null){
            return 1;
        }
        if(v1==null || v2==null){
            return 0;
        }
        return v1.toString().compareTo(v2.toString());
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
    
    /**
     * Generar un icono con dimenciones especificar a partir de una imagen
     */
    public static Icon generateIcon(String path, int width, int height){
        ImageIcon image=new ImageIcon(path);
        Icon icon=new ImageIcon(image.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
        return icon;
    }
    
    public static Icon generateIcon(ImageIcon image, int width, int height){
        Icon icon=new ImageIcon(image.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
        return icon;
    }
    
    /**
     * Crear una ImageIcon apartir de una URL
     * @param url dirección http de la imagen
     * @return ImageIcon
     */
    public static ImageIcon createImageFromURL(String url){
        try{
            URL image_url=new URL(url);
            
            Iterator<ImageReader> readers=ImageIO.getImageReadersBySuffix("webp");
            if(readers.hasNext()){
                ImageReader reader=readers.next();
                try{
                    reader.setInput(ImageIO.createImageInputStream(image_url.openStream()));
                    Image image=reader.read(0);
                    return new ImageIcon(image);
                }finally{
                    reader.dispose();
                }
            }
            
            Image image=ImageIO.read(image_url);
            if(image!=null){
                return new ImageIcon(image);
            }
        }catch(Exception ex){
            
        }
        return null;
    }
    
    /**
     * Obtener un elemento aleatorio de una lista
     * @param <T> Tipo de dato de la lista
     * @param items La lista con los datos
     * @return Elemento de la lista
     */
    public static <T> T random(List<T> items){
        if(items.isEmpty()){
            return null;
        }
        return items.get(new Random().nextInt(items.size()));
    }
    
    public static void iterate(int steps, Consumer<Integer> action){
        for(int step=1; step<=steps; step++){
            action.accept(step);
        }
    }
    
}
