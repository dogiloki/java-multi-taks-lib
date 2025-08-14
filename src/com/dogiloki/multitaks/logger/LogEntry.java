package com.dogiloki.multitaks.logger;

import com.dogiloki.multitaks.logger.enums.LogType;

/**
 *
 * @author _dogi
 */

public class LogEntry{
    
    private final String time;
    private final LogType type;
    private final String message;
    
    public LogEntry(String time, LogType type, String message){
        this.time=time;
        this.type=type;
        this.message=message;
    }
    
    public String time(){
        return this.time;
    }
    
    public LogType type(){
        return this.type;
    }
    
    public String message(){
        return this.message;
    }
    
    @Override
    public String toString(){
        return "["+this.time+"] "+this.type.getText()+" "+this.message;
    }
    
}
