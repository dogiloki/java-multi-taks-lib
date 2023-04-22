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
    }
    
    public static void main(String[] args){
        new Test();
    }
    
}
