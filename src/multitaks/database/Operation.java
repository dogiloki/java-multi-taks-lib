package multitaks.database;

/**
 *
 * @author dogi_
 */

public class Operation{
    
    public String field;
    public String operator;
    public Object value;
    
    public Operation(String field, String operator, Object value){
        this.field=field;
        this.operator=operator;
        this.value=value;
    }
    
    public Operation(String field, Object value){
        this.field=field;
        this.operator="=";
        this.value=value;
    }
    
}
