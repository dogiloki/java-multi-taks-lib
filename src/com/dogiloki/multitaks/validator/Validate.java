package com.dogiloki.multitaks.validator;

import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.Singleton;
import static com.dogiloki.multitaks.SingletonMethods.RULES;
import com.dogiloki.multitaks.dataformat.contracts.DataFormat;
import com.dogiloki.multitaks.validator.enums.ValidatorRule;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author _dogi
 */

public class Validate{
    
    public static Validation make(MapValues values, MapValues rules){
        MapErrors errors=new MapErrors();
        for(Map.Entry<String,Object> entry:rules.entrySet()){
            String key=entry.getKey();
            String value=entry.getValue().toString();
            String[] alias_rules=value.split("\\|");
            for(String alias_rule:alias_rules){
                Object[] alia_rule_split=alias_rule.split(":");
                alias_rule=alia_rule_split[0].toString();
                MakeValidator make_validator=new MakeValidator();
                Rule action=((MapRules)Singleton.singleton(Validator.class).callMethod(RULES)).get(alias_rule);
                Object[] params;
                if(alia_rule_split.length<=1){
                    params=new Object[0];
                }else{
                    params=alia_rule_split[1].toString().split(",");
                }
                if(alias_rule.equals(ValidatorRule.NULLABLE) && value==null){
                    break;
                }
                if(action==null){
                    continue;
                }
                if(!action.passes(key,values.get(key),values,params)){
                    List<String> list=Function.set(errors.get(key),new ArrayList<>());
                    MapValues args=new MapValues(params);
                    args.append("key",key);
                    args.append("value",values.get(key));
                    list.add(DataFormat.messageFormat(action.message,args));
                    errors.put(key,list);
                }
            }
        }
        return new Validation(values,errors);
    }
    
}
