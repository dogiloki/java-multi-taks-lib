package com.dogiloki.multitaks.directory;

import com.dogiloki.multitaks.directory.enums.DirectoryType;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
    private boolean append=false;
    
    public FileBlock(String src, int block_size){
        this.src=src;
        this.block_size=block_size;
        this.buffer=new byte[this.block_size];
        new Storage(src,DirectoryType.FILE).exists(true);
    }
    
    public byte[] read()throws IOException{
        if(this.in==null){
            this.in=new FileInputStream(this.getSrc());
        }
        int bit;
        bit=this.in.read(this.buffer);
        if(bit==-1){
            return null;
        }
        this.index++;
        byte[] process=new byte[bit];
        System.arraycopy(this.buffer,0,process,0,bit);
        this.element=process;
        return this.element;
    }
    
    public byte[] readAll()throws IOException{
        return Files.readAllBytes(Paths.get(this.getSrc()));
    }
    
    public void write(byte[] b)throws IOException{
        if(this.out==null){
            this.out=new FileOutputStream(this.getSrc(),this.append());
        }
        this.out.write(b);
    }
    
    public void write(int b)throws IOException{
        if(this.out==null){
            this.out=new FileOutputStream(this.getSrc(),this.append());
        }
        this.out.write(b);
    }
    
    public void write(byte[] buffer, int b1, int b2)throws IOException{
        if(this.out==null){
            this.out=new FileOutputStream(this.getSrc(),this.append());
        }
        this.out.write(buffer,b1,b2);
    }
    
    public void close() throws IOException{
        if(this.in!=null){
            this.in.close();
        }
        if(this.out!=null){
            this.out.close();
        }
    }
    
    public boolean append(){
        return this.append;
    }
    
    public FileBlock append(boolean append){
        this.append=append;
        return this;
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
