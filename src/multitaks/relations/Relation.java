package multitaks.relations;

import multitaks.interfaces.ActionRelation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.security.auth.callback.Callback;

/**
 *
 * @author dogi_
 */
public class Relation{
    
    public interface Callback{
        public void execute(Field field);
    }
    
    private Object instance;
    private Class chield_class;
    private List<List<ActionRelation>> items=new ArrayList<>();
    
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
    
    public void removeAll(){
        this.getField((field)->{
            try{
                ((ActionRelation)field.get(this.instance)).removeAll();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
    }
    
    public List<List<ActionRelation>> getAll(){
        this.getField((field)->{
            try{
                this.items.add(((ActionRelation)field.get(this.instance)).getAll());
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
        return this.items;
    }
    
}
