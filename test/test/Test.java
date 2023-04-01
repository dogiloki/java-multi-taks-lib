package test;

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
public class Test extends ModelDirectory{
    
    @Key(value="name")
    public String nombre="Julio";
    @Key(value="edad")
    public int edad=20;
    @Key(value="direccion",type=FieldType.CLASS)
    public Direccion direccion=new Direccion();
    
    public Test(){
        super.run(this,"prueba.json");
        System.out.println(this.direccion.cp);
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}
