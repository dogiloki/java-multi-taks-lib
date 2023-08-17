package multitaks.directory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author dogi_
 */

public class FileBlock{
    
    private String src;
    private int block_size;
    private byte[] buffer;
    private FileInputStream in;
    private int index=0;
    private byte[] element;
    
    public FileBlock(String src, int block_size) throws FileNotFoundException{
        this.src=src;
        this.block_size=block_size;
        this.buffer=new byte[this.block_size];
        this.in=new FileInputStream(this.getSrc());
    }
    
    public byte[] read(){
        int bit;
        try{
            bit=this.in.read(this.buffer);
            if(bit==-1){
                this.close();
            }else{
                this.index++;
                byte[] process=new byte[bit];
                System.arraycopy(this.buffer,0,process,0,bit);
                this.element=process;
                return this.element;
            }
        }catch(IOException ex){
            return null;
        }
        return null;
    }
    
    public void close() throws IOException{
        this.in.close();
    }
    
    // Getters
    public String getSrc(){
        return this.src;
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
