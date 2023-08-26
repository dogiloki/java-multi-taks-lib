package database;

import com.dogiloki.multitaks.database.Collection;
import com.dogiloki.multitaks.database.Database;
import com.dogiloki.multitaks.database.Record;
import com.dogiloki.multitaks.database.RecordList;

/**
 *
 * @author dogi_
 */
public class Test{
    
    public Test(){
        Database db=new Database("db");
        Record record=new Record();
        record.set("nombre","dsa");
        RecordList personas=db.collection("personas").find(record);
        Record persona;
        while((persona=personas.next())!=null){
            System.out.println(persona.get("nombre"));
        }
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}