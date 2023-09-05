package test.storage;

import com.dogiloki.multitaks.directory.annotations.Key;
import java.time.LocalDate;

/**
 *
 * @author dogi_
 */

public class Comentario{
    
    @Key("message")
    public String message;
    @Key("date_register")
    public LocalDate date_register=LocalDate.now();
    
    public Comentario(){
    
    }
    
}
