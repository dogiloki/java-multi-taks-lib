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
import com.dogiloki.multitaks.datastructure.sorting.enums.OrderBy;
import com.dogiloki.multitaks.database.record.Record;
import com.dogiloki.multitaks.database.record.RecordList;
import com.dogiloki.multitaks.datastructure.sorting.enums.OrderAlgorithm;
import com.dogiloki.multitaks.directory.Storage;
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
        Runtime runtime=Runtime.getRuntime();
        long initial_memory=runtime.totalMemory()-runtime.freeMemory();
        long initial_time=System.currentTimeMillis();
        /*
        Faker f=new Faker();
        for(int a=0; a<1; a++){
            Persona p=new Persona();
            p.nombre=f.name().firstName();
            p.apellido=f.name().lastName();
            p.edad=f.number().randomDigit();
            p.save();
        }
        */
        Sorting.default_order_algorithm=OrderAlgorithm.INSERTION_SORT;
        RecordList<Persona> personas=new Persona().all().orderBy("nombre");
        Persona p;
        int index=0;
        while((p=personas.next())!=null){
            System.out.println(index+" - "+p.nombre);
            index++;
        }
        long final_memory=runtime.totalMemory()-runtime.freeMemory();
        long used_memory=final_memory-initial_memory;
        long end_time=System.currentTimeMillis();
        System.out.println("Memoria usada: "+Storage.convertSize(used_memory));
        System.out.println("Tiempo: "+(end_time-initial_time));
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}