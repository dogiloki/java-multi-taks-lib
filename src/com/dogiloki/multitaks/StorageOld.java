package com.dogiloki.multitaks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author dogi_
 */

public class StorageOld{
   
    public static String[] readFile(Class _class, String ruta){
        ArrayList<String> lineas=new ArrayList<>();
        try{
            InputStream in=_class.getResourceAsStream("/"+ruta);
            BufferedReader bf=new BufferedReader(new InputStreamReader(in));
            String linea;
            while((linea=bf.readLine())!=null){
                lineas.add(linea);
            }
            return lineas.toArray(new String[lineas.size()]);
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null,ex,"Error",JOptionPane.ERROR_MESSAGE);
            return new String[0];
        }
    }
    


}