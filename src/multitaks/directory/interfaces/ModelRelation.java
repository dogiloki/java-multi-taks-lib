package multitaks.directory.interfaces;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import multitaks.directory.annotations.Relation;
import multitaks.directory.enums.RelationType;

/**
 *
 * @author dogi_
 */
public class ModelRelation implements ActionRelation{
    
    public static ModelRelation ami(Object instance){
        return new ModelRelation(instance);
    }
    
    protected interface Callback{
        public void execute(Field field, Relation annot_key);
    }
    
    private Object instance;
    private Class chield_class;
    private List<Object> items=new ArrayList<>();
    
    public ModelRelation(){
        
    }
    
    public ModelRelation(Object instance){
        this.aim(instance);
    }
    
    public void aim(Object instance){
        this.instance=instance;
        this.chield_class=instance.getClass();
    }
    
    public void getField(Callback call){
        for(Field field:this.chield_class.getDeclaredFields()){
            try{
                Relation annot_key=field.getAnnotation(Relation.class);
                if(annot_key!=null && annot_key.type()!=null){
                    call.execute(field,annot_key);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    public void clearRelations(){
        this.getField((field,annot_key)->{
            try{
                if(annot_key.type()==RelationType.OneToMany){
                    field.getType().getMethod("clear").invoke(field.get(this.instance));
                }else{
                    field.set(this.instance,null);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
    }
    
    public List<Object> getRelations(){
        this.items.clear();
        this.getField((field,annot_key)->{
            try{
                this.items.add(field.get(this.instance));
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
        return this.items.size()<=0?null:this.items;
    }
    
}
