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
        Lang lang=new Lang("es").item("exceptions");
        System.out.println(lang.format("prueba")
                .append("name",lang.format("name").toString())
                .append("profile","Mentor")
                .toString()
        );
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}