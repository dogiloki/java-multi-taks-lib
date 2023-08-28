package com.dogiloki.multitaks.database.order;

import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.database.order.enums.OrderAlgorithm;
import com.dogiloki.multitaks.database.order.enums.OrderBy;
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
    
}
