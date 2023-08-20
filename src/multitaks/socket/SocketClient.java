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
    
    private Socket socket;
    private BufferedReader reader;
    
    public SocketClient(){
    
    }
    
    public SocketClient(String ip, int port){
        this.init(ip,port);
    }
    
    private void init(String ip, int port){
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
    
    public void emit(String channel, Object message)throws IOException{
        this.send(this.socket,new SocketData(channel,message).toString());
    }
    
    public void start(String ip, int port)throws IOException{
        this.init(ip,port);
        this._start();
    }
    
    public void start()throws IOException{
        this._start();
    }
    
    private void _start()throws IOException{
        this.socket=new Socket(this.ip,this.port);
        new Thread(this).start();
    }
    
    public void close()throws IOException{
        this.start=false;
        this.socket.close();
        this.reader.close();
    }
    
    @Override
    public void run(){
        this.start=true;
        try{
            try{
                this.reader=new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            }catch(Exception ex){
                
            }
            while(this.isStart()){
                String data;
                try{
                    while(this.isStart() && (data=this.reader.readLine())!=null){
                        SocketData message=new Gson().fromJson(data,SocketData.class);
                        SocketServer.onMessage on_message=this.getChannels().get(message.getChannel());
                        if(on_message!=null){
                            on_message.run(message.getMessage().toString());
                        }
                    }
                }catch(Exception ex){
                
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public Socket getSocket(){
        return this.socket;
    }
    
}
