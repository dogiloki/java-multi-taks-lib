package com.dogiloki.multitaks.logger;

import com.dogiloki.multitaks.directory.ModelDirectory;
import com.dogiloki.multitaks.directory.annotations.Directory;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import com.dogiloki.multitaks.logger.enums.LogType;

/**
 *
 * @author dogiloki
 */

@Directory(type=DirectoryType.FILE)
public class Log extends ModelDirectory{
    
    public static String format( String message){
        return "["+Logger.getTimeCurrent()+"] "+message;
    }
    
    public static String format(LogType log_type, String message){
        return "["+Logger.getTimeCurrent()+"] "+log_type.getText()+" "+message;
    }
    
    public Log(String src){
        super.aim(src);
    }
    
    public void add(String message){
        this.append(message+"\n");
    }
    
    public void add(LogType log_type, String message){
        this.append("["+Logger.getTimeCurrent()+"] "+log_type.getText()+" "+message+"\n");
    }
    
}
