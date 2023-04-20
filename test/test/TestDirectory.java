package test;

import java.util.ArrayList;
import java.util.List;
import multitaks.Function;
import multitaks.annotations.directory.Directory;
import multitaks.annotations.directory.Key;
import multitaks.directory.ModelDirectory;
import multitaks.enums.DirectoryType;
import multitaks.enums.FieldType;

/**
 *
 * @author dogi_
 */
@Directory(type=DirectoryType.JSON)
public class TestDirectory extends ModelDirectory{
    
    @Key(value="name")
    public String nombre="Julio";
    @Key(value="edad")
    public int edad=20;
    @Key(value="direccion",type=FieldType.CLASS)
    public Direccion direccion=new Direccion();
    @Key(value="direcciones",type=FieldType.LIST)
    public List<Direccion> direcciones=new ArrayList<>();
    
    public TestDirectory(){
        super.run(this,"prueba.json");
        this.direcciones.add(new Direccion());
        this.direcciones.add(new Direccion());
        this.direcciones.add(new Direccion());
        System.out.println(this.direcciones.get(0).cp);
        //this.save();
    }
    
    public static void main(String args[]){
        new TestDirectory();
    }
    
}
