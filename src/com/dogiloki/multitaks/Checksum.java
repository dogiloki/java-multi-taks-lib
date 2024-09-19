package com.dogiloki.multitaks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.Adler32;

/**
 *
 * @author dogi_
 */

public class Checksum{
    
    public static String chechk(String file){
        try{
            Path path=Paths.get(file);
            byte data[]=Files.readAllBytes(path);
            Adler32 adler=new Adler32();
            adler.update(data);
            System.out.println(adler);
            long adler_check=adler.getValue();
            return String.valueOf(adler_check);
        }catch(IOException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
}
