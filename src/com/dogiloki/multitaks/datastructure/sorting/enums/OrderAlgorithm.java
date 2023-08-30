package com.dogiloki.multitaks.datastructure.sorting.enums;

/**
 *
 * @author dogi_
 */

public enum OrderAlgorithm{
    
    BUBBLE_SORT("Bubble Sort"),
    SELECTION_SORT("Selection Sort"),
    INSERTION_SORT("Insertion Sort");
    
    private String text;
    
    private OrderAlgorithm(String text){
        this.text=text;
    }
    
    @Override
    public String toString(){
        return this.text;
    }
    
}
