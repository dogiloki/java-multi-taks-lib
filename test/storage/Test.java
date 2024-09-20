package storage;

import com.dogiloki.multitaks.Checksum;
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
        /*
        ModelDirectory di=new ModelDirectory();
        Persona p=new Persona();
        Direccion d=new Direccion();
        Comentario c=new Comentario();
        c.message="Hola que tal";
        d.calle="Morelos";
        d.cp=55846;
        p.nombre="Julio";
        p.edad=21;
        p.direccion=d;
        p.comentarios.add(c);
        p.comentarios.add(c);
        p.comentarios.add(c);
        di.aim(p,"persona.txt");
        di.save();
        System.out.println(p.nombre);
        */
        String text=Storage.instance("E:\\Escritorio\\fotos\\20171006_115719.jpg").hashing();
        System.out.println(text);
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}
