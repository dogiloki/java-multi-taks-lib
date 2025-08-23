package validator;

import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.validator.MapRules;
import com.dogiloki.multitaks.validator.MapValues;
import com.dogiloki.multitaks.validator.Validate;
import com.dogiloki.multitaks.validator.Validator;
import com.dogiloki.multitaks.validator.enums.ValidatorRule;



/**
 *
 * @author _dogi
 */

public class Test{
    
    public Test(){
        MapValues values=new MapValues();
        values.append("nombre", "Juan");          // String
        values.append("edad", 18);                // Integer
        values.append("email", "invalidemail");   // Email inválido
        values.append("saldo", 500);         // Decimal como string
        values.append("activo", "true");          // Booleano como string
        values.append("fecha", "2025-08-22");     // Fecha válida
        values.append("password", "12345");
        values.append("password_confirmation", "123"); // Confirmación incorrecta
        values.append("url", "htp://malformed.url");   // URL inválida
        MapValues rules=new MapValues();
        rules.append("edad",
                ValidatorRule.append(ValidatorRule.MIN,16)
                .append(ValidatorRule.MAX,20)
        );
        rules.append("saldo",ValidatorRule.append(ValidatorRule.BETWEEN,100,200));
        System.out.println(Validate.make(values,rules).errors().toJson());
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}