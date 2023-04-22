package test;

import java.util.ArrayList;
import java.util.List;
import multitaks.annotations.directory.Directory;
import multitaks.annotations.directory.Key;
import multitaks.directory.ModelDirectory;
import multitaks.enums.DirectoryType;

/**
 *
 * @author dogi_
 */

@Directory(type=DirectoryType.JSON)
public class Persona extends ModelDirectory{
    
    @Key(value="name")
    public String name;
    
    @Key(value="edad")
    public int edad;
    
    @Key(value="direction")
    public Direccion direccion=new Direccion();
    
    @Key(value="documents")
    public List<Documento> documentos=new ArrayList<>();

    public Persona(){
        super("persona.json");
    }
    
}
