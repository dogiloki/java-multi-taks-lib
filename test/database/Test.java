package database;

import com.dogiloki.multitaks.database.Collection;
import com.dogiloki.multitaks.database.Database;
import com.dogiloki.multitaks.database.ModelDB;
import com.dogiloki.multitaks.database.filter.ComparisonExpression;
import com.dogiloki.multitaks.database.filter.Filter;
import com.dogiloki.multitaks.database.filter.LogicalExpression;
import com.dogiloki.multitaks.database.filter.enums.CompOp;
import com.dogiloki.multitaks.database.filter.enums.LogicalOp;
import com.dogiloki.multitaks.datastructure.sorting.Sorting;
import com.dogiloki.multitaks.datastructure.sorting.SortingBy;
import com.dogiloki.multitaks.datastructure.order.enums.OrderBy;
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
        Database db=new Database("db");
        Collection c=db.collection("personas");
        Record r=new Record();
        r.set("nombre","Julio2");
        Filter f=Filter.or(
                Filter.eq("nombre","Julio"),
                Filter.and(
                        Filter.like("nombre","j"),
                        Filter.gte("edad",18),
                        Filter.lt("edad",32)
                ),
                Filter.eq("edad",60)
        );
        c.delete();
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}