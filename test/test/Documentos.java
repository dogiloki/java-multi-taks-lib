package test;

import multitaks.annotations.directory.Directory;
import multitaks.annotations.directory.Key;
import multitaks.enums.DirectoryType;

/**
 *
 * @author dogi_
 */

@Directory(type=DirectoryType.JSON)
public class Documentos{
    
    @Key(value="name")
    public String name="C:\\Escritorio\\Hola.txt";
    
}
