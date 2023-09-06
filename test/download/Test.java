package download;

import com.dogiloki.multitaks.Download.Download;

/**
 *
 * @author dogi_
 */
public class Test{
    
    public Test(){
        Download d=new Download("https://piston-data.mojang.com/v1/objects/f69c284232d7c7580bd89a5a4931c3581eae1378/server.jar","minecraft.jar");
        d.start();
        d.onConnecting((metrics)->{
            System.out.println(metrics.status);
        });
        d.onProgress((metrics)->{
            System.out.println(metrics.status);
        });
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}