package com.dogiloki.multitaks.datastructure.sorting;

import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.datastructure.sorting.enums.OrderBy;
import java.util.ArrayList;
import java.util.List;
import com.dogiloki.multitaks.callbacks.OnEvaluate;
import com.dogiloki.multitaks.datastructure.tree.TreeBinary;

/**
 *
 * @author dogi_
 */

public class Sorting<T> extends SortingAlgorithm{
    
    private List<T> items=new ArrayList<>();
    private OnEvaluate<T> evaluate_with=(item)->item;
    
    public Sorting(){
        
    }
    
    public List<T> sort(){
        switch(this.orderAlgorithm()){
            case BUBBLE_SORT: return this.bubbleSort();
            case SELECTION_SORT: return this.selectionSort();
            case INSERTION_SORT: return this.insertionSort();
            case BINARY_TREE_SORT: return this.binaryTreeSort();
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
                if(this.orderBy()==OrderBy.ASC && !(Function.compareTo(this.evaluateWith().run(items.get(index)),this.evaluateWith().run(items.get(index+1)))>0)){
                    continue;
                }else
                if(this.orderBy()==OrderBy.DESC && !(Function.compareTo(this.evaluateWith().run(items.get(index)),this.evaluateWith().run(items.get(index+1)))<0)){
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
                if(this.orderBy()==OrderBy.ASC && (Function.compareTo(this.evaluateWith().run(items.get(index2)),this.evaluateWith().run(items.get(min)))>0)){
                    continue;
                }else
                if(this.orderBy()==OrderBy.DESC && (Function.compareTo(this.evaluateWith().run(items.get(index2)),this.evaluateWith().run(items.get(min)))<0)){
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
                if(this.orderBy()==OrderBy.ASC && !(Function.compareTo(this.evaluateWith().run(items.get(posi-1)),this.evaluateWith().run(aux))>0)){
                    break;
                }else
                if(this.orderBy()==OrderBy.DESC && !(Function.compareTo(this.evaluateWith().run(items.get(posi-1)),this.evaluateWith().run(aux))<0)){
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
    
    public List<T> binaryTreeSort(){
        List<T> items=new ArrayList<>();
        TreeBinary<T> tree=new TreeBinary();
        tree.onEvaluate((item)->this.evaluateWith().run(item));
        tree.onOrder((item)->{
            items.add(item);
        });
        for(T item:this.items()){
            tree.add(item);
        }
        if(this.orderBy()==OrderBy.ASC){
            tree.inOrden();
        }else
        if(this.orderBy()==OrderBy.DESC){
            tree.inOrdenReverse();
        }
        return items;
    }
    
    public OnEvaluate<T> evaluateWith(){
        return this.evaluate_with;
    }
    
    public Sorting evaluateWith(OnEvaluate<T> action){
        this.evaluate_with=action;
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
