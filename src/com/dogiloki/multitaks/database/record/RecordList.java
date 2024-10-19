package com.dogiloki.multitaks.database.record;

import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.database.filter.Filter;
import com.dogiloki.multitaks.datastructure.sorting.Sorting;
import com.dogiloki.multitaks.datastructure.sorting.enums.OrderBy;
import com.dogiloki.multitaks.dataformat.JSON;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author dogi_
 * @param <T>
 */

public class RecordList<T extends Record>{
    
    private final Scanner iterator;
    private T current;
    private Filter filter;
    private long line_number=0;
    private int count=0;
    private Integer limit_index=null;
    private Integer limit_end=null;
    private Class<T> clazz;
    private boolean ordering=false;
    private boolean ignore_limit=false;
    private List<T> items=new ArrayList<>();
    
    public RecordList(Scanner iterator, Filter filter, Class clazz){
        this.iterator=iterator;
        this.filter=filter;
        this.clazz=clazz;
    }
    
    public RecordList(Scanner iterator, Class clazz){
        this.iterator=iterator;
        this.clazz=clazz;
    }
    
    public Scanner iterator(){
        return this.iterator;
    }
    
    public boolean isRecord(T obj){
        return obj instanceof Record;
    }
    
    private T current(Record current){
        if(current==null){
            this.current=null;
        }else{
            if(this.clazz==null || Record.class.equals(this.clazz)){
                this.current=(T)current;
            }else{
                RecordField fields=current.getFields();
                String json=JSON.builder().toJson(fields);
                this.current=JSON.builder().fromJson(json,this.clazz);
                this.current.setFields(fields);
                this.current.setLineNumber(current.getLineNumber());
            }
        }
        return this.current();
    }
    
    private T current(){
        return this.current;
    }
    
    private boolean hasNext(){
        if(!this.iterator.hasNextLine()){
            this.iterator.close();
            return false;
        }
        return true;
    }
    
    public T next(){
        
        if(this.isOrder()){
            do{
                if(this.count==this.items.size()){
                    return null;
                }
                this.count++;
                if(this.current()!=null && this.hasLimit() && !this.ignore_limit){
                    if(!this.withinLimit()){
                        return null;
                    }
                }
                this.current=this.items.get((this.count-1));
            }while(this.current()==null);
            return this.current();
        }
        
        do{
            if(!this.hasNext()){
                return null;
            }

            this.line_number++;
            this.current(null);
            String json=this.iterator.nextLine();
            System.out.println(json);
            if(json==null || json.equals("")){
                continue;
            }
            RecordField fields=JSON.builder().fromJson(json,RecordField.class);

            Record record=new Record(fields,this.line_number);
            if(this.filter==null){
                this.current(record);
            }else{
                this.current(this.filter.apply(record)?record:null);
            }
            if(this.current()==null){
                continue;
            }
            this.count++;
            if(this.current()!=null && this.hasLimit() && !this.ignore_limit){
                if(!this.withinLimit()){
                    return null;
                }
            }

            if(this.current()!=null && !Record.class.equals(this.current().getClass())){
                // Métodos dinamicos pendiente
            }
        }while(this.current()==null);
        
        return this.current();
    }
    
    public T first(){
        this.current(null);
        T obj;
        while((obj=this.next())!=null){
            this.current(obj);
            break;
        }
        return this.current();
    }
    
    public RecordList limit(int index, int end){
        this.limit_index=index;
        this.limit_end=end;
        return this;
    }
    
    public RecordList limit(int end){
        this.limit_index=1;
        this.limit_end=end;
        return this;
    }
    
    public boolean hasLimit(){
        return this.limit_index!=null && this.limit_end!=null;
    }
    
    public boolean withinLimit(){
        return Function.withinRange(this.count,this.limit_index,this.limit_end);
    }
    
    public RecordList orderByAsc(String key){
        return this._orderBy(key,OrderBy.ASC);
    }
    
    public RecordList orderByDesc(String key){
        return this._orderBy(key,OrderBy.DESC);
    }
    
    public RecordList orderBy(String key){
        return this._orderBy(key,OrderBy.ASC);
    }
    
    public RecordList orderBy(String key, OrderBy order_by){
        return this._orderBy(key,order_by);
    }
    
    private RecordList _orderBy(String key, OrderBy order_by){
        if(this.hasLimit()){
            this.ignore_limit=true;
        }
        this.current(null);
        List<T> items=new ArrayList<>();
        T obj;
        while((obj=this.next())!=null){
            items.add(obj);
        }
        this.ignore_limit=false;
        Sorting<T> sort=new Sorting();
        sort.items(items);
        sort.orderBy(order_by);
        sort.evaluateWith((item)->{
            try{
                if(key.contains("()")){
                    for(Method method:this.clazz.getMethods()){
                        if(!method.getName().endsWith(key.replace("()",""))){
                            continue;
                        }
                        method.setAccessible(true);
                        return method.invoke(item);
                    }
                }
                for(Field field:this.clazz.getFields()){
                    if(!field.getName().equals(key)){
                        continue;
                    }
                    field.setAccessible(true);
                    return field.get(item);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
            return null;
        });
        this.items=sort.sort();
        this.count=0;
        this.ordering=true;
        return this;
    }
    
    public boolean isOrder(){
        return this.ordering;
    }
    
    public Scanner getIterator(){
        return this.iterator;
    }
    
    public String toJson(){
        return JSON.builder().toJson(this.toList());
    }
    
    public List<T> toList(){
        List<T> items=new ArrayList<>();
        T obj;
        while((obj=this.next())!=null){
            items.add(obj);
        }
        return items;
    }
    
}
