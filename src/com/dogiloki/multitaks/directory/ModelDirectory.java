package com.dogiloki.multitaks.directory;

import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.Map;
import com.dogiloki.multitaks.directory.annotations.Directory;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import com.dogiloki.multitaks.Function;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import com.dogiloki.multitaks.dataformat.ENV;
import com.dogiloki.multitaks.dataformat.JSON;
import com.dogiloki.multitaks.directory.annotations.Execute;
import com.google.gson.annotations.Expose;
import java.lang.reflect.Method;

/**
 *
 * @author dogi_
 */
public class ModelDirectory extends Storage{
    
    private ListFields fields=new ListFields();
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
    
    public void aim(Object instance){
        this._aim(instance,null);
    }
    
    public void aim(String src){
        this._aim(null,src);
    }
    
    public void aim(Object instance, String src){
        this._aim(instance,src);
    }
    
    private void _aim(Object instance, String src){
        this.setInstance(instance);
        this.setSrc(src);
        Directory annot_directory=this.getInstance().getClass().getAnnotation(Directory.class);
        if(annot_directory instanceof Directory){
            this.setType(annot_directory.type());
            if(!annot_directory.src().isEmpty()){
                this.setSrc(annot_directory.src());
            }
        }
        this.create();
        super.aim(this.getSrc(),this.getType());
    }
    
    private void create(){
        this.loadFields();
        if(this.getType()==null ||this.getSrc()==null){
            return;
        }
        if(this.isFolder()){
            Storage.createFolder(this.getSrc());
        }else{
            new Storage(this.getSrc(),DirectoryType.FILE).exists(true);
        }
    }
    
    public Object getInstance(){
        return Function.assignNotNull(this._instance,this);
    }
    
    public void setInstance(Object instance){
        this._instance=instance;
    }
    
    public ListFields loadFields(){
        try{
            Object instance=this.getInstance();
            Directory annot_directory=instance.getClass().getAnnotation(Directory.class);
            if(annot_directory instanceof Directory){
                this.setType(annot_directory.type());
                if(this.isFolder()){
                    return this.fields;
                }
            }
            for(Field field:instance.getClass().getFields()){
                Expose annot_key=field.getAnnotation(Expose.class);
                if(annot_key instanceof Expose){
                    Object value=field.get(instance);
                    this.fields.put(field.getName(),Function.assignNotNull(value,""));
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return this.fields;
    }
    
    public Object builder(){
        Object instance=this.getInstance();
        instance=JSON.builder().fromJson(this.read(),instance.getClass());
        for(Method method:instance.getClass().getMethods()){
            Execute annot_execute=method.getAnnotation(Execute.class);
            if(annot_execute==null){
                continue;
            }
            try{
                method.invoke(instance);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        this.setInstance(instance);
        return this.getInstance();
    }
    
    public String text(){
        if(this.getType()==null){
            return null;
        }
        this.loadFields();
        switch(this.getType()){
            case JSON: return JSON.builder().toJson(this.fields);
            case ENV: return new ENV(this.fields).toString()+"\n";
            case XML:{
                try{
                    String xml="";
                    for(Map.Entry<String,Object> entry:this.fields.entrySet()){
                        String key=entry.getKey();
                        Object value=entry.getValue();
                        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder=factory.newDocumentBuilder();
                        Document document=builder.newDocument();
                        Element root_element=document.createElement(key);
                        root_element.appendChild(document.createTextNode(String.valueOf(value)));
                        document.appendChild(root_element);
                        TransformerFactory transformer_factory=TransformerFactory.newInstance();
                        Transformer transformer=transformer_factory.newTransformer();
                        DOMSource source=new DOMSource(document);
                        StringWriter writer=new StringWriter();
                        StreamResult result=new StreamResult(writer);
                        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"yes");
                        transformer.transform(source,result);
                        xml+=writer.toString()+"\n";
                    }
                    return xml.substring(0,xml.length()-1);
                }catch(Exception ex){
                    ex.printStackTrace();
                    return "";
                }
            }
            default: return null;
        }
    }
    
    public boolean save(){
        if(this.getType()!=null && this.getSrc()!=null && this.isFile()){
            return this.write(this.text());
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