package com.dogiloki.multitaks.dataformat;

import com.dogiloki.multitaks.dataformat.contracts.DataFormat;
import com.dogiloki.multitaks.directory.ListFields;
import java.util.Map;

/**
 *
 * @author _dogi
 */

public class Format extends DataFormat{

    private ListFields<String> values=new ListFields<>();
    
    public Format(String text){
        super(text);
    }
    
    public String data(){
        return this._data(this.values);
    }
    
    public String data(ListFields<String> values){
        return this._data(values);
    }
    
    private String _data(ListFields<String> values){
        String done="";
        if(values.isEmpty()){
            done=this.text;
        }
        for(Map.Entry<String,Object> entry:values.entrySet()){
            String str="\\{"+entry.getKey()+"\\}";
            done=this.text.replaceAll(str,entry.getValue().toString());
            this.text=done;
        }
        return done;
    }
    
    public Format append(String key,String value){
        this.values.append(key,value);
        return this;
    }
    
    @Override
    public String toString(){
        return this.data();
    }

    @Override
    protected ListFields<String> format(String text){
        return null;
    }
    
}
