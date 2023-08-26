package database;

import com.dogiloki.multitaks.database.Database;
import com.dogiloki.multitaks.database.filter.ComparisonExpression;
import com.dogiloki.multitaks.database.filter.Filter;
import com.dogiloki.multitaks.database.filter.LogicalExpression;
import com.dogiloki.multitaks.database.filter.enums.CompOp;
import com.dogiloki.multitaks.database.filter.enums.LogicalOp;
import com.dogiloki.multitaks.database.record.Record;
import com.dogiloki.multitaks.database.record.RecordList;

/**
 *
 * @author dogi_
 */

public class Test{
    
    public Test(){
        Database db=new Database("db");
        Record r=new Record()
                .set("nombre","Marlón")
                .set("apellido","Torrez")
                .set("edad",19);
        //db.collection("personas").insert(r);
        Filter f=new LogicalExpression(LogicalOp.OR,
                new ComparisonExpression("nombre",CompOp.EQ,"Marlón"),
                new ComparisonExpression("apellido",CompOp.EQ,"Torrez")
        );
        //r.set("nombre","dasa");
        //db.collection("personas").update(f,r);
        RecordList personas=db.collection("personas").find(f);
        Record persona;
        while((persona=personas.next())!=null){
            System.out.println(persona.getFields());
        }
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}