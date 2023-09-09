package com.dogiloki.multitaks.dataformat;

import com.dogiloki.multitaks.directory.ListFields;
import com.dogiloki.multitaks.directory.interfaces.DataFormat;

/**
 *
 * @author dogi_
 */

public class XML extends DataFormat{
    
    public XML(String text){
        super(text);
    }
    
    public XML(Object instace){
        super(instace);
    }
    
    @Override
    protected ListFields format(String text){
        ListFields fields=new ListFields();
        if(text.isEmpty()){
            return fields;
        }
        fields=JSON.builder().fromJson(text,ListFields.class);
        return fields;
    }
    
    @Override
    public String toString(){
        return this.json;
    }
    
}
