package multitaks.loguer.contracts;

import multitaks.logger.Logger;

/**
 *
 * @author dogiloki
 */
public interface LogImpl{
    
    public Logger add(String message);
    public Logger info(String message);
    public Logger error(String message);
    public Logger warning(String message);
    public Logger debug(String message);
    public Logger notice(String message);
    public Logger critical(String message);
    public Logger alert(String message);
    public Logger emergency(String message);
    
}
