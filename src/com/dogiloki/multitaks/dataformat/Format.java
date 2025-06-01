package com.dogiloki.multitaks.dataformat;

import com.dogiloki.multitaks.dataformat.contracts.DataFormat;
import com.dogiloki.multitaks.directory.HashFields;
import java.util.Map;

/**
 *
 * @author _dogi
 */

public class Format extends DataFormat{

    public Format(String text){
        super(text);
    }
    
    public Format(Object instace){
        super(instace);
    }
    
    public String data(HashFields<String,Object> values){
        String done="";
        for(Map.Entry<String,Object> entry:values.entrySet()){
            String str="\\{"+entry.getKey()+"\\}";
            done=this.text.replaceAll(str,entry.getValue().toString());
            this.text=done;
        }
        return done;
    }

    @Override
    protected HashFields<String,Object> format(String text){
        return null;
    }
    
}
