package test;

import multitaks.annotations.directory.Directory;
import multitaks.annotations.directory.Key;
import multitaks.dataformat.JSON;
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
        //JSON json=new JSON("{\"key_name\":\"Julio\",\"key_edad\":20,\"key_peso\":58.0,\"key\":{\"key_name\":\"Julio\",\"key_edad\":20,\"key_peso\":58.0}}");
        super.run(this,"E:\\Escritorio\\hola.json");
        this.save();
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}
