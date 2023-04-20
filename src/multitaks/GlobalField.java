package multitaks;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dogi_
 */

public class GlobalField{
    
    private static GlobalField instance=null;
    private Map<String,Object> fields=new HashMap<>();
    private Map<String,GlobalField> groups=new HashMap<>();
    private GlobalField sele_group=null;
    
    public static GlobalField singleton(){
        if(GlobalField.instance==null){
            GlobalField.instance=new GlobalField();
        }
        return GlobalField.instance;
    }
    
    public static GlobalField group(String key){
        GlobalField global=GlobalField.singleton();
        GlobalField global_group=global.groups.get(key);
        if(global_group==null){
            global_group=new GlobalField();
            global.groups.put(key,global_group);
        }
        global.sele_group=global_group;
        return global_group;
    }
    
    public static Object get(String key){
        GlobalField global=GlobalField.singleton().sele_group;
        if(global==null){
            global=GlobalField.singleton();
        }
        return global.fields.get(key);
    }
    
    public static void set(String key, Object value){
        GlobalField global=GlobalField.singleton().sele_group;
        if(global==null){
            global=GlobalField.singleton();
        }
        global.fields.put(key,value);
    }
    
    public static Map<String,GlobalField> groups(){
        GlobalField global=GlobalField.singleton();
        return global.groups;
    }
    
    public static Map<String,Object> all(){
        GlobalField global=GlobalField.singleton().sele_group;
        if(global==null){
            global=GlobalField.singleton();
        }
        return global.fields;
    }
    
}
