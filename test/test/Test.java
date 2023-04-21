package test;

import database.DataBase;
import database.Record;

/**
 *
 * @author dogi_
 */

public class Test{
    
    public Test(){
        DataBase db=new DataBase("db");
        //db.collection("alumnos").insert(new Record("nombre","Juan").add("apellido","Lopéz"));
        db.collection("alumnos").find(new Record().setId("26931d574190b654d4dfaebdb0c29f051d477e67afe4dd4276df19fb551c7c"));
    }
    
    public static void main(String[] args){
        new Test();
    }
    
}
