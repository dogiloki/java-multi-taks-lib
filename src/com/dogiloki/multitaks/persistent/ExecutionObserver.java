package com.dogiloki.multitaks.persistent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author dogi_
 */

public class ExecutionObserver{
    
    public static ExecutionObserver execution(String command) throws IOException{
        return new ExecutionObserver(command);
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
    
    public ExecutionObserver command(String command){
        this.command=command;
        this.pb=new ProcessBuilder(command.split(" "));
        return this;
    }
    
    public String start() throws IOException{
        return this._start(null);
    }
    
    public String start(onOutput action) throws IOException{
        return this._start(action);
    }
    
    private String _start(onOutput action) throws IOException{
        this.p=this.pb.start();
        this.input_stream=this.p.getInputStream();
        this.reader=new BufferedReader(new InputStreamReader(this.input_stream));
        this.out_str="";
        this.cancel=false;
        //this.resumen();
        if(action==null){
            return this.transientOutput();
        }
        new Thread(){
            @Override
            public void run(){
                String line;
                try{
                    int index=0;
                    while((line=reader.readLine())!=null){
                        action.call(line,index);
                        out_str+=line+"\n";
                        index++;
                        if(cancel){
                            break;
                        }
                    }
                    input_stream.close();
                    reader.close();
                    exit_code=p.waitFor();
                    if(cancel){
                        onCanceled.call(out_str,exit_code);
                    }else{
                        onFinalized.call(out_str,exit_code);
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }.start();
        return this.out_str;
    }
    
    public void cancel(){
        this.cancel=true;
    }
    
    private String transientOutput() throws IOException{
        String line;
        while((line=this.reader.readLine())!=null){
            this.out_str+=line+"\n";
        }
        input_stream.close();
        reader.close();
        return this.out_str;
    }
    
}
