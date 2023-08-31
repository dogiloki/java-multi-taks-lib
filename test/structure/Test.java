package structure;

import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.database.Database;
import com.dogiloki.multitaks.database.record.RecordList;
import com.dogiloki.multitaks.datastructure.Node;
import com.dogiloki.multitaks.datastructure.binary.TreeBinary;
import com.dogiloki.multitaks.datastructure.binary.NodeBinary;
import com.dogiloki.multitaks.datastructure.sorting.Sorting;
import database.Persona;

/**
 *
 * @author dogi_
 */

public class Test{
    
    public Test(){
        TreeBinary<Persona> tree=new TreeBinary();
        RecordList<Persona> personas=new Persona().all();
        Persona p;
        tree.on_evaluate=(item)->item.nombre;
        tree.on_orden=(per)->{
            System.out.println(per.nombre);
        };
        while((p=personas.next())!=null){
            tree.add(p);
        }
        tree.inOrden();
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}
