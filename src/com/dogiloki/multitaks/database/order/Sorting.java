package com.dogiloki.multitaks.database.order;

import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.database.order.enums.OrderBy;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dogi_
 */

public class Sorting<T> extends SortingAlgorithm{
    
    public interface orderWith<T>{
        public Object run(T item);
    }
    
    private List<T> items=new ArrayList<>();
    private orderWith<T> order_with=(item)->item;
    
    public Sorting(){
        
    }
    
    public List<T> sort(){
        switch(this.orderAlgorithm()){
            case BUBBLE_SORT: return this.bubbleSort();
            case SELECTION_SORT: return this.selectionSort();
            case INSERTION_SORT: return this.insertionSort();
        }
        return this.items();
    }
    
    public List<T> bubbleSort(){
        List<T> items=this.items();
        T aux;
        boolean loop;
        do{
            loop=false;
            for(int index=0; index<items.size()-1; index++){
                if(this.orderBy()==OrderBy.ASC && !(Function.compareTo(this.orderWith().run(items.get(index)),this.orderWith().run(items.get(index+1)))>0)){
                    continue;
                }else
                if(this.orderBy()==OrderBy.DESC && !(Function.compareTo(this.orderWith().run(items.get(index)),this.orderWith().run(items.get(index+1)))<0)){
                    continue;
                }
                aux=items.get(index);
                items.set(index,items.get(index+1));
                items.set(index+1,aux);
                loop=true;
            }
        }while(loop);
        this.items(items);
        return this.items();
    }
    
    public List<T> selectionSort(){
        List<T> items=this.items();
        T aux;
        int min;
        for(int index=0; index<items.size(); index++){
            min=index;
            for(int index2=index+1; index2<items.size(); index2++){
                if(this.orderBy()==OrderBy.ASC && (Function.compareTo(this.orderWith().run(items.get(index2)),this.orderWith().run(items.get(min)))>0)){
                    continue;
                }else
                if(this.orderBy()==OrderBy.DESC && (Function.compareTo(this.orderWith().run(items.get(index2)),this.orderWith().run(items.get(min)))<0)){
                    continue;
                }
                min=index2;
            }
            aux=items.get(index);
            items.set(index,items.get(min));
            items.set(min,aux);
        }
        this.items(items);
        return this.items();
    }
    
    public List<T> insertionSort(){
        List<T> items=this.items();
        T aux;
        int posi;
        for(int index=0; index<items.size(); index++){
            posi=index;
            aux=items.get(index);
            while(posi>0){
                if(this.orderBy()==OrderBy.ASC && !(Function.compareTo(this.orderWith().run(items.get(posi-1)),this.orderWith().run(aux))>0)){
                    break;
                }else
                if(this.orderBy()==OrderBy.DESC && !(Function.compareTo(this.orderWith().run(items.get(posi-1)),this.orderWith().run(aux))<0)){
                    break;
                }
                items.set(posi,items.get(posi-1));
                posi--;
            }
            items.set(posi,aux);
        }
        this.items(items);
        return this.items();
    }
    
    public orderWith<T> orderWith(){
        return this.order_with;
    }
    
    public Sorting orderWith(orderWith<T> action){
        this.order_with=action;
        return this;
    }
    
    public List<T> items(){
        return this.items;
    }
    
    public Sorting items(List<T> items){
        this.items=items;
        return this;
    }
    
}
