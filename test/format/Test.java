package format;

import com.dogiloki.multitaks.dataformat.JSON;

/**
 *
 * @author dogi_
 */

public class Test{
    
    public Test(){
        Config c=new Config().builder();
        /*
        Folder f=new Folder();
        f.name="Hola";
        f.path="dsa/gfd/sa";
        c.folder=f;
        c.flotante=1.70;
        c.peso=21;
        c.save();
        */
        System.out.println(c.toString());
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}