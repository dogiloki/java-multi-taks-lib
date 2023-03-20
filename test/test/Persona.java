package test;

import java.util.ArrayList;
import java.util.List;
import multitaks.annotations.directory.Directory;
import multitaks.annotations.directory.Key;
import multitaks.annotations.relation.Relation;
import multitaks.directory.ModelDirectory;
import multitaks.directory.Storage;
import multitaks.enums.DirectoryType;
import multitaks.enums.FieldType;
import multitaks.enums.RelationType;
import multitaks.relation.ModelRelation;

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
    
    @Key(value="direction",type=FieldType.CLASS)
    public Direccion direction;
    
    @Key(value="documents",type=FieldType.LIST)
    @Relation(type=RelationType.OneToMany)
    public List<Documentos> documentos=new ArrayList<>();
    
    @Key(value="pueba_clase",type=FieldType.CLASS)
    @Relation(type=RelationType.OneByOne)
    public Direccion pueba_clase=new Direccion();
    
    public Persona(){
        super.run(this,"src\\PUEBA");
        this.direction=new Direccion();
        this.documentos.add(new Documentos());
        this.documentos.add(new Documentos());
        this.documentos.add(new Documentos());
        this.save();
        System.out.println(ModelRelation.ami(this).getRelations());
        ModelRelation.ami(this).clearRelations();
        System.out.println(ModelRelation.ami(this).getRelations());
    }
    
    public static void main(String[] args){
        new Persona();
    }
    
}
