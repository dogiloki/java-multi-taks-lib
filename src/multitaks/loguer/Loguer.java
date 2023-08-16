package multitaks.loguer;

import java.text.SimpleDateFormat;
import java.util.Date;
import multitaks.Function;
import multitaks.GlobalVar;
import multitaks.directory.ModelDirectory;
import multitaks.directory.annotations.Directory;
import multitaks.directory.enums.DirectoryType;
import multitaks.loguer.contracts.LogImpl;
import multitaks.loguer.enums.LogType;

/**
 *
 * @author dogiloki
 */

@Directory(type=DirectoryType.FOLDER)
public class Loguer extends ModelDirectory implements LogImpl{
    
    private static String day_format="dd-MM-yyyy";
    private static String time_format="HH:mm:ss";
    public static String getDayCurrent(){
        return new SimpleDateFormat(Loguer.day_format).format(new Date());
    }
    public static String getTimeCurrent(){
        return new SimpleDateFormat(Loguer.time_format).format(new Date());
    }
    
    private Log log;
    
    public Loguer(){
        this.createFielLog(null);
    }
    
    public Loguer(String src){
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
        this.log=new Log(this.getSrc()+"/"+Loguer.getDayCurrent()+".txt");
    }
    
    @Override
    public void add(String message){
        this.getLog().add(message);
    }
    
    @Override
    public void info(String message){
        this.getLog().add(LogType.INFO,message);
    }
    
    @Override
    public void error(String message){
        this.getLog().add(LogType.ERROR,message);
    }

    @Override
    public void warning(String message){
        this.getLog().add(LogType.WARNING,message);
    }

    @Override
    public void debug(String message){
        this.getLog().add(LogType.DEBUG,message);
    }

    @Override
    public void notice(String message){
        this.getLog().add(LogType.NOTICE,message);
    }

    @Override
    public void critical(String message){
        this.getLog().add(LogType.CRITICAL,message);
    }

    @Override
    public void alert(String message){
        this.getLog().add(LogType.ALERT,message);
    }

    @Override
    public void emergency(String message){
        this.getLog().add(LogType.EMERGENCY,message);
    }
    
    public Log getLog(){
        return this.log;
    }
    
}
