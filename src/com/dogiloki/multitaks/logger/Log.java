package com.dogiloki.multitaks.logger;

import com.dogiloki.multitaks.directory.ListFields;
import com.dogiloki.multitaks.directory.ModelDirectory;
import com.dogiloki.multitaks.directory.annotations.Directory;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import com.dogiloki.multitaks.logger.contracts.LogListener;
import com.dogiloki.multitaks.logger.enums.LogType;
import javax.swing.JOptionPane;

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
    
    private ListFields<LogListener> listeners=new ListFields();
    private boolean show_message=false;
    
    public Log(String src){
        super.aim(src);
        this.notifyListener(null,this.getSrc());
    }
    
    public void showMessage(){
        this.show_message=true;
    }
    
    public void addListener(LogListener listener){
        this.listeners.add(listener);
    }
    
    public void removeListener(LogListener listener){
        this.listeners.remove(listener);
    }
    
    public void notifyListener(LogEntry entry, String created_path){
        for(LogListener listener:this.listeners){
            if(entry!=null){
                listener.onLogAdded(entry);
            }
            if(created_path!=null){
                listener.onLogFileCreated(created_path);
            }
        }
    }
    
    public void add(String message){
        LogEntry entry=new LogEntry(Logger.getTimeCurrent(),null,message);
        this.append(message+"\n");
        this.notifyListener(entry,null);
        this.displayMessage(entry);
    }
    
    public void add(LogType log_type, String message){
        LogEntry entry=new LogEntry(Logger.getTimeCurrent(),log_type,message);
        this.append(entry.toString()+"\n");
        this.notifyListener(entry,null);
        this.displayMessage(entry);
    }
    
    public void displayMessage(LogEntry entry){
        if(!this.show_message) return;
        int message_type;
        switch(entry.type()){
            case INFO: message_type=JOptionPane.INFORMATION_MESSAGE; break;
            case WARNING: message_type=JOptionPane.WARNING_MESSAGE; break;
            case ERROR:
            case CRITICAL:
            case ALERT:
            case EMERGENCY: message_type=JOptionPane.ERROR_MESSAGE; break;
            case DEBUG:
            case NOTICE: message_type=JOptionPane.PLAIN_MESSAGE; break;
            default: message_type=JOptionPane.PLAIN_MESSAGE;
        }
        JOptionPane.showMessageDialog(null,entry.message(),entry.type().toString(),message_type);
        this.show_message=false;
    }
    
}
