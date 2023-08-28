package database;

import com.dogiloki.multitaks.database.Database;
import com.dogiloki.multitaks.database.ModelDB;
import com.dogiloki.multitaks.database.filter.ComparisonExpression;
import com.dogiloki.multitaks.database.filter.Filter;
import com.dogiloki.multitaks.database.filter.LogicalExpression;
import com.dogiloki.multitaks.database.filter.enums.CompOp;
import com.dogiloki.multitaks.database.filter.enums.LogicalOp;
import com.dogiloki.multitaks.database.order.Sorting;
import com.dogiloki.multitaks.database.order.BubbleSort;
import com.dogiloki.multitaks.database.order.SortingBy;
import com.dogiloki.multitaks.database.order.enums.OrderBy;
import com.dogiloki.multitaks.database.record.Record;
import com.dogiloki.multitaks.database.record.RecordList;
import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author dogi_
 */

public class Test{
    
    public Test(){
        RecordList<Persona> personas=new Persona().getCollection().find(Filter.like("nombre","J")).orderBy(OrderBy.ASC,"nombre");
        Persona p;
        while((p=personas.next())!=null){
            System.out.println(p.nombre);
        }
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}