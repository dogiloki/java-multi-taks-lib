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
        MapValues values=new MapValues();
        values.append("nombre",10);
        values.append("edad",10);
        MapValues rules=new MapValues();
        rules.append("edad","required");
        System.out.println(Validate.make(values,rules).values().toJson());
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}