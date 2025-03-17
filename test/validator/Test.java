package validator;

import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.validator.MapRules;
import com.dogiloki.multitaks.validator.MapValues;
import com.dogiloki.multitaks.validator.Validate;
import com.dogiloki.multitaks.validator.Validator;



/**
 *
 * @author _dogi
 */

public class Test{
    
    public Test(){
        Validator.singleton(Validator.class).make("between",(Object key, Object value, MapValues values, Object[] params)->{
            return Function.compareTo(value,params[0])==1 && Function.compareTo(value,params[1])==-1;
        },":key debe estar entre :0 y :1");
        MapValues values=new MapValues();
        values.append("edad",10);
        MapValues rules=new MapValues();
        rules.append("edad","between:10,20");
        System.out.println(Validate.make(values,rules).errors().toJson());
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}