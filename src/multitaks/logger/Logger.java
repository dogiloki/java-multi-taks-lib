package multitaks.logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import multitaks.Function;
import multitaks.GlobalVar;
import multitaks.directory.ModelDirectory;
import multitaks.directory.annotations.Directory;
import multitaks.directory.enums.DirectoryType;
import multitaks.logger.contracts.LogImpl;
import multitaks.logger.enums.LogType;

/**
 *
 * @author dogiloki
 */

@Directory(type=DirectoryType.FOLDER)
public class Logger extends ModelDirectory implements LogImpl{
    
    private static String day_format="dd-MM-yyyy";
    private static String time_format="HH:mm:ss";
    public static String getDayCurrent(){
        return new SimpleDateFormat(Logger.day_format).format(new Date());
    }
    public static String getTimeCurrent(){
        return new SimpleDateFormat(Logger.time_format).format(new Date());
    }
    
    private Log log;
    private boolean mode_debug=true;
    
    public Logger(){
        this.createFielLog(null);
    }
    
    public Logger(String src){
        this.createFielLog(src);
    }
    
    private void createFielLog(String src){
        if(src==null){
            src=(String)Function.assign(GlobalVar.get("loguer"),"log");
            if(src==null){
                GlobalVar.set("loguer",src);
            }
        }
        super.aim(this,src);
        this.log=new Log(this.getSrc()+"/"+Logger.getDayCurrent()+".txt");
    }
    
    public void modeDebug(boolean op){
        this.mode_debug=op;
    }
    
    public boolean isModeDebug(){
        return this.mode_debug;
    }
    
    @Override
    public Logger add(String message){
        if(this.isModeDebug()){
            this.getLog().add(message);
        }
        return this;
    }
    
    @Override
    public Logger info(String message){
        if(this.isModeDebug()){
            this.getLog().add(LogType.INFO,message);
        }
        return this;
    }
    
    @Override
    public Logger error(String message){
        if(this.isModeDebug()){
            this.getLog().add(LogType.ERROR,message);
        }
        return this;
    }

    @Override
    public Logger warning(String message){
        if(this.isModeDebug()){
            this.getLog().add(LogType.WARNING,message);
        }
        return this;
    }

    @Override
    public Logger debug(String message){
        if(this.isModeDebug()){
            this.getLog().add(LogType.DEBUG,message);
        }
        return this;
    }

    @Override
    public Logger notice(String message){
        if(this.isModeDebug()){
            this.getLog().add(LogType.NOTICE,message);
        }
        return this;
    }

    @Override
    public Logger critical(String message){
        if(this.isModeDebug()){
            this.getLog().add(LogType.CRITICAL,message);
        }
        return this;
    }

    @Override
    public Logger alert(String message){
        if(this.isModeDebug()){
            this.getLog().add(LogType.ALERT,message);
        }
        return this;
    }

    @Override
    public Logger emergency(String message){
        this.getLog().add(LogType.EMERGENCY,message);
        return this;
    }
    
    public Log getLog(){
        return this.log;
    }
    
}
