package storage;

import com.google.gson.annotations.Expose;
import java.time.LocalDate;

/**
 *
 * @author dogi_
 */

public class Comentario{
    
    @Expose
    public String message;
    @Expose
    public LocalDate date_register=LocalDate.now();
    
    public Comentario(){
    
    }
    
}
