package com.dogiloki.multitaks.datastructure;

import com.dogiloki.multitaks.directory.HashFields;

/**
 *
 * @author _dogi
 */

public class CountingMap<T> extends HashFields<T,Integer>{
    
    private int amount_increment=1;
    private int amount_decrement=1;
    
    public CountingMap(){
        
    }
    
    public CountingMap<T> amountIncrement(int value){
        this.amount_increment=value;
        return this;
    }
    
    public CountingMap<T> amountDecrement(int value){
        this.amount_decrement=value;
        return this;
    }
    
    public int amountIncrement(){
        return this.amount_increment;
    }
    
    public int amountDecrement(){
        return this.amount_decrement;
    }
    
    // Añade un elemento y si ya existe incrementa el contador
    public void add(T key){
        this.put(key,this.getOrDefault(key,0)+this.amount_increment);
    }
    
    // Incrementar el contador manualmente
    public void increment(T key){
        this.add(key);
    }
    
    // Disminuir el contador y si llega a cero se elimina
    public void decrement(T key){
        if(this.containsKey(key)){
            int count=this.get(key);
            if(count<=1){
                this.remove(key);
            }else{
                this.put(key,count-this.amount_decrement);
            }
        }
    }
    
    // Obtener el contador actual y si no existe retorna 0
    public int getCount(T key){
        return this.getOrDefault(key,0);
    }
    
}
