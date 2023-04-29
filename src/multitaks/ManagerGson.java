package multitaks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import multitaks.directory.Storage;
import multitaks.directory.enums.DirectoryType;

/**
 *
 * @author dogi_
 */

public class ManagerGson extends Storage{
    
    private final Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    private Object instance;
    
    public ManagerGson(){
        
    }
    
    public void aim(Object instance, String src){
        this.instance=instance;
        super.aim(src,DirectoryType.JSON);
        this.load();
    }
    
    public void load(){
        if(this.type!=null && this.src!=null){
            this.instance=this.gson.fromJson(this.read(),this.instance.getClass());
        }
    }
    
    public boolean save(){
        if(this.type!=null && this.src!=null){
            this.clean();
            return this.write(this.gson.toJson(this.instance));
        }
        return false;
    }
    
}
