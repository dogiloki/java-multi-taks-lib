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
        //db.collection("alumnos").insert(new Record("nombre","Julio").add("apellido","Villanueva"));
        Record record=db.collection("alumnos").find(new Record().setId("af8c8ec92754d16de2e27825c66fc4072c4911ec74b829eb0266f313ad77")).get(0);
        System.out.println(record.get("nombre"));
    }
    
    public static void main(String[] args){
        new Test();
    }
    
}
