package com.dogiloki.multitaks.locale;

import com.dogiloki.multitaks.Properties;
import com.dogiloki.multitaks.dataformat.ENV;
import com.dogiloki.multitaks.dataformat.Format;
import com.dogiloki.multitaks.directory.Storage;
import com.dogiloki.multitaks.directory.enums.DirectoryType;

/**
 *
 * @author _dogi
 */

public class Lang{
    
    private String locale;
    private Storage file;
    private ENV items;
    
    public Lang(String locale){
        this.locale(locale);
    }
    
    public void locale(String locale){
        this.locale=locale;
    }
    
    public Lang item(String item){
        this.file=new Storage(Properties.LOCALE_FOLDER+"/"+this.locale+"/"+item+".opt",DirectoryType.FILE);
        this.file.exists(true);
        this.items=new ENV(this.file.read());
        return this;
    }
    
    public Format format(String key){
        return new Format(this.items.getValue(key).toString());
    }
    
}
