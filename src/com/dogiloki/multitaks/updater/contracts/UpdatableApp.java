package com.dogiloki.multitaks.updater.contracts;

/**
 *
 * @author _dogi
 */

public interface UpdatableApp{
    
    // Verificar si hay una nueva versión disponible
    public boolean checkForUpdates();
    // Descargar actualización
    public void downloadUpdate();
    // Aplicar actualización
    public void applyUpdate();
    
    // Consultar actual versión
    public String getCurrentVersion();
    // Consultar última versión
    public String getLastVersion();

    // Aplicar respaldo de la última versión anterior
    public void applyBackup();
    
    // Notificar progreso de descarga/instalación
    public void onProgress();
    // Notificar errores durante el proceso
    public void onError(Exception ex);
    // Acción cuando finalice la actualización
    public void onComplete();
    
}
