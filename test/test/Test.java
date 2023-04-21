package test;

import database.DataBase;
import database.Record;

/**
 *
 * @author dogi_
 */

public class Test{
    
    public Test(){
        DataBase db=new DataBase("database");
        db.collection("alumnos").insert(new Record("nombre","apellido").append("Julio","Villanueva"));
    }
    
    public static void main(String[] args){
        new Test();
    }
    
}
