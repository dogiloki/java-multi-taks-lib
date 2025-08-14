package com.dogiloki.multitaks.logger.contracts;

import com.dogiloki.multitaks.logger.LogEntry;

/**
 *
 * @author _dogi
 */

public abstract class LogListener{
    
    // Se llama cada vez que se agrega un mensaje
    public void onLogAdded(LogEntry entry){}
    
    // Se llama cuando se crea un nuevo archivo log
    public void onLogFileCreated(String path){}
    
    // Se llama cuando se borra un archivo antiguo (pendiente) onLogRetention
    public void onLogFileDeleted(String path){}
    
}
