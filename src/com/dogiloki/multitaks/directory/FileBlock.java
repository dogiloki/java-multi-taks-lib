package com.dogiloki.multitaks.directory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author dogi_
 */

public class FileBlock{
    
    private String src;
    private int block_size;
    private byte[] buffer;
    private FileInputStream in;
    private FileOutputStream out;
    private int index=0;
    private byte[] element;
    
    public FileBlock(String src, int block_size) throws FileNotFoundException{
        this.src=src;
        this.block_size=block_size;
        this.buffer=new byte[this.block_size];
    }
    
    public byte[] read()throws IOException{
        if(this.in==null){
            this.in=new FileInputStream(this.getSrc());
        }
        int bit;
        bit=this.in.read(this.buffer);
        if(bit==-1){
            this.close();
            return null;
        }
        this.index++;
        byte[] process=new byte[bit];
        System.arraycopy(this.buffer,0,process,0,bit);
        this.element=process;
        return this.element;
    }
    
    public void write(byte[] b)throws IOException{
        if(this.out==null){
            this.out=new FileOutputStream(this.getSrc());
        }
        //this.out.write(b,0,b.length);
        this.out.write(b);
    }
    
    public void write(int b)throws IOException{
        if(this.out==null){
            this.out=new FileOutputStream(this.getSrc());
        }
        //this.out.write(b,0,b.length);
        this.out.write(b);
    }
    
    public void close() throws IOException{
        if(this.in!=null){
            this.in.close();
        }
        if(this.out!=null){
            this.out.close();
        }
    }
    
    // Getters
    public String getSrc(){
        return this.src;
    }
    public int getBlockSize(){
        return this.block_size;
    }
    public int getSizeBlock(){
        return this.block_size;
    }
    public byte[] getBuffer(){
        return this.buffer;
    }
    public int getFileBlock(){
        return this.block_size;
    }
    public int getIndex(){
        return this.index;
    }
    
}
