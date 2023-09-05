package test.storage;

import java.util.Base64;
import com.dogiloki.multitaks.code.Code;
import com.dogiloki.multitaks.directory.FileBlock;
import com.dogiloki.multitaks.directory.ModelDirectory;
import com.dogiloki.multitaks.directory.Storage;

/**
 *
 * @author dogi_
 */

public class Test{
    
    public Test(){
        Persona p=(Persona)new Persona().builder();
        Direccion d=new Direccion();
        Comentario c=new Comentario();
        /*
        c.message="Hola que tal";
        d.calle="Morelos";
        d.cp=55846;
        p.nombre="Julio";
        p.edad=21;
        p.direccion=d;
        p.comentarios.add(c);
        p.comentarios.add(c);
        p.comentarios.add(c);
        p.save();
        */
        System.out.println(p.nombre);
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}
