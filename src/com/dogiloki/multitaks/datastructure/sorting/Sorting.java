package com.dogiloki.multitaks.datastructure.sorting;

import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.datastructure.sorting.enums.OrderBy;
import java.util.ArrayList;
import java.util.List;
import com.dogiloki.multitaks.datastructure.tree.TreeBinary;
import com.dogiloki.multitaks.callbacks.OnCallback;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author dogi_
 */

public class Sorting<T> extends SortingAlgorithm{
    
    private int use_threads=1;
    private List<List<T>> items=new ArrayList<>();
    private OnCallback<T> evaluate_with=(item)->item;
    
    public Sorting(){
        
    }
    
    public List<T> sort(){
        List<T> list=new ArrayList<>();
        ExecutorService executor=Executors.newFixedThreadPool(this.use_threads);
        for(int index=0; index<this.use_threads; index++){
            final int current_index=index;
            executor.execute(new Runnable(){
                @Override
                public void run(){
                    items.set(current_index,sortAlgorithm(items.get(current_index)));
                }
            });
        }
        executor.shutdown();
        try{
            executor.awaitTermination(Long.MAX_VALUE,TimeUnit.NANOSECONDS);
            TreeBinary<T> tree=new TreeBinary();
            tree.onEvaluate((item)->{
                return this.evaluateWith().run(item);
            });
            if(this.use_threads>1){
                /*
                for(List<T> item:this.items){
                    tree.addAll(item);
                }
                Iterator<T> i=this.orderBy()==OrderBy.ASC?tree.inOrden().nodes().values().iterator():tree.inOrdenReverse().nodes().values().iterator();
                while(i.hasNext()){
                    list.add(i.next());
                }
                */
                throw new Exception("YOU CANNOT USE MORE THAN 1 THREAD");
            }else{
                list=items.get(0);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return list;
    }
    
    private List<T> sortAlgorithm(List<T> items){
        switch(this.orderAlgorithm()){
            case BUBBLE_SORT: return this.bubbleSort(items);
            case SELECTION_SORT: return this.selectionSort(items);
            case INSERTION_SORT: return this.insertionSort(items);
            case BINARY_TREE_SORT: return this.binaryTreeSort(items);
        }
        return null;
    }
    
    public T minor(List<T> items){
        T done=items.get(0);
        for(T item:items){
            if(Function.compareTo(this.evaluateWith().run(item),this.evaluateWith().run(done))<0){
                done=item;
            }
        }
        return done;
    }
    
    public T major(List<T> items){
        T done=items.get(0);
        for(T item:items){
            if(Function.compareTo(this.evaluateWith().run(item),this.evaluateWith().run(done))>0){
                done=item;
            }
        }
        return done;
    }
    
    public List<T> bubbleSort(List<T> items){
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
        return items;
    }
    
    public List<T> selectionSort(List<T> items){
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
        return items;
    }
    
    public List<T> insertionSort(List<T> items){
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
        return items;
    }
    
    public List<T> binaryTreeSort(List<T> items){
        List<T> tree_items=new ArrayList<>();
        TreeBinary<T> tree=new TreeBinary();
        tree.onEvaluate((item)->this.evaluateWith().run(item));
        tree.onOrder((item)->{
            tree_items.add(item);
        });
        for(T item:items){
            tree.add(item);
        }
        if(this.orderBy()==OrderBy.ASC){
            tree.inOrden();
        }else
        if(this.orderBy()==OrderBy.DESC){
            tree.inOrdenReverse();
        }
        return tree_items;
    }
    
    public OnCallback<T> evaluateWith(){
        return this.evaluate_with;
    }
    
    public Sorting evaluateWith(OnCallback<T> action){
        this.evaluate_with=action;
        return this;
    }
    
    public Sorting items(List<T> items){
        int len=Math.floorDiv(items.size(),this.use_threads);
        int index=0;
        for(int a=0; a<this.use_threads; a++){
            int end=index+len;
            if(a==this.use_threads-1){
                end=items.size();
            }
            this.items.add(items.subList(index,end));
            index=end;
        }
        return this;
    }
    
}
