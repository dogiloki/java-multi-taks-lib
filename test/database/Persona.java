package database;

import com.dogiloki.multitaks.database.ModelDB;
import com.dogiloki.multitaks.database.annotations.Collect;
import com.google.gson.annotations.Expose;

/**
 *
 * @author dogi_
 */

@Collect(src="personas")
public class Persona extends ModelDB{
    
    @Expose
    public String nombre;
    
    @Expose
    public String apellido;
    
    @Expose
    public int edad;
    
    public Persona(){
        
    }
    
}
