package test.storage;

import com.dogiloki.multitaks.directory.ModelDirectory;
import com.dogiloki.multitaks.directory.annotations.Directory;
import com.dogiloki.multitaks.directory.annotations.Key;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dogi_
 */

@Directory(type=DirectoryType.JSON,src="persona.txt")
public class Persona extends ModelDirectory{
    
    @Key("nombre")
    public String nombre;
    @Key("edad")
    public int edad;
    @Key("direccion")
    public Direccion direccion;
    @Key("comentarios")
    public List<Comentario> comentarios=new ArrayList<>();
    
    public Persona(){
        
    }
    
}
