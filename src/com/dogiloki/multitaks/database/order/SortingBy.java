package com.dogiloki.multitaks.database.order;

import com.dogiloki.multitaks.database.order.enums.OrderBy;

/**
 *
 * @author dogi_
 */

public abstract class SortingBy{
    
    private static OrderBy default_order_by=OrderBy.ASC;
    private OrderBy order_by;
    
    public SortingBy(){
        this.orderBy(SortingBy.default_order_by);
    }
    
    protected OrderBy orderBy(){
        return this.order_by;
    }
    
    public SortingBy orderBy(OrderBy order_by){
        this.order_by=order_by;
        return this;
    }
    
}
