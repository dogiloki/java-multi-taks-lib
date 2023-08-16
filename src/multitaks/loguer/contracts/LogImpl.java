package multitaks.loguer.contracts;

import multitaks.loguer.Loguer;

/**
 *
 * @author dogiloki
 */
public interface LogImpl{
    
    public Loguer add(String message);
    public Loguer info(String message);
    public Loguer error(String message);
    public Loguer warning(String message);
    public Loguer debug(String message);
    public Loguer notice(String message);
    public Loguer critical(String message);
    public Loguer alert(String message);
    public Loguer emergency(String message);
    
}
