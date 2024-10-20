package com.dogiloki.multitaks.dataformat;

import com.dogiloki.multitaks.directory.ListFields;
import com.dogiloki.multitaks.dataformat.contracts.DataFormat;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

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
        if(text==null || text.isEmpty()){
            return fields;
        }
        fields=JSON.builder().fromJson(text,ListFields.class);
        return fields;
    }
    
    @Override
    public String toString(){
        try{
            XmlMapper xml=new XmlMapper();
            return xml.writeValueAsString(this.instace);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return "";
    }
    
}
