package com.dogiloki.multitaks.persistent;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author dogi_
 */

public class ExecutionObserver{
    
    public static final int CORES=Runtime.getRuntime().availableProcessors();
    public static final int POOL=Math.max(4,CORES*2);
    public static final ExecutorService EXECUTOR=Executors.newFixedThreadPool(POOL);
    public static Future submitTask(Runnable task){
        return EXECUTOR.submit(task);
    }
    
    public static ExecutionObserver execution(String command) throws IOException{
        return new ExecutionObserver(command);
    }
    
    public static ExecutionObserver execution(String command, String context) throws IOException{
        return new ExecutionObserver(command,context);
    }
    
    public interface onOutput{
        public void call(String line, int posi);
    }
    
    public interface onCaceled{
        public void call(String out_str, int code);
    }
    
    public interface onFinalized{
        public void call(String out_str, int code);
    }
    
    private boolean cancel;
    //private boolean stop;
    private String command;
    private String context;
    private String out_str;
    private ProcessBuilder pb;
    private Process p;
    private InputStream input_stream;
    private BufferedReader reader;
    private int exit_code;
    public onCaceled onCanceled=(String out_str1, int code)->{
        
    };
    public onFinalized onFinalized=(String out_str1,int code)->{
        
    };
    
    public ExecutionObserver(){
        
    }
    
    public ExecutionObserver(String command){
        this.command(command);
    }
    
    public ExecutionObserver(String command, String context){
        this.command(command,context);
    }
    
    public ExecutionObserver command(String command){
        return this._command(command,null);
    }
    
    private ExecutionObserver command(String command, String context){
        return this._command(command,context);
    }
    
    private ExecutionObserver _command(String command, String context){
        this.command=command;
        this.context=context;
        this.pb=new ProcessBuilder(command.split(" "));
        if(this.context!=null){
            this.pb.directory(new File(this.context));
        }
        return this;
    }
    
    public Future<String> start() throws Exception{
        return this._start(null);
    }
    
    public Future<String> start(onOutput action) throws Exception{
        return this._start(action);
    }
    
    private Future<String> _start(onOutput action) throws Exception{
        this.p=this.pb.start();
        this.input_stream=this.p.getInputStream();
        this.reader=new BufferedReader(new InputStreamReader(this.input_stream));
        this.out_str="";
        this.cancel=false;
        //this.resumen();
        return EXECUTOR.submit(()->{
            if(action==null){
                return this.transientOutput();
            }
            try{
                String line;
                int index=0;
                while((line=reader.readLine())!=null){
                    action.call(line,index);
                    out_str+=line+"\n";
                    index++;
                    if(cancel){
                        break;
                    }
                }
                this.input_stream.close();
                this.reader.close();
                if(this.cancel){
                    this.onCanceled.call(this.out_str,-1);
                }else{
                    this.exit_code=this.p.waitFor();
                    this.onFinalized.call(out_str,this.exit_code);
                }
                System.out.println("FIN!!!");
            }catch(Exception ex){
                ex.printStackTrace();
            }
            return this.out_str;
        });
    }
    
    public void cancel(){
        this.cancel=true;
    }
    
    private String transientOutput() throws Exception{
        String line;
        while((line=this.reader.readLine())!=null){
            this.out_str+=line+"\n";
        }
        this.input_stream.close();
        this.reader.close();
        this.exit_code=this.p.waitFor();
        this.onFinalized.call(out_str,this.exit_code);
        return this.out_str;
    }
    
}
