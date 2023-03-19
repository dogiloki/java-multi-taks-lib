package multitaks.interfaces;

import java.util.List;

/**
 *
 * @author dogi_
 */

public interface ActionOneToMany<T>{
    
    public void add(T item);
    public void set(List<T> items);
    public void remove(T item);
    public boolean exists(T item);
    
}
