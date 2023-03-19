package test;

import multitaks.annotations.directory.Directory;
import multitaks.annotations.directory.Key;
import multitaks.enums.DirectoryType;

@Directory(type=DirectoryType.JSON)
public class Direccion{
    
    @Key(value="calle")
    public String calle="Morelos";
    @Key(value="cp")
    public int cp=55846;
    
}