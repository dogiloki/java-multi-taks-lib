package multitaks.logger;

import multitaks.directory.ModelDirectory;
import multitaks.directory.annotations.Directory;
import multitaks.directory.enums.DirectoryType;
import multitaks.loguer.enums.LogType;

/**
 *
 * @author dogiloki
 */

@Directory(type=DirectoryType.FILE)
public class Log extends ModelDirectory{
    
    public Log(String src){
        super.aim(this,src);
    }
    
    public void add(String message){
        this.append(message+"\n");
    }
    
    public void add(LogType log_type, String message){
        this.append("["+Logger.getTimeCurrent()+"] "+log_type.getText()+" "+message+"\n");
    }
    
}
