package test;

import multitaks.loguer.Loguer;

/**
 *
 * @author dogiloki
 */

public class LoguerTest{
    
    public LoguerTest(){
        Loguer loguer=new Loguer();
        loguer.info("Esto es un mensage informativo");
        loguer.add("HOla");
        loguer.info("que tal");
        loguer.error("ESto es un erorr");
    }
    
    public static void main(String[] args){
        new LoguerTest();
    }
    
}
