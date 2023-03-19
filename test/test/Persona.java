package test;

import multitaks.relations.OneByOne;
import multitaks.relations.Relation;

/**
 *
 * @author dogi_
 */

public class Persona extends Relation{
    
    public String name="Julio";
    public int edad=20;
    public OneByOne<Direccion> direction=new OneByOne<>();
    
    public Persona(){
        super.aim(this);
        this.direction.set(new Direccion());
        System.out.println(this.getAll());
    }
    
    public static void main(String[] args){
        new Persona();
    }
    
}
