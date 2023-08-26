package com.dogiloki.multitaks;

import java.awt.Frame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JOptionPane;
import com.dogiloki.multitaks.directory.DirectoryList;
import com.dogiloki.multitaks.directory.Storage;

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