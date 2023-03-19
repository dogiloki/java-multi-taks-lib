package multitaks.relations;

import multitaks.interfaces.ActionRelation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author dogi_
 */
public class Relation implements ActionRelation{
    
    public interface Callback{
        public void execute(Field field);
    }
    
    private Object instance;
    private Class chield_class;
    private List<Object> items=new ArrayList<>();
    
    public Relation(){
        
    }
    
    public Relation(Object instance){
        this.aim(instance);
    }
    
    public void aim(Object instance){
        this.instance=instance;
        this.chield_class=instance.getClass();
    }
    
    public void getField(Callback call){
        for(Field field:this.chield_class.getDeclaredFields()){
            try{
                if(field.getType()==OneByOne.class || field.getType()==OneToMany.class){
                    call.execute(field);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    public void removeRelations(){
        this.getField((field)->{
            try{
                field.getType().getMethod("remove").invoke(field.get(this.instance));
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
    }
    
    public List<Object> getRelations(){
        this.items.clear();
        this.getField((field)->{
            try{
                this.items.add(field.getType().getMethod("get").invoke(field.get(this.instance)));
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
        return this.items;
    }
    
}
