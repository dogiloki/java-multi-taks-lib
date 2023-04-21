package test;

import database.DataBase;
import database.ObjectId;
import multitaks.GlobalField;

/**
 *
 * @author dogi_
 */

public class Test{
    
    public Test(){
        DataBase db=new DataBase("database");
    }
    
    public static void main(String[] args){
        new Test();
    }
    
}
