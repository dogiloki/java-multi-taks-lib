package database;

import com.dogiloki.multitaks.database.Database;
import com.dogiloki.multitaks.database.ModelDB;
import com.dogiloki.multitaks.database.filter.ComparisonExpression;
import com.dogiloki.multitaks.database.filter.Filter;
import com.dogiloki.multitaks.database.filter.LogicalExpression;
import com.dogiloki.multitaks.database.filter.enums.CompOp;
import com.dogiloki.multitaks.database.filter.enums.LogicalOp;
import com.dogiloki.multitaks.database.record.Record;
import com.dogiloki.multitaks.database.record.RecordList;
import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author dogi_
 */

public class Test{
    
    public Test(){
        RecordList<Persona> personas=new Persona().getCollection().find(Filter.like("nombre","Julio"));
        Persona p;
        while((p=personas.next())!=null){
            p.edad=20;
            p.save();
            System.out.println(p.getLineNumber());
        }
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}