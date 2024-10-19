package com.dogiloki.multitaks.dataformat.annotations;

import com.dogiloki.multitaks.dataformat.enums.NoEnum;
import com.dogiloki.multitaks.dataformat.enums.TypeFieldFormat;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author _dogi
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldFormat{
    
    String id() default("");
    String label();
    TypeFieldFormat type();
    Class<? extends Enum<?>> list() default NoEnum.class;
    
}
