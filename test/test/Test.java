package test;

import database.ModelDB;
import database.Record;

/**
 *
 * @author dogi_
 */

public class Test{
    
    public Test(){
        ModelDB db=new ModelDB("db");
    }
    
    public static void main(String[] args){
        new Test();
    }
    
}
