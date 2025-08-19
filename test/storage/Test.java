package storage;

import com.dogiloki.multitaks.Checksum;
import java.util.Base64;
import com.dogiloki.multitaks.code.Code;
import com.dogiloki.multitaks.dataformat.JSON;
import com.dogiloki.multitaks.directory.DirectoryList;
import com.dogiloki.multitaks.directory.FileBlock;
import com.dogiloki.multitaks.directory.ModelDirectory;
import com.dogiloki.multitaks.directory.Storage;
import com.dogiloki.multitaks.updater.Manifest;
import com.dogiloki.multitaks.updater.Updater;
import com.dogiloki.multitaks.updater.UpdaterConfig;

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
        //String text=Storage.instance("E:\\Escritorio\\fotos\\20171006_115719.jpg").hashing();
        //System.out.println(new Persona().toString());
        /*
        DirectoryList paths=new Storage("db").listFiles().setRecursive(true);
        while(paths.hasNext()){
            System.out.println(paths.next());
        }
        */
        /*
        Manifest mani=new Manifest("E:\\Github\\wolftri\\updates\\minecraft-server").create();
        mani.url("https://dogiloki.github.io/wolftri/updates/minecraft-server");
        mani.version("1.0.0");
        mani.save();
        */
        Updater updater=new Updater("updates"){
            @Override
            public void onProgress(){
                switch(this.getStatus()){
                    case CHECKING:{
                        System.out.println("Verificando si hay actualización....");
                        break;
                    }
                    case NO_UPDATE:{
                        System.out.println("No hay actualizaciones");
                        break;
                    }
                    case DOWNLOADING:{
                        System.out.println("Descargando archivos...");
                        break;
                    }
                    case DOWNLOAD_COMPLETED:{
                        System.out.println("Descarga completada");
                        break;
                    }
                    case APPLYING:{
                        System.out.println("Aplicando actualización");
                        break;
                    }
                    case FINALIZED:{
                        System.out.println("Actualización completada");
                        break;
                    }
                    case FAILED:{
                        System.out.println("Falló!!!");
                        break;
                    }
                }
            }
        };
        updater.start();
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}
