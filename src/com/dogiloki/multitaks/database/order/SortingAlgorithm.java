package com.dogiloki.multitaks.database.order;

import com.dogiloki.multitaks.database.order.enums.OrderAlgorithm;

/**
 *
 * @author dogi_
 */

public class SortingAlgorithm extends SortingBy{
    
    private static OrderAlgorithm default_order_algorithm=OrderAlgorithm.INSERTION_SORT;
    private OrderAlgorithm order_algorithm;
    
    public SortingAlgorithm(){
        this.orderAlgorithm(SortingAlgorithm.default_order_algorithm);
    }
    
    public OrderAlgorithm orderAlgorithm(){
        return this.order_algorithm;
    }
    
    protected SortingAlgorithm orderAlgorithm(OrderAlgorithm order_algorithm){
        this.order_algorithm=order_algorithm;
        return this;
    }
    
}
