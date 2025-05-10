package com.dogiloki.multitaks.persistent;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author dogi_
 */

public class ExecutionObserver{
    
    public static final int CORES=Runtime.getRuntime().availableProcessors();
    public static final int POOL=Math.max(4,CORES*2);
    public static final ExecutorService EXECUTOR=Executors.newFixedThreadPool(POOL);
    public static final List<Process> PROCESS=Collections.synchronizedList(new ArrayList<>());
    public static final List<Thread> THREADS=Collections.synchronizedList(new ArrayList<>());
    public static void shutdown(){
        for(Process p:PROCESS){
            p.destroy();
        }
        PROCESS.clear();
        EXECUTOR.shutdown();
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
    
    public interface onCanceled{
        public void call(String out_str, int code);
    }
    
    public interface onFinalized{
        public void call(String out_str, int code);
    }
    
    private boolean cancel;
    //private boolean stop;
    private String command;
    private String context;
    private StringBuilder out_str;
    private ProcessBuilder pb;
    private Process p;
    private InputStream input_stream;
    private OutputStream output_stream;
    private BufferedReader reader;
    private int exit_code;
    private boolean use_thread=false;
    public onCanceled onCanceled=(String out_str1, int code)->{
        
    };
    public onFinalized onFinalized=(String out_str1, int code)->{
        
    };
    
    public ExecutionObserver(){
        
    }
    
    public ExecutionObserver(String command){
        this.command(command);
    }
    
    public ExecutionObserver(String command, String context){
        this.command(command,context);
    }
    
    public ExecutionObserver useThread(){
        this.use_thread=true;
        return this;
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
    
    public void start() throws Exception{
        this._start(null);
    }
    
    public void start(onOutput action) throws Exception{
        this._start(action);
    }
    
    private void _start(onOutput action) throws Exception{
        if(this.use_thread){
            Thread thread=new Thread(()->task(action));
            thread.start();
            THREADS.add(thread);
        }else{
            EXECUTOR.submit(()->{
                this.task(action);
            });
        }
    }
    
    private String task(onOutput action){
        try{
            this.p=this.pb.start();
            PROCESS.add(this.p);
            this.input_stream=this.p.getInputStream();
            this.output_stream=this.p.getOutputStream();
            this.reader=new BufferedReader(new InputStreamReader(this.input_stream));
            this.out_str=new StringBuilder();
            this.cancel=false;
            //this.resumen();
            if(action==null){
                return this.transientOutput();
            }
            String line;
            int index=0;
            while((line=reader.readLine())!=null){
                action.call(line,index);
                this.out_str.append(line).append(System.lineSeparator());
                index++;
                if(this.cancel){
                    break;
                }
            }
            if(this.cancel){
                this.p.destroy();
                this.onCanceled.call(this.out_str.toString(),-1);
            }else{
                this.exit_code=this.p.waitFor();
                this.onFinalized.call(out_str.toString(),this.exit_code);
            }
        }catch(Exception ex){
            this.onFinalized.call(ex.getMessage(),-999);
            ex.printStackTrace();
        }finally{
            try{
                if(this.input_stream!=null)this.input_stream.close();
                if(this.output_stream!=null)this.output_stream.close();
                if(this.reader!=null)this.reader.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return this.out_str.toString();
    }
    
    public void sendToProcess(String input) throws Exception{
        if(this.output_stream==null){
            return;
        }
        this.output_stream.write((input+System.lineSeparator()).getBytes());
        this.output_stream.flush();
    }
    
    public void cancel(){
        this.cancel=true;
    }
    
    private String transientOutput() throws Exception{
        String line;
        while((line=this.reader.readLine())!=null){
            this.out_str.append(line).append(System.lineSeparator());;
        }
        this.input_stream.close();
        this.reader.close();
        this.exit_code=this.p.waitFor();
        this.onFinalized.call(this.out_str.toString(),this.exit_code);
        return this.out_str.toString();
    }
    
}
