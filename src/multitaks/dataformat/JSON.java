package multitaks.dataformat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.Map;
import multitaks.directory.interfaces.DataFormat;

/**
 *
 * @author dogi_
 */

public class JSON implements DataFormat{
    
    private Map<String,Object> datas=new HashMap<>();
    private Object instance;
    
    public JSON(String json_string){
        this.datas=new Gson().fromJson(json_string,new TypeToken<HashMap<String,Object>>(){}.getType());
    }
    
    public static Gson builder(){
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }
    
    @Override
    public Object getValue(String key){
        return datas.get(key);
    }
    
}
