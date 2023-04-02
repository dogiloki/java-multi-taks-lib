package multitaks.annotations.directory;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import multitaks.enums.DirectoryType;

/**
 *
 * @author dogi_
 */

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Directory{
    
    DirectoryType type();
    
}
