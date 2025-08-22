package com.dogiloki.multitaks.logger;

/**
 *
 * @author _dogi
 */

public class AppLogger{
    
    private static final Logger APP_LOGGER=new Logger();
    
    public static void probe(){
        AppLogger.info("This is a trial message");
        AppLogger.error("This is a trial message");
        AppLogger.warning("This is a trial message");
        AppLogger.debug("This is a trial message");
        AppLogger.notice("This is a trial message");
        AppLogger.critical("This is a trial message");
        AppLogger.alert("This is a trial message");
        AppLogger.emergency("This is a trial message");
        
    }
    
    public static void add(String message){
        AppLogger.APP_LOGGER.add(message);
    }
    
    public static void info(String message){
        AppLogger.APP_LOGGER.info(message);
    }
    
    public static void error(String message){
        AppLogger.APP_LOGGER.error(message);
    }
    
    public static void warning(String message){
        AppLogger.APP_LOGGER.warning(message);
    }
    
    public static void debug(String message){
        AppLogger.APP_LOGGER.debug(message);
    }
    
    public static void notice(String message){
        AppLogger.APP_LOGGER.notice(message);
    }
    
    public static void critical(String message){
        AppLogger.APP_LOGGER.critical(message);
    }
    
    public static void alert(String message){
        AppLogger.APP_LOGGER.alert(message);
    }
    
    public static void emergency(String message){
        AppLogger.APP_LOGGER.emergency(message);
    }
    
    public static Logger logger(){
        return AppLogger.APP_LOGGER;
    }
    
}
