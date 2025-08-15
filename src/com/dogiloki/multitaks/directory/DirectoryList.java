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
import java.util.function.Predicate;

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
    private Predicate<Path> filter;
    private boolean recursive=false;
    private boolean relative_paths=false;
    
    public DirectoryList(String path){
        this.run(path,DirectoryType.ALL);
    }
    
    public DirectoryList(String path, DirectoryType type){
        this.run(path,type);
    }
    
    public DirectoryList filter(Predicate<Path> extra_filter){
        Predicate<Path> old_filter=this.filter;
        this.filter=old_filter.and(extra_filter);
        return this;
    }
    
    public DirectoryList setRecursive(boolean value){
        this.recursive=value;
        this.run(this.directory.toString(),this.type);
        return this;
    }
    
    public DirectoryList setUseRelativePaths(boolean value){
        this.relative_paths=value;
        return this;
    }
    
    public boolean isRelativePaths(){
        return this.relative_paths;
    }
    
    public boolean isRecursive(){
        return this.recursive;
    }
    
    private void run(String path, DirectoryType type){
        try{
            this.directory=Paths.get(path);
            this.type=type;
            this.filter=p->true;
            this.current_directory=null;
            if(this.isRecursive()){
                this.iterator=Files.walk(this.directory)
                        .filter(filter_path->{
                            return this.type==DirectoryType.ALL ||
                                    (this.type==DirectoryType.FOLDER && Files.isDirectory(filter_path)) ||
                                    (this.type==DirectoryType.FILE && !Files.isDirectory(filter_path));
                        })
                        .iterator();
            }else{
                this.directory_stream=Files.newDirectoryStream(this.directory);
                this.iterator=this.directory_stream.iterator();
            }
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
                boolean type_valid=this.type==DirectoryType.ALL ||
                        (this.type==DirectoryType.FOLDER && Files.isDirectory(path)) ||
                        (this.type==DirectoryType.FILE && !Files.isDirectory(path));
                if(type_valid && this.filter.test(path)){
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
        return this.isRelativePaths()?this.directory.relativize(path):path;
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
