package test.storage;

import com.dogiloki.multitaks.directory.ModelDirectory;
import com.dogiloki.multitaks.directory.annotations.Directory;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dogi_
 */

@Directory(type=DirectoryType.JSON)
public class Persona extends ModelDirectory{
    
    @Expose
    public String nombre;
    @Expose
    public int edad;
    @Expose
    public Direccion direccion;
    @Expose
    public List<Comentario> comentarios=new ArrayList<>();
    
    public Persona(){
        
    }
    
}
