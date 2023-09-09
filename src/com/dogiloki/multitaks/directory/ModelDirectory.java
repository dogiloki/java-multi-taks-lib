package com.dogiloki.multitaks.directory;

import com.dogiloki.multitaks.directory.annotations.Directory;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import com.dogiloki.multitaks.Function;
import com.dogiloki.multitaks.dataformat.ENV;
import com.dogiloki.multitaks.dataformat.JSON;
import com.dogiloki.multitaks.dataformat.XML;
import com.dogiloki.multitaks.directory.annotations.Execute;
import com.dogiloki.multitaks.dataformat.contracts.DataFormat;
import java.lang.reflect.Method;

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
    
    public <T extends Object> T builder(){
        String text=this.read();
        DataFormat data=null;
        Object instance=this.getInstance();
        switch(this.getType()){
            case JSON: data=new JSON(text); break;
            case ENV: data=new ENV(text); break;
            case XML: data=new XML(text); break;
        }
        if(data==null){
            return null;
        }
        instance=data.from(instance.getClass());
        if(instance==null){
            return null;
        }
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
            case XML:{
                try{
                    String xml="";
                    /*
                    for(Map.Entry entry:fields.entrySet()){
                        String key=entry.getKey().toString();
                        Object value=entry.getValue();
                        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder=factory.newDocumentBuilder();
                        Document document=builder.newDocument();
                        Element root_element=document.createElement(key);
                        root_element.appendChild(document.createTextNode(value.toString()));
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
                    */
                    return xml.substring(0,xml.length()-1);
                }catch(Exception ex){
                    ex.printStackTrace();
                    return "";
                }
            }
        }
        if(data==null){
            return "";
        }
        text=data.toString();
        return text;
    }
    
    public boolean save(){
        if(this.getType()!=null && this.getSrc()!=null && this.isFile()){
            return this.write(this.toString());
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