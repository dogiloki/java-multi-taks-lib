package multitaks.directory;

import java.awt.Component;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import multitaks.Function;
import multitaks.GlobalVar;
import multitaks.ObjectId;
import multitaks.directory.enums.DirectoryType;

/**
 *
 * @author dogi_
 */

public class Storage{
    
    /**
     * Dar formato de direcorio remplazando \ por /
     * @param path Ruta del directorio
     * @return 
     */
    public static String formatPath(String path){
        return path.replace("\\","/");
    }
    
    /**
     * Crea una carpeta incluyendo si no existe las subcarpetas anteriores
     * @param path Ruta de la carpeta completa
     * @return Indica si se creó la carpeta final (las anteriores no las elimina, en caso de indicar false)
     */
    public static boolean createFolder(String path){
        try{
            String ruta_crear="";
            for(String sub_ruta:Storage.formatPath(path).split("/")){
                ruta_crear+=sub_ruta+"/";
                File directorio=new File(ruta_crear);
                if(!directorio.exists()){
                    directorio.mkdir();
    
                }
            }
            return true;
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex,"Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * Renombrar un fichero
     * @param name_old Ruta del fichero anterior / viejo
     * @param name_new Ruta del fichero a renombrar nuevo
     * @return Indica si renombró correctamente el fichero
     */
    public static boolean rename(String name_old, String name_new){
        File directorio_old=new File(name_old);
        File directorio_new=new File(name_new);
        return directorio_old.renameTo(directorio_new);
    }
    
    /**
     * Reescribir en un archivo
     * @param path Ruta del fichero
     * @param text Contenido a reecribir
     * @return Indicar si se reescribió el archivo
     */
    public static boolean writerFile(String path, Object text){
        return new Storage(path).write(text);
    }
    
    /**
     * Adicionar contenido al final del fichero
     * @param path Ruta del fichero
     * @param text Contenido a adicionar
     * @return Indica si se adiciono el contenido en el archivo
     */
    public static boolean appendFile(String path, Object text){
        return new Storage(path).append(text);
    }
    
    /**
     * Reescribe contenido de una línea en especifica con nuevo contenido dentro del fichero
     * @param path Ruta del fichero
     * @param text Contenido que se adicciona al fichero
     * @param number Número de la línea donde se reecribira en contenido
     * @return Indica si se reescribio el fichero
     */
    
    public static boolean writeLine(String path, Object text, long number){
        return new Storage(path).writeLine(text,number);
    }
    
    /**
     * Obtiene todo el contenido de un fichero de un fichero
     * @param path Ruta del fichero
     * @return Devuelve el contenido en un solo String con sus respectivos salto de línea
     */
    public static String readFile(String path){
        return new Storage(path).read();
    }
    
    /**
     * Obtiene todo el contenido de un fichero en forma de iterador en lugar de leer todo fichero
     * @param path Ruta del fichero
     * @return Scanner con el método en forma de iterador
     */
    public static Scanner readIterator(String path){
        return new Storage(path).readIterator();
    }
    
    /**
     * Obtine el tamaño en bits de un fichero ya sea carpeta o arvhivo
     * @param path Ruta del fichero
     * @return Devuelve el tamaño de ficheros en bits
     */
    public static long getSize(String path){
        return new Storage(path).getSize();
    }
    
    /**
     * Lista todos los fichero en caso de ser carpeta
     * @param path Ruta del fichero
     * @return Devuelve un objeto de tipo DirectoryList que actual como iterador
     */
    public static DirectoryList listDirectory(String path){
        return new Storage(path).listDirectory();
    }
    
    /**
     * Lista todos las carpetas en caso de ser carpetas
     * @param path Ruta del fichero
     * @return Devuelve un objeto de tipo DirectoryList que actual como iterador
     */
    public static DirectoryList listFolders(String path){
        return new Storage(path).listFolders();
    }
    
    /**
     * Lista todos los archivos en caso de ser una carpetaç
     * @param path Ruta del fichero
     * @return Devuelve un objeto de tipo DirectoryList que actual como iterador
     */
    public static DirectoryList listFiles(String path){
        return new Storage(path).listFiles();
    }
    
    /**
     * Verificar si existe un directorio (carpeta o archivo)
     * @param path Ruta del fichero
     * @return Indica si existe el directorio
     */
    public static boolean exists(String path){
        return new Storage(path).exists();
    }
    
    /**
     * Verificar si existe un directorio indicando si es carpeta o archivo y si crearlo
     * @param path Ruta del directorio
     * @param type Indicar si es una carpeta o archivo con el enum DirectoryType
     * @param created Indicar si crearlo en caso de no existir
     * @return Indica si existe el directorio y si se creo correctamente en caso de indicarlo
     */
    public static boolean exists(String path, DirectoryType type, boolean created){
        return new Storage()._exists(path,type,created);
    }
    
    /**
     * Saber si es una carpeta
     * @param path Ruta del directorio
     * @return Indica si es una carpeta
     */
    public static boolean isFolder(String path){
        return new File(path).isDirectory();
    }
    
    /**
     * Saber si es un archivo
     * @param path Ruta del directorio
     * @return Indica si es un archivo
     */
    public static boolean isFile(String path){
        return new File(path).isFile();
    }
    
    /**
     * Obtener extensión del un archivo
     * @param path Ruta del fichero
     * @return Devuelve un String con el nombre de la extesión
     */
    public static String getExtension(String path){
        return new Storage(path).getExtension();
    }
    
    /**
     * Obtener nombre de una archivo sin extensión
     * @param path Ruta del fichero
     * @return Devuelve en un String el nombre del archivo (sin extensión)
     */
    public static String getNameNotExtension(String path){
        return new Storage(path).getNameNotExtension();
    }
    
    /**
     * Obtener nombre de una archivo con extensión
     * @param path Ruta del fichero
     * @return Devuelve en un String el nombre del archivo (con extensión)
     */
    public static String getName(String path){
        return new Storage(path).getName();
    }
    
    /**
     * Obtiene la carpeta en la que esta un archivo
     * @param path Ruta del fichero
     * @return Devuelve en un String el nombre de la carpeta
     */
    public static String getFolder(String path){
        return new Storage(path).getFolder();
    }
    
    /**
     * Comprimir archivos en formato .zip
     * @param path Ruta de la carpeta a compromir
     * @param name_zip Nombre del archivo .zip (sin la extensión .zip)
     * @return Indica si se comprimio el archivo
     */
    public static boolean compress(String path, String name_zip){
        try{
            ZipOutputStream zos=new ZipOutputStream(new FileOutputStream(name_zip+".zip"));
            zos=Storage.compress(path,zos,"");
            zos.close();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    
    /**
     * Comprimir archivos en formato .zip
     * @param path Ruta de la carpeta a compromir
     * @param zos ZipOutputStream Para almacenar subcarpetas
     * @param path_dir Nombre del archivo .zip (sin la extensión .zip)
     * @return Indica si se descomprimio correctamente
     */
    private static ZipOutputStream compress(String path, ZipOutputStream zos, String path_dir){
        try{
            DirectoryList directories=Storage.listDirectory(path);
            while(directories.hasNext()){
                String f=directories.next().getFileName().toString();
                String path_zip=path+"/"+f;
                if(Storage.isFolder(path_zip)){
                    zos=Storage.compress(path_zip,zos,(path_dir.equals("")?"":(path_dir+"/"))+f);
                    if(zos==null){
                        return null;
                    }
                    continue;
                }
                ZipEntry ze=new ZipEntry((path_dir.equals("")?"":(path_dir+"/"))+f);
                zos.putNextEntry(ze);
                FileInputStream fis=new FileInputStream(path_zip);
                byte[] buffer=new byte[1024];
                int len=0;
                while((len=fis.read(buffer))>0){
                    zos.write(buffer,0,len);
                }
                fis.close();
            }
            return zos;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    /**
     * Descomprimir archivos en formato .zip
     * @param path Ruta de la carpeta a compromir
     * @param path_zip Nombre del archivo .zip (incluyendo la extensión .zip)
     * @return 
     */
    public static boolean descompress(String path, String path_zip){
        try{
            ZipInputStream zis=new ZipInputStream(new FileInputStream(path_zip));
            ZipEntry zos;
            while((zos=zis.getNextEntry())!=null){
                String path_folder=path+"/"+zos.getName();
                if(zos.isDirectory()){
                    Storage.createFolder(path_folder);
                    continue;
                }else{
                    String[] sub_path=Storage.formatPath(path_folder).split("/");
                    String path_create="";
                    for(int a=0; a<sub_path.length-1; a++){
                        path_create+=sub_path[a]+"/";
                    }
                    path_create=path_create.substring(0,path_create.length()-1);
                    Storage.createFolder(path_create);
                }
                FileOutputStream fos=new FileOutputStream(path_folder);
                int len=0;
                byte[] buffer=new byte[1024];
                while((len=zis.read(buffer))>0){
                    fos.write(buffer,0,len);
                }
                fos.close();
            }
            zis.close();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    
    /**
     * @see Storage@_selectFile()
     */
    public static String selectFile(){ 
        return Storage._selectFile(null,"");
    }
    
    /**
     * @see Storage@_selectFile()
     */
    public static String selectFile(Component frame, String path_current){ 
        return Storage._selectFile(frame,path_current);
    }
    
    /**
     * @see Storage@_selectFile()
     */
    public static String selectFile(Component frame){ 
        return Storage._selectFile(frame,"");
    }
    
    /**
     * @see Storage@_selectFile()
     */
    public static String selectFile(String path_current){ 
        return Storage._selectFile(null,path_current);
    }
    
    /**
     * @see Storage@_selectFiles()
     */
    public static File[] selectFiles(Component frame, String path_current){
        return Storage._selectFiles(frame,path_current);
    }
    
    /**
     * @see Storage@_selectFiles()
     */
    public static File[] selectFiles(Component frame){
        return Storage._selectFiles(frame,"");
    }
    
    /**
     * @see Storage@_selectFiles()
     */
    public static File[] selectFiles(String path_current){ 
        return Storage._selectFiles(null,path_current);
    }
    
    /**
     * Muestra un selector de archivo
     * @param frame Clase de tipo java.awt.Component para anclar el selector de archivos
     * @param path_current Ruta en la que el selector se posisionara
     * @return Devuelve un String con lara ruta y nombre del archivo seleccionado
     */
    private static String _selectFile(Component frame, String path_current){ 
        JFileChooser chooser=new JFileChooser(path_current);
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.showOpenDialog(frame);
        try{
            return chooser.getSelectedFile().toString();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    /**
     * Muestra un selector de varios archivos
     * @param frame Clase de tipo java.awt.Component para anclar el selector de archivos
     * @param path_current Ruta en la que el selector se posisionara
     * @return Devuelve una array de tipo java.io.File de los archivos seleccionados
     */
    private static File[] _selectFiles(Component frame, String path_current){ 
        JFileChooser chooser=new JFileChooser(path_current);
        chooser.setMultiSelectionEnabled(true);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.showOpenDialog(frame);
        try{
            return chooser.getSelectedFiles();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    /**
     * @see Storage@_selectFolder()
     */
    public static String selectFolder(){ 
        return Storage._selectFolder(null,"");
    }
    
    /**
     * @see Storage@_selectFolder()
     */
    public static String selectFolder(Component frame, String path_current){ 
        return Storage._selectFolder(frame,path_current);
    }
    
    /**
     * @see Storage@_selectFolder()
     */
    public static String selectFolder(Component frame){ 
        return Storage._selectFolder(frame,"");
    }
    
    /**
     * @see Storage@_selectFolder()
     */
    public static String selectFolder(String path_current){ 
        return Storage._selectFolder(null,path_current);
    }
    
    /**
     * Selector de una carpeta
     * @param frame Clase de tipo java.awt.Component para una anclar a una ventana
     * @param path_current Ruta en la que el selector se posisionara
     * @return Devuelve un String con la ruta de la carpeta seleccionada
     */
    private static String _selectFolder(Component frame, String path_current){ 
        JFileChooser chooser=new JFileChooser(path_current);
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(frame);
        try{
            return chooser.getSelectedFile().toString();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    /**
     * Elimina un archivo
     * @param ruta Ruta del fichero
     * @return Indica si se eliminó en archivo (si no existe devuelve false)
     */
    public static boolean deleteFile(String ruta){
        try{
            File file=new File(ruta);
            if(!file.exists()){
                return false;
            }
            return file.delete();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    
    /**
     * Elimina una carpeta incluyendo subcarpetas y todo el contenido
     * @param path Ruta del fichero
     * @throws Exception Si hay un error al eliminar algún fichero (no hay rollback de lo anterior eliminado)
     */
    public static void deleteFolder(String path) throws Exception{
        try{
            File file;
            DirectoryList directories=Storage.listDirectory(path);
            while(directories.hasNext()){
                String directory=directories.next().getFileName().toString();
                file=new File(path+"/"+directory);
                if(file.isFile()){
                    file.delete();
                    continue;
                }
                Storage.deleteFolder(file.getPath());
                file.delete();
            }
            file=new File(path);
            if(file.isFile()){
                return;
            }
            String[] list=file.list();
            if(list==null || list.length<=0){
                file.delete();
            }
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    
    /**
     * Abrir archivo
     * @param path Ruta del fichero
     * @return Indica si el archivo se abrio
     */
    public static boolean execute(String path){
        return new Storage(path).execute();
    }
    
    /**
     * Almacenar el archivo path_file a la dirección path_store renombrandolo a un hash-256 y almacenandolo
     * @param path_file Ruta actual del archivo
     * @return Indicar si se almaceno
     */
    public static String store(String path_file){
        return Storage._store((String)GlobalVar.group("storage").get("store"),path_file);
    }
    
    /**
     * Almacenar el archivo path_file a la dirección path_store renombrandolo a un hash-256 y almacenandolo
     * @param path_store Ruta actual de la carpeta donde se almacenará
     * @param path_file Ruta actual del archivo
     * @return Indicar si se almaceno
     */
    public static String store(String path_store, String path_file){
        return Storage._store(path_store,path_file);
    }
    
    /**
     * Almacenar el archivo path_file a la dirección path_store renombrandolo a un hash-256 y almacenandolo
     * @param path_store Ruta actual de la carpeta donde se almacenará
     * @param path_file Ruta actual del archivo
     * @return Indicar si se almaceno
     */
    private static String _store(String path_store, String path_file){
        try{
            String ext=Storage.getExtension(path_file);
            String hash=ObjectId.generate();
            String path=path_store+"/"+hash.substring(0,2)+"/"+hash+"."+ext;
            Storage.copyFile(path_file,path);
            return path;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    /**
     * Obtener el fichero alamacenado por el método Storage.store()
     * @param name_file Nombre hash del archivo
     * @return Devuelve un File del archivo encontrado o null en caso de no encontrarlo
     */
    public static File get(String name_file){
        return Storage._get((String)GlobalVar.group("storage").get("store"),name_file);
    }
    
    /**
     * Obtener el fichero alamacenado por el método Storage.store()
     * @param path_store Ruta actual de la carpeta donde se almacenó
     * @param name_file Nombre hash del archivo
     * @return Devuelve un File del archivo encontrado o null en caso de no encontrarlo
     */
    public static File get(String path_store, String name_file){
        return Storage._get(path_store,name_file);
    }
    
    /**
     * Obtener el fichero alamacenado por el método Storage.store()
     * @param path_store Ruta actual de la carpeta donde se almacenó
     * @param name_file Nombre hash del archivo
     * @return Devuelve un File del archivo encontrado o null en caso de no encontrarlo
     */
    private static File _get(String path_store, String name_file){
        try{
            String name=Storage.getNameNotExtension(name_file);
            DirectoryList files=Storage.listFiles(path_store+"/"+name.substring(0,2));
            while(files.hasNext()){
                String path=files.next().toString();
                if(name.equals(Storage.getNameNotExtension(path))){
                    return new File(path);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    /**
     * Copiar un archivo
     * @param path_old Ruta actual del archivo
     * @param path_new Ruta a copiar del archivo
     * @throws Exception Se dispara un excepción si hay error al copiar el archivo
     */
    public static void copyFile(String path_old, String path_new) throws Exception{
        File directory_old=new File(path_old);
        File directory_new=new File(path_new);
        if(!directory_old.exists()){
            throw new Exception("Not exists "+path_old);
        }else
        if(!directory_old.isFile()){
            throw new Exception(path_old+" not a file");
        }
        try{
            Storage.createFolder(directory_new.getParent());
            InputStream in=new FileInputStream(directory_old);
            OutputStream out=new FileOutputStream(directory_new);
            byte[] buf=new byte[1024];
            int len;
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
            in.close();
            out.close();
        }catch(IOException ex){
            if(directory_new.exists()){
                directory_new.delete();
            }
            throw new Exception(ex.getMessage());
        }
    }
    
    /**
     * 
     * @param path_old Ruta actual de la carpeta
     * @param path_new Ruta a copiar de la carpeta
     * @throws Exception Se dispara un excepción si hay error al mover algún archivo
     */
    public static void copyDirectory(String path_old, String path_new) throws Exception{
        Storage._copyDirectory(path_old,path_new,null);
    }
    
    /**
     * 
     * @param path_old Ruta actual de la carpeta
     * @param path_new Ruta a copiar de la carpeta
     * @param context Ventana a anclar (pendiente)
     * @throws Exception Se dispara un excepción si hay error al mover algún archivo
     */
    public static void copyDirectory(String path_old, String path_new, Component context) throws Exception{
        Storage._copyDirectory(path_old,path_new,context);
    }
    
    /**
     * 
     * @param path_old Ruta actual de la carpeta
     * @param path_new Ruta a copiar de la carpeta
     * @param context Ventana a anclar (pendiente)
     * @throws Exception Se dispara un excepción si hay error al mover algún archivo
     */
    private static void _copyDirectory(String path_old, String path_new, Component context) throws Exception{
        File directory_old=new File(path_old);
        File directory_new=new File(path_new);
        if(!directory_old.exists()){
            throw new Exception("Not exists "+path_old);
        }else
        if(directory_old.isDirectory()){
            directory_new.mkdir();
        }
        try{
            DirectoryList directories=Storage.listDirectory(path_old);
            while(directories.hasNext()){
                String directory=directories.next().getFileName().toString();
                if(Storage.isFolder(path_old+"/"+directory)){
                    Storage._copyDirectory(path_old+"/"+directory, path_new+"/"+directory,context);
                }else
                if(Storage.isFile(path_old+"/"+directory)){
                    Storage.copyFile(path_old+"/"+directory, path_new+"/"+directory);
                }
            }
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    
    /**
     * Obtiene la ruta del programa ejecutandose
     * @return Devuelve en un String ruta de la carpeta
     */
    public static String getDir(){
        return Storage.formatPath(new File("").getAbsolutePath());
    }
    
    private String _src=null;
    private DirectoryType _type=null;
    private File file=null;
    private BufferedWriter bw=null;
    private BufferedReader br=null;
    
    public Storage(){
        
    }
    
    public Storage(String src){
        this.aim(src,null);
    }
    
    public Storage(String src, DirectoryType type){
        this.aim(src,type);
    }
    
    public void aim(String src, DirectoryType type){
        this.setSrc(src);
        File file;
        if(type==null && (file=new File(src)).exists()){
            type=file.isDirectory()?DirectoryType.FOLDER:DirectoryType.FILE;
        }
        this.setType(type);
    }
    
    public String getSrc(){
        return this._src;
    }
    
    public void setSrc(String src){
        this._src=src;
    }
    
    public DirectoryType getType(){
        return this._type;
    }
    
    public void setType(DirectoryType type){
        this._type=type;
    }
    
    /**
     * Abre fichero inicializando su BufferedWriter y BufferedReader
     * @param append Indicar si abrir el fichero para adicción de texto
     * @return Indica si se abrió correctamente el fichero
     */
    private boolean open(boolean append){
        if(this.getType()!=null && this.getType()!=DirectoryType.FOLDER && this.getSrc()!=null){
            try{
                this.file=new File(this.getSrc());
                this.bw=new BufferedWriter(new FileWriter(this.file,append));
                this.br=new BufferedReader(new FileReader(this.file));
                return true;
            }catch(IOException ex){
                ex.printStackTrace();
                return false;
            }
        }
        return false;
    }
    
    /**
     * Reescribe el fichero con contenido vacio
     * @return Indica si se limpio el fichero
     */
    public boolean clean(){
        try{
            if(!this.open(false) || !this.close()){
                return false;
            }
            this.bw.write("");
            this.close();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    /**
     * Adicionar contenido a un fichero
     * @param text Contenido a adicionar en el fichero
     * @return Indica si se adiciono el contenido en el fichero.
     */
    public boolean append(Object text){
        try{
            if(!this.open(true)){
                return false;
            }
            this.bw.write(((String)text));
            this.close();
            return true;
        }catch(IOException ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    /**
     * Reescribir el fichero con nuevo contenido
     * @param text Contenido a reescribir en el fichero
     * @return Indica si se reescribio el nuevo contenido en el fichero
     */
    public boolean write(Object text){
        try{
            if(!this.open(false)){
                return false;
            }
            this.bw.write(((String)text));
            this.close();
            return true;
        }catch(IOException ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    /**
     * Reescribe contenido de una línea en especifica con nuevo contenido dentro del fichero
     * @param text Contenido que se adicciona al fichero
     * @param number Número de la línea donde se reecribira en contenido
     * @return Indica si se reescribio el fichero
     */
    public boolean writeLine(Object text, long number){
        try{
            RandomAccessFile file=new RandomAccessFile(this.getSrc(),"rw");
            Scanner reader=this.readIterator();
            int current_number=1;
            List<String> lines=new ArrayList<>();
            while(reader.hasNextLine()){
                String line=reader.nextLine();
                line=(current_number==number?Function.assign((String)text,""):line);
                if(line!=null){
                    lines.add(line);
                }
                current_number++;
            }
            this.close();
            this.clean();
            if(!this.open(true)){
                return false;
            }
            for(String line:lines){
                this.bw.write(line+"\n");
            }
            this.close();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    /**
     * Obtiene todo el contenido de un fichero de un fichero
     * @return Devuelve el contenido en un solo String con sus respectivos salto de línea
     */
    public String read(){
        try{
            if(!this.open(true)){
                return null;
            }
            String line=null;
            String lines="";
            while((line=this.br.readLine())!=null){
                lines+=line+"\n";
            }
            this.close();
            return lines;
        }catch(IOException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    /**
     * Obtiene todo el contenido de un fichero en forma de iterador en lugar de leer todo fichero
     * @return Scanner con el método en forma de iterador
     */
    public Scanner readIterator(){
        Scanner in=new Scanner(System.in);
        in.useDelimiter("\n");
        if(!this.open(true)){
            return in;
        }
        try{
            in=new Scanner(this.file);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return in;
    }
    
    /**
     * Cierra fichero, cerrando su BufferedWriter y BufferedReader
     * @return 
     */
    public boolean close(){
        try{
            if(this.bw==null || this.br==null){
                return true;
            }
            this.bw.close();
            this.br.close();
            return true;
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return false;
    }
    
    /**
     * Eliminar fichero. Incuyendo si es un carpeta con subcarpetas y archivos
     * @return Indica si se eliminó correctamente
     */
    public boolean delete(){
        try{
            if(this.getType()==DirectoryType.FOLDER){
                Storage.deleteFolder(this.getSrc());
            }else{
                Storage.deleteFile(this.getSrc());
            }
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    
    /**
     * Obtine el tamaño en bits de un fichero ya sea carpeta o arvhivo
     * @return Devuelve el tamaño de ficheros en bits
     */
    public long getSize(){
        return this._getSize(this.getSrc(),0);
    }
    /**
     * Obtine el tamaño en bits de un fichero ya sea carpeta o arvhivo
     * @param path Ruta del directorio
     * @param size Peso del fichero en bits para recursividad (subcarpetas)
     * @return Devuelve el tamaño de ficheros en bits
     */
    private long _getSize(String path, long size){
        File directory=new File(path);
        if(directory.isDirectory()){
            DirectoryList directories=this.listDirectory(path);
            while(directories.hasNext()){
                String path_=directories.next().getFileName().toString();
                File direct=new File(path+"/"+path_);
                if(direct.isDirectory()){
                    size=this._getSize(direct.getPath(), size);
                }else
                if(direct.isFile()){
                    size+=direct.length();
                }
            }
        }else
        if(directory.isFile()){
            size+=directory.length();
        }else{
            size=-1;
        }
        return size;
    }
    
    /**
     * Lista todos los fichero en caso de ser carpeta
     * @return Devuelve un objeto de tipo DirectoryList que actual como iterador
     */
    public DirectoryList listDirectory(){
        return this._listDirectory(this.getSrc(),DirectoryType.ALL);
    }
    
    /**
     * Lista todos las carpetas en caso de ser carpetas
     * @return Devuelve un objeto de tipo DirectoryList que actual como iterador
     */
    public DirectoryList listFolders(){
        return this._listDirectory(this.getSrc(),DirectoryType.FOLDER);
    }
    
    /**
     * Lista todos los archivos en caso de ser una carpeta
     * @return Devuelve un objeto de tipo DirectoryList que actual como iterador
     */
    public DirectoryList listFiles(){
        return this._listDirectory(this.getSrc(),DirectoryType.FILE);
    }
    
    /**
     * Lista todos los archivos y carpetas de un directorio
     * @param path Ruta del directorio
     * @param type Indicar si enlistar carpetas, archivo o todo con el enum DirectoryType
     * @return Devuelve un objeto de tipo DirectoryList que actual como iterador
     */
    private DirectoryList _listDirectory(String path, DirectoryType type){
        return new DirectoryList(path,type);
    }
    
    /**
     * Verificar si existe un directorio (carpeta o archivo)
     * @return Indica si existe el directorio
     */
    public boolean exists(){
        return this._exists(this.getSrc(),this.getType(),false);
    }
    
    /**
     * Verificar si existe un directorio (carpeta o archivo)
     * @param created Indicar si crearlo en caso de no existir
     * @return Indica si existe el directorio
     */
    public boolean exists(boolean created){
        return this._exists(this.getSrc(),this.getType(),created);
    }
    
    /**
     * Verificar si existe un directorio indicando si es carpeta o archivo y si crearlo
     * @param path Ruta del directorio
     * @param type Indicar si es una carpeta o archivo con el enum DirectoryType
     * @param created Indicar si crearlo en caso de no existir
     * @return Indica si existe el directorio y si se creo correctamente en caso de indicarlo
     */
    private boolean _exists(String path, DirectoryType type, boolean created){
        File directorio=new File(path);
        if(!directorio.exists() && created){
            Storage store=new Storage(path,type);
            switch(type){
                case FOLDER: return Storage.createFolder(path);
                case FILE: return store.write("");
            }
            store.close();
        }
        return directorio.exists();
    }
    
    /**
     * Saber si es una carpeta
     * @return Indica si es una carpeta
     */
    public boolean isFolder(){
        return this.file.isDirectory();
    }
    
    /**
     * Saber si es un archivo
     * @return Indica si es un archivo
     */
    public boolean isFile(){
        return this.file.isFile();
    }
    
    /**
     * Obtener extensión del un archivo
     * @return Devuelve un String con el nombre de la extesión
     */
    public String getExtension(){
        return this._getExtension(this.getSrc());
    }
    
    /**
     * Obtener extensión del un archivo
     * @param path Ruta del fichero
     * @return Devuelve un String con el nombre de la extesión
     */
    private String _getExtension(String path){
        String[] path_array=path.split("\\.");
        return path_array[path_array.length-1];
    }
    
    /**
     * Obtener nombre de una archivo sin extensión
     * @return Devuelve en un String el nombre del archivo (sin extensión)
     */
    public String getNameNotExtension(){
        return this._getNameNotExtension(this.getSrc());
    }
    
    /**
     * Obtener nombre de una archivo sin extensión
     * @param path Ruta del fichero
     * @return Devuelve en un String el nombre del archivo (sin extensión)
     */
    private String _getNameNotExtension(String path){
        String[] path_array=Storage.formatPath(path).split("/");
        return (path_array[path_array.length-1]).split("\\.")[0];
    }
    
    /**
     * Obtener nombre de una archivo con extensión
     * @return Devuelve en un String el nombre del archivo (con extensión)
     */
    public String getName(){
        return this._getName(this.getSrc());
    }
    
    /**
     * Obtener nombre de una archivo con extensión
     * @param path Ruta del fichero
     * @return Devuelve en un String el nombre del archivo (con extensión)
     */
    private String _getName(String path){
        String[] path_array=Storage.formatPath(path).split("/");
        return path_array[path_array.length-1];
    }
    
    /**
     * Obtiene la carpeta en la que esta un archivo
     * @return Devuelve en un String el nombre de la carpeta
     */
    public String getFolder(){
        return this._getFolder(this.getSrc());
    }
    
    /**
     * Obtiene la carpeta en la que esta un archivo
     * @param path Ruta del fichero
     * @return Devuelve en un String el nombre de la carpeta
     */
    private String _getFolder(String path){
        String[] array=Storage.formatPath(path).split("/");
        return String.join("/",Arrays.copyOfRange(array,0,array.length-1));
    }
    
    /**
     * Abrir archivo
     * @return Indica si el archivo se abrio
     */
    public boolean execute(){
        try{
            if(!this.open(false)){
                return false;
            }
            Desktop.getDesktop().open(this.file);
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    
}
