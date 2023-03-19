package multitaks.dataformat;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import multitaks.interfaces.DataFormat;

/**
 *
 * @author dogi_
 */

public class JSON implements DataFormat{
    
    public String json_string;
    public JsonElement json_element;
    public JsonObject json_object;
    public Gson gson=new Gson();
    
    public JSON(String json_string){
        this.json_string=json_string;
        this.json_object=this.gson.fromJson(this.json_string, JsonObject.class);
        this.json_element=this.gson.fromJson(this.json_string, JsonObject.class);
    }
    
    @Override
    public Object getValue(String... keys){
        return null;
    }

    @Override
    public Object getValue(String key) {
        return this.json_object.get(key).getAsString();
    }
    
}
