package code;

import com.dogiloki.multitaks.ObjectId;

/**
 *
 * @author dogi_
 */

public class Test{
    
    public Test(){
        System.out.println(ObjectId.generate());
        System.out.println(ObjectId.generate());
        System.out.println(ObjectId.generate());
        System.out.println(ObjectId.generate());
        System.out.println(ObjectId.generate());
        System.out.println(ObjectId.generate());
    }
    
    public static void main(String args[]){
        new Test();
    }
    
}