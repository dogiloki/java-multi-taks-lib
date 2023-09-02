package structure;

import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.database.Database;
import com.dogiloki.multitaks.database.record.RecordList;
import com.dogiloki.multitaks.datastructure.Node;
import com.dogiloki.multitaks.datastructure.graph.Graph;
import com.dogiloki.multitaks.datastructure.tree.TreeBinary;
import com.dogiloki.multitaks.datastructure.tree.NodeBinary;
import com.dogiloki.multitaks.datastructure.sorting.Sorting;
import com.dogiloki.multitaks.datastructure.tree.enums.TraversalType;
import com.dogiloki.multitaks.directory.Storage;
import database.Persona;

/**
 *
 * @author dogi_
 */

public class Test{
    
    public Test(){
        Graph<String> g=new Graph();
        g.add("Julio");
        g.add("Martha");
        g.add("Sofia");
        g.add("Edgar");
        g.add("1");
        g.bidirectionalEdge("Julio","Martha",(edge)->edge.source().getValue().length(),true);
        System.out.println(g.neighbors("Julio"));
        /*
        Runtime runtime=Runtime.getRuntime();
        long initial_memory=runtime.totalMemory()-runtime.freeMemory();
        TreeBinary<Persona> tree=new TreeBinary();
        RecordList<Persona> personas=new Persona().all();
        Persona p;
        tree.onEvaluate((item)->item.nombre);
        while((p=personas.next())!=null){
            tree.add(p);
        }
        tree.inOrden();
        while(tree.hasNext()){
            System.out.println(tree.next().nombre);
        }
        long final_memory=runtime.totalMemory()-runtime.freeMemory();
        long used_memory=final_memory-initial_memory;
        System.out.println("Memoria usada: "+Storage.convertSize(used_memory));
        */
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}
