package test;

import multitaks.GlobalField;

/**
 *
 * @author dogi_
 */

public class Test{
    
    public Test(){
        GlobalField.set("valor1","dsadsa");
        GlobalField.group("dsa").set("valor2","dsadsa");
        System.out.println(GlobalField.groups().get("dsa").all());
    }
    
    public static void main(String[] args){
        new Test();
    }
    
}
