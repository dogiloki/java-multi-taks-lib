package multitaks.socket;

import com.google.gson.Gson;

/**
 *
 * @author dogi_
 */

public class SocketData{
    
    private String channel;
    private String message;
    
    public SocketData(String channel, String message){
        this.channel=channel;
        this.message=message;
    }
    
    @Override
    public String toString(){
        return new Gson().toJson(this);
    }
    
    // Getters
    public String getChannel(){
        return this.channel;
    }
    public String getMessage(){
        return this.message;
    }
    
}
