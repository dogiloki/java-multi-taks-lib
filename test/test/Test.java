package test;

import multitaks.database.Database;
import multitaks.database.Record;
import multitaks.database.RecordList;
import multitaks.directory.Storage;
import multitaks.directory.enums.DirectoryType;

/**
 *
 * @author dogi_
 */

public class Test{
    
    public Test(){
        //Database db=new Database("db");
        //db.collection("personas").insert(new Record().set("name","Julio").set("surname","Villanueva"));
        //db.collection("personas").update(new Record().setId("1c78e0dda75e3fbea6df8aca482a3fec3746c59d017cfcf3c71f425b4628e45"),new Record().set("name","Cambio de nombre"));
        //db.collection("personas").delete(new Record().setId("d4f8b651c3e2112248276da81d98f2dda4c2585fdc88ca1bee6b7aa8773240"));
        //Storage.compress("E:\\Escritorio\\inversiones","hola");
        //Storage.descompress("compress","hola.zip");
    }
    
    public static void main(String[] args){
        new Test();
    }
    
}
