package com.dogiloki.multitaks.dataformat;

import com.dogiloki.multitaks.dataformat.contracts.DataFormat;
import com.dogiloki.multitaks.directory.HashFields;
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
    protected HashFields format(String text){
        HashFields fields=new HashFields();
        if(text==null || text.isEmpty()){
            return fields;
        }
        fields=JSON.builder().fromJson(text,HashFields.class);
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
