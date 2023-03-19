package test;

import multitaks.annotations.directory.Directory;
import multitaks.annotations.directory.Key;
import multitaks.directory.ModelDirectory;
import multitaks.directory.Storage;
import multitaks.enums.DirectoryType;
import multitaks.enums.FieldType;
import multitaks.relations.OneByOne;
import multitaks.relations.OneToMany;
import multitaks.relations.Relation;

/**
 *
 * @author dogi_
 */

@Directory(type=DirectoryType.JSON)
public class Persona extends ModelDirectory{
    
    @Key(value="name")
    public String name="Julio";
    @Key(value="edad")
    public int edad=20;
    @Key(value="direction",type=FieldType.OneByOne)
    public OneByOne<Direccion> direction=new OneByOne<>();
    @Key(value="documents",type=FieldType.OneToMany)
    public OneToMany<Documentos> documentos=new OneToMany<>();
    @Key(value="pueba_clase",type=FieldType.CLASS)
    public Direccion pueba_clase=new Direccion();
    
    public Persona(){
        super.run(this,"src\\PUEBA.json");
        this.save();
    }
    
    public static void main(String[] args){
        new Persona();
    }
    
}
