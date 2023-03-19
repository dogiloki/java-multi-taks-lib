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
        JSON json=new JSON("{\"documents\":\"\",\"name\":\"Julio\",\"pueba_clase\":{\"calle\":\"Morelos\",\"cp\":55846},\"edad\":20,\"direction\":\"\"}");
        System.out.println(json.getValue("pueba_clase","calle"));
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}
