package com.dogiloki.multitaks.dataformat;

import com.dogiloki.multitaks.dataformat.contracts.DataFormat;
import com.dogiloki.multitaks.directory.HashFields;
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
    protected HashFields format(String text){
        HashFields fields=new HashFields();
        if(text==null || text.isEmpty()){
            return fields;
        }
        for(String raw_line:text.split("\n")){
            // Limipiar espacios en blanco
            String line=raw_line.trim().replace("\r","");
            // Ignorar linas vacías o que comiencen con #
            if(line.isEmpty() || line.startsWith("#")){
                continue;
            }
            // Buscar el simbolo '=' de forma segura
            int index_value=line.indexOf("=");
            // Si no hay '=', se omite la línea (evitar el error de substrings)
            if(index_value==-1){
                continue;
            }
            // Extraer clave-valor (recortar espacios alrededor del '='
            String key=line.substring(0,index_value).trim();
            String value=line.substring(index_value+1).trim();
            // Guardar en el mapa la clave-valor
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
