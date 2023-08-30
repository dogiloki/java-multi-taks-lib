package com.dogiloki.multitaks.datastructure.sorting;

import com.dogiloki.multitaks.datastructure.sorting.enums.OrderBy;

/**
 *
 * @author dogi_
 */

public class SortingBy{
    
    public static OrderBy default_order_by=OrderBy.ASC;
    private OrderBy order_by;
    
    public SortingBy(){
        this.orderBy(SortingBy.default_order_by);
    }
    
    public OrderBy orderBy(){
        return this.order_by;
    }
    
    public SortingBy orderBy(OrderBy order_by){
        this.order_by=order_by;
        return this;
    }
    
}
