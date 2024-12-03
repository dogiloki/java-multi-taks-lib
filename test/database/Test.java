package database;

import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.GlobalVar;
import com.dogiloki.multitaks.database.DatabaseConfig;
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
import com.dogiloki.multitaks.datastructure.sorting.enums.OrderBy;
import com.dogiloki.multitaks.database.record.Record;
import com.dogiloki.multitaks.database.record.RecordField;
import com.dogiloki.multitaks.database.record.RecordList;
import com.dogiloki.multitaks.dataformat.JSON;
import com.dogiloki.multitaks.datastructure.sorting.enums.OrderAlgorithm;
import com.dogiloki.multitaks.directory.ConfigFile;
import com.dogiloki.multitaks.directory.Storage;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
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
        long start_time=System.nanoTime();
        Runtime runtime=Runtime.getRuntime();
        long memory_before=runtime.totalMemory()-runtime.freeMemory();
        
        Database db=new Database("db");
        Collection col=db.collection("persona",Persona.class);
        RecordList<Persona> list=col
                .find(Filter.eq("_id","b91df49282bdd6cd9411879acf3fe99ddbdd4a482477d4e1eafbfc2745c2e4aa"))
                .orderBy("name");
        System.out.println(list.next().name);
        
        long end_time=System.nanoTime();
        long memory_after=runtime.totalMemory()-runtime.freeMemory();
        long duration=end_time-start_time;
        double duration_s=duration/1000000000;
        long memory_used=memory_after-memory_before;
        System.out.println("Tiempo de ejecución: "+duration_s+" segundos");
        System.out.println("Memoria usada: "+memory_used/(1024*1024)+" MB");
        
    }
    
    public void loadData(){
        Database db=new Database("db");
        List<Record> records=new ArrayList();
        Faker faker=new Faker();
        Function.iterate(200000,(step)->{
            Name name=faker.name();
            records.add(new Record()
                    .set("name",name.firstName())
                    .set("surname",name.lastName())
                    .set("email",faker.internet().emailAddress())
                    .set("address",faker.address().streetAddress())
                    .set("edad",faker.number().numberBetween(18,32))
            );
        });
        db.collection("persona").insert(records);
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}