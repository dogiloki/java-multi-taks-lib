package com.dogiloki.multitaks.logger.enums;

import java.awt.Color;

/**
 *
 * @author dogiloki
 */

public enum LogType{
    
    INFO("[INFO]",Color.BLACK),
    ERROR("[ERROR]",new Color(200,0,0)),
    WARNING("[WARNING]",new Color(255,140,0)),
    DEBUG("[DEBUG]",new Color(0,0,200)),
    NOTICE("[NOTICE]",new Color(150,0,150)),
    CRITICAL("[CRITICAL]",new Color(150,0,0)),
    ALERT("[ALERT]",new Color(220,20,60)),
    EMERGENCY("[EMERGENCY]",Color.RED);
    
    private String text;
    private Color color;
    
    private LogType(String text, Color color){
        this.text=text;
        this.color=color;
    }
    
    public String getText(){
        return this.text;
    }
    
    public Color getColor(){
        return this.color;
    }
    
}
