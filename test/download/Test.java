package download;

import com.dogiloki.multitaks.download.DownloadDialog;

/**
 *
 * @author dogi_
 */
public class Test{
    
    public Test(){
        //new DownloadDialog(null,true,"https://piston-data.mojang.com/v1/objects/f69c284232d7c7580bd89a5a4931c3581eae1378/server.jar","minecraft.jar").setVisible(true);
        new DownloadDialog(
                null,
                true,
                "https://c.tile.openstreetmap.org/13/310/470.png",
                "E:\\Github\\enlaces\\readme\\oragnismos\\13-310-470.png"
        ).setVisible(true);
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}