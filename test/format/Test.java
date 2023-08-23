package format;

import multitaks.dataformat.JSON;

/**
 *
 * @author dogi_
 */

public class Test{
    
    public Test(){
        String text="{\"string\": \"Hola, mundo!\", \"numero_entero\": 42, \"numero_flotante\": 3.14159, \"booleano\": true, \"array\": [1, 2, 3, 4], \"objeto\": {\"clave1\": \"valor1\", \"clave2\": \"valor2\"}}";
        JSON json=new JSON(text);
        System.out.println(json.toJson("array").getValue(1));
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}