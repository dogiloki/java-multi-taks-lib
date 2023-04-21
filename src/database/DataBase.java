package database;

import multitaks.annotations.directory.Directory;
import multitaks.directory.ModelDirectory;
import multitaks.enums.DirectoryType;

/**
 *
 * @author dogi_
 */

@Directory(type=DirectoryType.FOLDER)
public class DataBase extends ModelDirectory{
    
    private Schema schema;
    
    public DataBase(String src){
        super.aim(this,src);
        this.schema=new Schema(src);
    }
    
    public Collection createCollection(String name, String... fields){
        if(this.collection(name)!=null){
            return null;
        }
        Collection collection=new Collection(name,this.src);
        for(String field:fields){
            collection.addField(field);
        }
        this.schema.addCollection(collection);
        this.save();
        return collection;
    }
    
    public Collection collection(String name){
        return this.schema.getCollection(name);
    }
    
    @Override
    public boolean save(){
        return this.schema.save();
    }
    
}
