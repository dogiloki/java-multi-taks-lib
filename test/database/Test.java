package database;

import com.dogiloki.multitaks.database.Database;
import com.dogiloki.multitaks.database.filter.ComparisonExpression;
import com.dogiloki.multitaks.database.filter.Filter;
import com.dogiloki.multitaks.database.filter.enums.CompOp;
import com.dogiloki.multitaks.database.record.Record;
import com.dogiloki.multitaks.database.record.RecordList;

/**
 *
 * @author dogi_
 */
public class Test{
    
    public Test(){
        Database db=new Database("db");
        Record r=new Record();
        Filter f=new Filter();
        RecordList personas=db.collection("personas").find(f);
        Record persona;
        while((persona=personas.next())!=null){
            System.out.println(persona.get("nombre"));
        }
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}