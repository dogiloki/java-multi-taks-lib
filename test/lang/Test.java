package lang;

import com.dogiloki.multitaks.Properties;
import com.dogiloki.multitaks.locale.Lang;

/**
 *
 * @author _dogi
 */
public class Test{
    
    public Test(){
        Properties.load();
        Lang lang=new Lang();
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}