package com.dogiloki.multitaks.validator;

import com.dogiloki.multitaks.directory.HashFields;
import com.dogiloki.multitaks.validator.enums.ValidatorRule;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author _dogi
 */

public class RuleWithParams extends HashFields<ValidatorRule,Object[]>{
    
    public RuleWithParams append(ValidatorRule rule, Object... params){
        super.append(rule,params);
        return this;
    }
    
    @Override
    public String toString(){
        List<String> parts=new ArrayList<>();
        this.iterate((rule,params)->{
            StringBuilder str=new StringBuilder();
            str.append(rule.toString())
                    .append(":")
                    .append(
                        Arrays.stream(params)
                        .map(Object::toString)
                        .collect(Collectors.joining(","))
                    );
            parts.add(str.toString());
        });
        return String.join("|",parts);
    }
    
}
