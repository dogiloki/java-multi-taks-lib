package multitaks.socket;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author dogi_
 */

public class SocketClient extends SocketHandle implements Runnable{
    
    private String ip;
    private int port;
    private Socket socket;
    private BufferedReader reader;
    
    public SocketClient(String ip, int port){
        this.ip=ip;
        this.port=port;
    }
    
    public void on(String channel, onMessage action){
        this.getChannels().put(channel,action);
        try{
            this.emit(channel,"");
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void emit(String channel, Object message) throws IOException{
        this.send(this.socket,new SocketData(channel,message).toString());
    }
    
    @Override
    public void close() throws IOException{
        this.reader.close();
        this.socket.close();
    }
    
    @Override
    public void start() throws IOException{
        this.socket=new Socket(this.ip,this.port);
        new Thread(this).start();
    }
    
    @Override
    public void run(){
        try{
            this.reader=new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            while(true){
                String data;
                while((data=this.reader.readLine())!=null){
                    try{
                        SocketData message=new Gson().fromJson(data,SocketData.class);
                        SocketServer.onMessage on_message=this.getChannels().get(message.getChannel());
                        if(on_message!=null){
                            on_message.run(message.getMessage());
                        }
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public String getAddress(){
        return this.ip+":"+this.port;
    }
    
    public String getIP(){
        return this.ip;
    }
    
    public int getPort(){
        return this.port;
    }
    
}
