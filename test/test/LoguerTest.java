package test;

import multitaks.logger.Logger;

/**
 *
 * @author dogiloki
 */

public class LoguerTest{
    
    public LoguerTest(){
        Logger loguer=new Logger();
        loguer.info("Esto es un mensage informativo");
        loguer.add("HOla");
        loguer.info("que tal");
        loguer.error("ESto es un erorr");
    }
    
    public static void main(String[] args){
        new LoguerTest();
    }
    
}
