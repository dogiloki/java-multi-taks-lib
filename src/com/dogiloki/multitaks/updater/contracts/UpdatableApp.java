package com.dogiloki.multitaks.updater.contracts;

/**
 *
 * @author _dogi
 */

public interface UpdatableApp{
    
    // Obtener manifest remoto para validar archivos
    public void loadRemoteManifest(boolean bloking);
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
    
    // Validar hash
    public boolean verifyUpdate();
    // Respaldar versión actual antes de aplicar la nueva
    public boolean backupCurrent();
    
    // Notificar progreso de descarga/instalación
    public void onProgress();
    // Notificar errores durante el proceso
    public void onError(Exception ex);
    // Acción cuando finalice la actualización
    public void onComplete();
    
}
