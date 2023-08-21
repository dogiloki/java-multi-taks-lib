package multitaks.relation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import multitaks.relation.annotations.Relation;
import multitaks.relation.enums.RelationType;
import multitaks.relation.contracts.ActionRelation;

/**
 *
 * @author dogi_
 */
public final class ModelRelation implements ActionRelation{
    
    public interface getRelations{
        public void execute(Field field, Relation annot_key);
    }
    
    public static ModelRelation ami(Object instance){
        return new ModelRelation(instance);
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
    
    public void getFields(getRelations action){
        for(Field field:this.chield_class.getDeclaredFields()){
            try{
                Relation annot_key=field.getAnnotation(Relation.class);
                if(annot_key!=null && annot_key.type()!=null){
                    action.execute(field,annot_key);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    @Override
    public void clearRelations(){
        this.getFields((field,annot_key)->{
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
    
    @Override
    public List<Object> getRelations(){
        this.items.clear();
        this.getFields((field,annot_key)->{
            try{
                this.items.add(field.get(this.instance));
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
        return this.items.size()<=0?null:this.items;
    }
    
}
