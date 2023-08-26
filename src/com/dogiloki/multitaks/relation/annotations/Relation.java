package com.dogiloki.multitaks.relation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.dogiloki.multitaks.relation.enums.RelationType;

/**
 *
 * @author dogi_
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Relation{
    
    RelationType type();
    
}
