package com.dogiloki.multitaks.dataformat;

import com.dogiloki.multitaks.directory.ListFields;
import com.dogiloki.multitaks.directory.interfaces.DataFormat;
import java.util.Map;

/**
 *
 * @author dogiloki
 */

public class ENV extends DataFormat{
    
    public ENV(String text){
        this.format(text.split("\n"));
    }
    
    public ENV(String... args){
        this.format(args);
    }
    
    public ENV(Object instace){
        super(instace);
    }
    
    private void format(String[] text){
        if(text.length<=0){
            return;
        }
        for(String line:text){
            if(line.isEmpty()){
                continue;
            }
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
            this.fields.put(key,value);
        }
        this.json=JSON.builder().toJson(this.fields);
    }
    
    @Override
    public String toString(){
        String str="";
        for(Map.Entry entry:this.fields.entrySet()){
            str+=entry.getKey()+"="+entry.getValue()+"\n";
        }
        return str.length()<=0?str:str.substring(0,str.length()-1);
    }
    
}
