package com.dogiloki.multitaks.updater;

import com.dogiloki.multitaks.directory.DirectoryList;
import com.dogiloki.multitaks.directory.ListFields;
import com.dogiloki.multitaks.directory.Storage;
import java.nio.file.Path;

/**
 *
 * @author _dogi
 */

public class UpdateFiles extends ListFields<UpdateFile>{
    
    public UpdateFiles(String path){
        try{
            DirectoryList files=new Storage(path)
                    .listFiles()
                    .setRecursive(true);
            while(files.hasNext()){
                Path file_path=files.next();
                if(file_path.toString().endsWith(Manifest.FILE_NAME)) continue;
                this.append(new UpdateFile(file_path.toString(),path));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
