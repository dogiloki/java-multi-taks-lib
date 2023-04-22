package multitaks.directory;

import com.google.gson.Gson;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import multitaks.annotations.directory.Directory;
import multitaks.annotations.directory.Key;
import multitaks.enums.DirectoryType;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import multitaks.Function;
import multitaks.annotations.directory.Execute;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import multitaks.dataformat.ENV;
import multitaks.interfaces.DataFormat;

/**
 *
 * @author dogi_
 */
public class ModelDirectory extends Storage{
    
    public static Object aim(String src, Class clazz){
        ModelDirectory model=new ModelDirectory(src,clazz);
        model.setText();
        return model.getInstance();
    }
    private Class<?> child_class;
    private Object instance;
    /**
     * String clave para formato de texto
     * String valor del atributo de la clase
    */
    private Map<String,Object> fields=new HashMap<>();
    /**
     * String clave para formato de texto
     * Field variable de la clase
    */
    private Map<String,Field> attributes=new HashMap<>();

    public ModelDirectory(){
        super();
        this._aim(this,this.getSrc());
    }
    
    public ModelDirectory(String src, Class clazz){
        super();
        try{
            this._aim(clazz.newInstance(),src);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public ModelDirectory(String src){
        super();
        this._aim(this,src);
    }
    
    public ModelDirectory(Object instance){
        super();
        this._aim(instance,null);
    }
    
    public void aim(Object instance){
        this._aim(instance,null);
    }
    public void aim(Object instance, String src){
        this._aim(instance,src);
    }
    
    private void _aim(Object instance, String src){
        this.instance=instance;
        this.child_class=instance.getClass();
        this.src=src;
        Directory annot_directory=this.child_class.getAnnotation(Directory.class);
        if(annot_directory instanceof Directory){
            this.type=annot_directory.type();
        }
        this.create();
        super.aim(src,this.type);
    }
    
    private void create(){
        if(this.type==null || this.src==null){
            return;
        }
        if(this.isFolder()){
            Storage.createFolder(this.src);
        }else{
            Storage.exists(this.src,DirectoryType.FILE,true);
            this.get();
            //this.setText();
        }
    }
    
    public Object getInstance(){
        return this.instance;
    }
    
    public Map<String,Object> getFields(){
        return this.fields;
    }
    
    public void setFields(Map<String,Object> fields){
        this.fields=fields;
    }
    
    private void get(){
        try{
            Directory annot_directory=this.child_class.getAnnotation(Directory.class);
            if(annot_directory instanceof Directory){
                this.type=annot_directory.type();
                if(this.isFolder()){
                    return;
                }
            }
            for(Field field:this.child_class.getFields()){
                Key annot_key=field.getAnnotation(Key.class);
                if(annot_key instanceof Key){
                    Object value=field.get(this.instance);
                    this.fields.put(annot_key.value(),Function.assignNotNull(value,""));
                    this.attributes.put(annot_key.value(),field);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void setText(String text){
        this._setText(text);
    }
    public void setText(){
        this._setText(null);
    }
    private void _setText(String _text){
        String text=_text==null?this.read():_text;
        if(text==null || text.equals("")){
            return;
        }
        DataFormat data;
        switch(this.type){
            case JSON:{
                this.instance=new Gson().fromJson(text,this.child_class);
                return;
            }
            case ENV: data=new ENV(text); break;
            default: data=null; break;
        }
        if(data==null){
            return;
        }
        for(Map.Entry<String,Field> entry:this.attributes.entrySet()){
            String key=entry.getKey();
            Field field=entry.getValue();
            try{
                Object value=data.getValue(key);
                Key annot_key=field.getAnnotation(Key.class);
                if(value==null){
                    continue;
                }
                field.set(this.instance,value);
                /*if(annot_key.type()==FieldType.CLASS){
                    Class<?> type_class=field.getType();
                    ModelDirectory model=new ModelDirectory();
                    model.aim(type_class.getConstructor().newInstance());
                    value=model.setText(value.toString());
                    field.set(this.instance,value);
                    continue;
                }else
                if(annot_key.type()==FieldType.ENUM){
                    Class<Enum> type_class=(Class<Enum>)field.getType();
                    field.set(this.instance,Enum.valueOf(type_class,value.toString().replaceAll("\"","")));
                    continue;
                }else
                if(annot_key.type()==FieldType.LIST){
                    List<Object> values=new ArrayList<>();
                    List<Object> items;
                    if(this.type==DirectoryType.JSON){
                        items=new Gson().fromJson(value.toString(),new TypeToken<List<JsonElement>>(){}.getType());
                    }else{
                        items=(List)value;
                    }
                    if(items!=null){
                        for(Object item:items){
                            Class<?> type_class=Class.forName(((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0].getTypeName());
                            ModelDirectory model=new ModelDirectory();
                            model.aim(type_class.getConstructor().newInstance());
                            Object instance=model.setText(item.toString());
                            values.add(instance);
                        }
                        value=values;
                    }else{
                        value=new ArrayList<>();
                    }
                    field.set(this.instance,value);
                    continue;
                }
                String class_name=field.getType().getName();
                if(field.getType().isPrimitive()){
                    if(int.class.equals(field.getType())){
                        class_name+="eger";
                    }
                    class_name="java.lang."+class_name.substring(0,1).toUpperCase()+class_name.substring(1);
                }
                Class<?> class_type=Class.forName(class_name);
                Constructor<?> constructor=class_type.getDeclaredConstructor(field.getType());
                if(this.type==DirectoryType.JSON){
                    field.set(this.instance,constructor.newInstance(new Gson().fromJson((JsonPrimitive)value,class_type)));
                }else{
                    field.set(this.instance,constructor.newInstance(value));
                }*/
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        // Métodos
        for(Method method:this.instance.getClass().getMethods()){
            Execute annot_execute=method.getAnnotation(Execute.class);
            if(annot_execute==null){
                continue;
            }
            try{
                method.invoke(this.instance);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    public Object getText(){
        if(this.type==null){
            return null;
        }
        this.get();
        switch(this.type){
            case JSON: return new Gson().toJson(this.fields);
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
        if(this.type!=null && this.src!=null && this.isFile()){
            return this.write(this.getText());
        }
        return false;
    }
    
    public boolean reset(){
        if(this.type!=null && this.src!=null && this.isFile()){
            this.clean();
            return this.save();
        }
        return false;
    }
    
    public boolean isFile(){
        if(this.type==null){
            return false;
        }
        switch(this.type){
            case JSON: return true;
            case XML: return true;
            case ENV: return true;
            default: return false;
        }
    }
    
    public boolean isFolder(){
        if(this.type==null){
            return false;
        }
        return this.type==DirectoryType.FOLDER;
    }
    
}