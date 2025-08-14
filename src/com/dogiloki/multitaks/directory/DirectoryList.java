package com.dogiloki.multitaks.directory;

import com.dogiloki.multitaks.callbacks.OnCallbackWithResult;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dogi_
 */

public class DirectoryList{
    
    private Path directory;
    private DirectoryStream<Path> directory_stream;
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
            this.directory_stream=Files.newDirectoryStream(this.directory);
            this.current_directory=null;
            this.type=type;
            this.iterator=this.directory_stream.iterator();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public Iterator<Path> iterator(){
        return this.iterator;
    }
    
    // Busca y prepara el próximo elemento válido
    private void prepareNext(){
        if(this.current_directory!=null) return; // Ya preparado
        try{
            while(this.iterator.hasNext()){
                Path path=this.iterator.next();
                if(path==null) continue;
                if(this.type==DirectoryType.ALL ||
                   (this.type==DirectoryType.FOLDER && Files.isDirectory(path)) ||
                   (this.type==DirectoryType.FILE && !Files.isDirectory(path))
                ){
                    this.current_directory=path;
                    break;
                }
            }
            // Si no había elemento válido se da por terminado
            if(this.current_directory==null && this.directory_stream!=null){
                this.directory_stream.close();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public boolean hasNext(){
        this.prepareNext();
        return this.current_directory!=null;
    }
    
    public Path next(){
        this.prepareNext();
        Path path=this.current_directory;
        this.current_directory=null;
        return path;
    }
    
    public <T> List<T> toList(){
        return (List<T>) this._toList(new OnCallbackWithResult<Path,String>(){
            @Override
            public String run(Path item){
                return item.toString();
            }
            
        });
    }
    
    public <T> List<T> toList(OnCallbackWithResult<Path,T> action){
        return this._toList(action);
    }
    
    private <T> List<T> _toList(OnCallbackWithResult<Path,T> action){
        List<T> list=new ArrayList<>();
        while(this.hasNext()){
            list.add(action.run(this.next()));
        }
        return list;
    }
    
}
