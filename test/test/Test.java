package test;

import multitaks.database.Database;
import multitaks.database.Record;
import multitaks.directory.DirectoryList;
import multitaks.directory.ModelDirectory;
import multitaks.directory.Storage;
import multitaks.enums.DirectoryType;

/**
 *
 * @author dogi_
 */

public class Test{
    
    public Test(){
        Storage storage=new Storage("E:\\Escritorio\\client\\libraries",DirectoryType.FOLDER);
        DirectoryList lista=storage.listDirectory();
        while(lista.hasNext()){
            System.out.println(lista.next().getFileName().toString());
        }
    }
    
    public static void main(String[] args){
        new Test();
    }
    
}
