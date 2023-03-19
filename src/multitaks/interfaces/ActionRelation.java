package multitaks.interfaces;

import java.util.List;

/**
 *
 * @author dogi_
 */

public interface ActionRelation<T>{
    
    public void removeAll();
    public List<T> getAll();
    
}
