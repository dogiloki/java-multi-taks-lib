package com.dogiloki.multitaks.dataformat;

import com.dogiloki.multitaks.directory.ListFields;
import com.dogiloki.multitaks.dataformat.contracts.DataFormat;
import java.util.Map;

/**
 *
 * @author dogiloki
 */

public class ENV extends DataFormat{
    
    public ENV(String text){
        super(text);
    }
    
    public ENV(Object instace){
        super(instace);
    }
    
    @Override
    protected ListFields format(String text){
        ListFields fields=new ListFields();
        if(text==null || text.isEmpty()){
            return fields;
        }
        for(String line:text.split("\n")){
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
            fields.put(key,value);
        }
        return fields;
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
