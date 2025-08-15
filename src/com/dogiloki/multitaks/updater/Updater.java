package com.dogiloki.multitaks.updater;

/**
 *
 * @author _dogi
 */

public class Updater{
    
    private Manifest local_manifest;
    private Manifest remote_manifest;
    private String base_directory;
    
    public Updater(String base_directory){
        this.base_directory=base_directory;
    }
    
}
