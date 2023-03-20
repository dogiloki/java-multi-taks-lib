package multitaks.annotations.relation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import multitaks.enums.RelationType;

/**
 *
 * @author dogi_
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Relation{
    
    RelationType type();
    
}
