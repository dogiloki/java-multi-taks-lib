package multitaks.relations.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import multitaks.relation.enums.RelationType;

/**
 *
 * @author dogi_
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Relation{
    
    RelationType type();
    
}
