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
        Persona p=new Persona();
        System.out.println(p.getInstance());
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}