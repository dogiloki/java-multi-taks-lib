package com.dogiloki.multitaks.directory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 *
 * @author _dogi
 */

public class FileBuffered{
    
    private InputStream is;
    private InputStreamReader isr;
    
    private OutputStream os;
    private OutputStreamWriter osw;
    
    private BufferedReader br;
    private BufferedWriter bw;
    
    public FileBuffered(InputStream is){
        this.input(is);
    }
    
    public FileBuffered(OutputStream os){
        this.output(os);
    }
    
    public FileBuffered(InputStream is, OutputStream os){
        this.input(is);
        this.output(os);
    }
    
    public void input(InputStream is){
        this.is=is;
        this.isr=new InputStreamReader(this.is);
        this.br=new BufferedReader(this.isr);
    }
    
    public void output(OutputStream os){
        this.os=os;
        this.osw=new OutputStreamWriter(this.os);
        this.bw=new BufferedWriter(this.osw);
    }
    
    public String readLine(){
        try{
            return this.br.readLine();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public String read(){
        try{
            StringBuilder content=new StringBuilder();
            char[] buffer=new char[1024];
            int b;
            while((b=this.br.read(buffer))!=-1){
                content.append(buffer,0,b);
            }
            return content.toString();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public void write(Object content){
        try{
            String str=(String)content;
            this.bw.write(str);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
