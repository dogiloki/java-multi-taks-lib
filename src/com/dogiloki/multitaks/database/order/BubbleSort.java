package com.dogiloki.multitaks.database.order;

import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.database.order.enums.OrderAlgorithm;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dogi_
 */

public class BubbleSort<T> extends Sorting{
    
    public BubbleSort(){
        this.orderAlgorithm(OrderAlgorithm.BUBBLE_SORT);
    }
    
    @Override
    public List<T> sort(){
        List<T> items=this.items();
        T aux;
        boolean loop;
        do{
            loop=false;
            for(int index=0; index<items.size()-1; index++){
                if(Function.compareTo(items.get(index),items.get(index+1))>1){
                    aux=items.get(index);
                    items.set(index,items.get(index+1));
                    items.set(index+1,aux);
                    loop=true;
                }
            }
        }while(loop);
        this.items(items);
        return this.items();
    }
    
}
