package test.storage;

import com.dogiloki.multitaks.directory.annotations.Key;

/**
 *
 * @author dogi_
 */

public class Direccion{
    
    @Key("calle")
    public String calle;
    @Key("cp")
    public int cp;
    
    public Direccion(){
    
    }
    
}
