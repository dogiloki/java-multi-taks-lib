package multitaks.logger.enums;

/**
 *
 * @author dogiloki
 */

public enum LogType{
    
    INFO("[INFO]"),
    ERROR("[ERROR]"),
    WARNING("[WARNING]"),
    DEBUG("[DEBUG]"),
    NOTICE("[NOTICEC]"),
    CRITICAL("[CRITICAL]"),
    ALERT("[ALERT]"),
    EMERGENCY("[EMERGENCY]");
    
    private String text;
    
    private LogType(String text){
        this.text=text;
    }
    
    public String getText(){
        return this.text;
    }
    
}
