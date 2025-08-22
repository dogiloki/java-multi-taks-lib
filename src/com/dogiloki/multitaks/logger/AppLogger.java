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
    
    public static Logger add(String message){
        return AppLogger.APP_LOGGER.add(message);
    }
    
    public static Logger info(String message){
        return AppLogger.APP_LOGGER.info(message);
    }
    
    public static Logger error(String message){
        return AppLogger.APP_LOGGER.error(message);
    }
    
    public static Logger warning(String message){
        return AppLogger.APP_LOGGER.warning(message);
    }
    
    public static Logger debug(String message){
        return AppLogger.APP_LOGGER.debug(message);
    }
    
    public static Logger notice(String message){
        return AppLogger.APP_LOGGER.notice(message);
    }
    
    public static Logger critical(String message){
        return AppLogger.APP_LOGGER.critical(message);
    }
    
    public static Logger alert(String message){
        return AppLogger.APP_LOGGER.alert(message);
    }
    
    public static Logger emergency(String message){
        return AppLogger.APP_LOGGER.emergency(message);
    }
    
    public static Logger logger(){
        return AppLogger.APP_LOGGER;
    }
    
}
