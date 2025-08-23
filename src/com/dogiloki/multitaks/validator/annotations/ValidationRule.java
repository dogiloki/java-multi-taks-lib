package com.dogiloki.multitaks.validator.annotations;

import com.dogiloki.multitaks.validator.enums.ValidatorRule;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author _dogi
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface ValidationRule{
    
    ValidatorRule type();
    String[] params() default {};
    
}
