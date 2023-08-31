package structure;

import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.database.Database;
import com.dogiloki.multitaks.database.record.RecordList;
import com.dogiloki.multitaks.datastructure.Node;
import com.dogiloki.multitaks.datastructure.tree.TreeBinary;
import com.dogiloki.multitaks.datastructure.tree.NodeBinary;
import com.dogiloki.multitaks.datastructure.sorting.Sorting;
import com.dogiloki.multitaks.datastructure.tree.enums.TraversalType;
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
        while((p=personas.next())!=null){
            tree.add(p);
        }
        tree.inOrden();
        while(tree.hasNext()){
            System.out.println(tree.next().nombre);
        }
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}
