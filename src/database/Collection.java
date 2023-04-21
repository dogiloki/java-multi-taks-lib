package database;

import java.util.ArrayList;
import java.util.List;
import multitaks.annotations.directory.Directory;
import multitaks.annotations.directory.Key;
import multitaks.enums.DirectoryType;
import multitaks.enums.FieldType;

/**
 *
 * @author dogi_
 */

@Directory(type=DirectoryType.JSON)
public class Collection{
    
    @Key(value="auto_increment")
    public long auto_increment=0;
    
    @Key(value="_id")
    public String _id=ObjectId.generate();
    
    @Key(value="name")
    public String name;
    
    @Key(value="fields",type=FieldType.LIST)
    public List<Field> fields=new ArrayList<>();
    
    public Collection(){
        
    }
    
    public Collection(String name, String src){
        this.name=name;
    }
    
    public boolean addField(String name){
        if(this.selectField(name)!=null){
            return false;
        }
        this.fields.add(new Field(name));
        return true;
    }
    
    public Field selectField(String name){
        for(Field field:this.fields){
            if(field.name.equals(name)){
                return field;
            }
        }
        return null;
    }
    
}
