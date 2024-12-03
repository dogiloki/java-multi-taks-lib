package com.dogiloki.multitaks;

import com.dogiloki.multitaks.directory.ConfigFile;
import com.dogiloki.multitaks.directory.annotations.Directory;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import com.google.gson.annotations.Expose;
import javax.swing.JOptionPane;

/**
 *
 * @author _dogi
 */

@Directory(src="properties.cfg",type=DirectoryType.ENV)
public class Properties{
    
    @Expose
    public static String DATABASE_FOLDER="db";
    
    @Expose
    public static String STORAGE_FOLDER="storage";
    
    @Expose
    public static String SERVER_FOLDER="server";
    
    @Expose
    public static String LOCALE_FOLDER="locale";
    
    public static void load(){
        try{
            ConfigFile.load(Properties.class);
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
