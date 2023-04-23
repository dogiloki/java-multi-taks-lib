package multitaks.directory;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import multitaks.enums.DirectoryType;

/**
 *
 * @author dogi_
 */

public class DirectoryList{
    
    private Path directory;
    private DirectoryStream<Path> directory_strema;
    private Path current_directory;
    private DirectoryType type;
    private Iterator<Path> iterator;
    
    public DirectoryList(String path){
        this.run(path,DirectoryType.ALL);
    }
    
    public DirectoryList(String path, DirectoryType type){
        this.run(path,type);
    }
    
    private void run(String path, DirectoryType type){
        try{
            this.directory=Paths.get(path);
            this.directory_strema=Files.newDirectoryStream(this.directory);
            this.current_directory=null;
            this.type=type;
            this.iterator=this.directory_strema.iterator();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public boolean hasNext(){
        if(!this.iterator.hasNext()){
            try{
                this.directory_strema.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
            return false;
        }
        return true;
    }
    
    public Path next(){
        Path path=this.iterator.next();
        if(path==null){
            return null;
        }
        if(this.type==DirectoryType.ALL || (this.type==DirectoryType.FOLDER && Files.isDirectory(path))){
            this.current_directory=path;
        }else
        if(this.type==DirectoryType.ALL || (this.type==DirectoryType.FILE && !Files.isDirectory(path))){
            this.current_directory=path;
        }
        return this.current_directory;
    }
    
}
