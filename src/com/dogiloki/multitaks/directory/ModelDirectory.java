package com.dogiloki.multitaks.directory;

import com.dogiloki.multitaks.directory.annotations.Directory;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.dataformat.ENV;
import com.dogiloki.multitaks.dataformat.JSON;
import com.dogiloki.multitaks.dataformat.XML;
import com.dogiloki.multitaks.dataformat.contracts.DataFormat;
import java.lang.reflect.Method;
import com.dogiloki.multitaks.directory.annotations.RunAfter;
import com.dogiloki.multitaks.validator.MapValues;
import com.dogiloki.multitaks.validator.RuleWithParams;
import com.dogiloki.multitaks.validator.Validate;
import com.dogiloki.multitaks.validator.Validation;
import com.dogiloki.multitaks.validator.annotations.Validatable;
import com.dogiloki.multitaks.validator.annotations.ValidationRule;
import java.lang.reflect.Field;

/**
 *
 * @author dogi_
 */
public class ModelDirectory extends Storage{
    
    private Object _instance;
    
    public ModelDirectory(){
        this._aim(null,null);
    }
    
    public ModelDirectory(Object instance){
        this._aim(instance,null);
    }
    
    public ModelDirectory(String src){
        this._aim(null,src);
    }
    
    public ModelDirectory(Object instance, String src){
        this._aim(instance,src);
    }
    
    public ModelDirectory aim(Object instance){
        return this._aim(instance,null);
    }
    
    public ModelDirectory aim(String src){
        return this._aim(null,src);
    }
    
    public ModelDirectory aim(Object instance, String src){
        return this._aim(instance,src);
    }
    
    private ModelDirectory _aim(Object instance, String src){
        this.setInstance(instance);
        src=Storage.formatPath(src);
        this.setSrc(src);
        Directory annot_directory=this.getInstance().getClass().getAnnotation(Directory.class);
        if(annot_directory instanceof Directory){
            this.fromJar(annot_directory.fromJar());
            this.setType(annot_directory.type());
            if(!annot_directory.src().isEmpty()){
                this.setSrc(annot_directory.src());
            }
        }
        super.aim(this.getSrc(),this.getType());
        return this;
    }
    
    public ModelDirectory create(){
        if(this.isFromJar()){
            return this;
        }
        if(this.getType()==null || this.getSrc()==null){
            return this;
        }
        if(this.isFolder()){
            Storage.createFolder(this.getSrc());
        }else{
            new Storage(this.getSrc(),DirectoryType.FILE).exists(true);
        }
        return this;
    }
    
    public Object getInstance(){
        return Function.set(this._instance,this);
    }
    
    public void setInstance(Object instance){
        this._instance=instance;
    }
    
    public <T extends Object> T builder(){
        String text=this.read();
        DataFormat data=null;
        Object instance=this.getInstance();
        switch(this.getType()){
            case JSON: data=new JSON(text); break;
            case ENV: data=new ENV(text); break;
            case XML: data=new XML(text); break;
            default: data=null;
        }
        if(data==null){
            return null;
        }
        instance=data.from(instance.getClass());
        if(instance==null){
            return null;
        }
        try{
            if(instance instanceof ModelDirectory){
                instance.getClass().getMethod("referenceClass",Class.class,String.class,DirectoryType.class).invoke(instance,this.referenceClass(),this.getSrc(),this.getType());
            }
            for(Method method:instance.getClass().getMethods()){
                RunAfter annot_execute=method.getAnnotation(RunAfter.class);
                if(annot_execute==null){
                    continue;
                }
                method.invoke(instance);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        this.setInstance(instance);
        return (T)this.getInstance();
    }
    
    @Override
    public String toString(){
        if(this.getType()==null){
            return null;
        }
        String text;
        DataFormat data=null;
        Object instance=this.getInstance();
        switch(this.getType()){
            case JSON: data=new JSON(instance); break;
            case ENV: data=new ENV(instance); break;
            case XML: data=new XML(instance); break;
            case FOLDER: data=new DataFormat(this.getName()){
                @Override
                protected HashFields format(String text){
                    return null;
                }
                @Override
                public String toString(){
                    return this.text;
                }
            }; break;
        }
        if(data==null){
            return "";
        }
        text=data.toString();
        return text;
    }
    
    public boolean save() throws Exception{
        // Extraer valores del modelo y contruir reglas a partir de anotaciones
        MapValues values=new MapValues();
        MapValues rules=new MapValues();
        for(Field field:this.getInstance().getClass().getDeclaredFields()){
            field.setAccessible(true);
            try{
                Validatable validatable=field.getAnnotation(Validatable.class);
                if(validatable==null) continue;
                RuleWithParams rule_with_params=new RuleWithParams();
                for(ValidationRule vr:validatable.rules()){
                    rule_with_params.append(vr.type(),vr.params());
                }
                values.append(field.getName(),field.get(this.getInstance()));
                rules.append(field.getName(),rule_with_params);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        Validation validation=Validate.make(values,rules);
        if(validation.fails()){
            throw new Exception(validation.errors().values().iterator().next().get(0));
        }
        this.create();
        if(this.getType()!=null && this.getSrc()!=null && this.isFile()){
            return this.write(this.toString());
        }else if(this.getType()==DirectoryType.FOLDER && this.getSrc()!=null){
            this.exists(true);
            return this.exists();
        }
        return false;
    }
    
    public boolean isFile(){
        if(this.getType()==null){
            return false;
        }
        switch(this.getType()){
            case JSON: return true;
            case XML: return true;
            case ENV: return true;
            default: return false;
        }
    }
    
    public boolean isFolder(){
        if(this.getType()==null){
            return false;
        }
        return this.getType()==DirectoryType.FOLDER;
    }
    
}