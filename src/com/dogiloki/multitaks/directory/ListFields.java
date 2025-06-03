package com.dogiloki.multitaks.directory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author dogi_
 */

public class ListFields<T> extends ArrayList<T>{
    
    // Indicar si los valores pueden repetirse
    private boolean repeated=true;
    // Almacenar los datos en una estructura más eficiente al usar el método contains
    private final Set<T> cache=new HashSet<>();
    
    public ListFields(boolean repeated){
        super();
        this.repeated(repeated);
    }
    
    public ListFields(){
        super();
    }
    
    public boolean repeated(boolean value){
        this.repeated=value;
        if(this.isRepeated()){
           this.cache.clear();
        }else{
            this.cache.clear();
            List<T> unique=new ArrayList<>();
            for(T val:this){
                if(this.cache.add(val)){
                    unique.add(val);
                }
            }
            super.clear();
            super.addAll(unique);
        }
        return this.repeated;
    }
    
    public boolean isRepeated(){
        return this.repeated;
    }
    
    @Override
    public boolean add(T value){
        if(!this.isRepeated()){
            if(this.cache.contains(value)) return false;
            this.cache.add(value);
        }
        return super.add(value);
    }
    
    @Override
    public void add(int index, T value){
        if(!this.isRepeated()){
            if(this.cache.contains(value)) return;
            this.cache.add(value);
        }
        super.add(index,value);
    }
    
    @Override
    public boolean addAll(Collection<? extends T> c){
        boolean modified=false;
        for(T value:c){
            modified|=this.add(value);
        }
        return modified;
    }
    
    @Override
    public boolean addAll(int index, Collection<? extends T> c){
        int insert_index=index;
        boolean modified=false;
        for(T value:c){
            if(!this.isRepeated() && this.cache.contains(value)) continue;
            if(!this.isRepeated()) this.cache.add(value);
            super.add(insert_index++,value);
            modified=true;
        }
        return modified;
    }
    
    @Override
    public boolean remove(Object o){
        if(!this.isRepeated()) this.cache.remove(o);
        return super.remove(o);
    }
    
    @Override
    public T remove(int index){
        T value=super.remove(index);
        if(!this.isRepeated()) this.cache.remove(value);
        return value;
    }
    
    @Override
    public void clear(){
        super.clear();
        this.cache.clear();
    }
    
}
