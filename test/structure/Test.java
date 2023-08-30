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
        tree.when_saving=(item)->item.nombre;
        while((p=personas.next())!=null){
            tree.add(p);
        }
        tree.inOrden();
        for(Node node:tree.nodes){
            System.out.println(node.getValue());
        }
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}
