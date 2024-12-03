package database;

import com.dogiloki.multitaks.database.ModelDB;
import com.dogiloki.multitaks.database.annotations.Collect;
import com.dogiloki.multitaks.dataformat.annotations.FieldFormat;
import com.dogiloki.multitaks.dataformat.enums.TypeFieldFormat;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import com.google.gson.annotations.Expose;

/**
 *
 * @author dogi_
 */

@Collect(src="persona")
public class Persona extends ModelDB{
    
    @FieldFormat(label="Nombre",type=TypeFieldFormat.TEXT) @Expose
    public String name;

    @FieldFormat(label="Apellido",type=TypeFieldFormat.TEXT) @Expose
    public String surname;
    
    @FieldFormat(label="Correo electrónico",type=TypeFieldFormat.TEXT) @Expose
    public String email;
    
    @FieldFormat(label="Dirección",type=TypeFieldFormat.TEXT) @Expose
    public String address;
    
    @FieldFormat(label="Edad",type=TypeFieldFormat.TEXT) @Expose
    public int edad;
    
    @FieldFormat(label="Tipo de medida",type=TypeFieldFormat.LIST,list=DirectoryType.class) @Expose
    public String tipo;
    
    public Persona(){
        
    }
    
}
