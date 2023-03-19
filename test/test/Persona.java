package test;

import multitaks.relations.OneByOne;
import multitaks.relations.OneToMany;
import multitaks.relations.Relation;

/**
 *
 * @author dogi_
 */

public class Persona extends Relation{
    
    public String name="Julio";
    public int edad=20;
    public OneByOne<Direccion> direction=new OneByOne<>();
    public OneToMany<Documentos> documentos=new OneToMany<>();
    
    public Persona(){
        super.aim(this);
        this.direction.set(new Direccion());
        this.documentos.add(new Documentos());
        this.documentos.add(new Documentos());
        this.documentos.add(new Documentos());
        System.out.println(this.getRelations());
        this.removeRelations();
        System.out.println(this.getRelations());
    }
    
    public static void main(String[] args){
        new Persona();
    }
    
}
