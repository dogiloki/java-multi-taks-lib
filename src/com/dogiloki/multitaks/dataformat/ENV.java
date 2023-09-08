package com.dogiloki.multitaks.dataformat;

import com.dogiloki.multitaks.directory.ListFields;
import com.dogiloki.multitaks.directory.interfaces.DataFormat;

/**
 *
 * @author dogiloki
 */

public class ENV implements DataFormat{
    
    public ListFields<String> datas=new ListFields();
    private String str;
    
    public ENV(String text){
        this.format(text.split("\n"));
    }
    
    public ENV(String... args){
        this.format(args);
    }
    
    public ENV(ListFields datas){
        this.datas=datas;
    }
    
    private void format(String[] text){
        for(String line:text){
            if(line.substring(0,1).equals("#")){
                continue;
            }
            int index_key=0;
            int index_value=line.indexOf("=");
            int end_key=index_value;
            index_value++;
            int end_value=line.length();
            String key=line.substring(index_key,end_key);
            Object value=line.substring(index_value,end_value);
            this.datas.put(key,value);
        }
    }
    
    public ListFields<String> datas(){
        return this.datas;
    }
    
    @Override
    public Object getValue(String key){
        return this.datas.get(key);
    }
    
    @Override
    public String toString(){
        this.datas.forEach((key,value)->{
            this.str+=key+"="+value+"\n";
        });
        return this.str.substring(0,this.str.length()-1);
    }
    
}
