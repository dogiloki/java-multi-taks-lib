package test;

import database.Database;
import database.Record;
import multitaks.directory.ModelDirectory;

/**
 *
 * @author dogi_
 */

public class Test{
    
    public Test(){
        //Database db=new Database("db");
        Persona per=(Persona)Persona.aim("personaa.json",Persona.class);
        System.out.println(per.name);
        System.out.println(per.edad);
        per.name="dsa";
        per.save();
    }
    
    public static void main(String[] args){
        new Test();
    }
    
}
