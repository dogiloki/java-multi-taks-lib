package com.dogiloki.multitaks.updater.enums;

/**
 *
 * @author _dogi
 */

public enum UpdateStatus{
    
    IDLE, // Inactivo
    CHECKING, // Revisando si hay actualización
    UPDATE, // Hay actualización
    NO_UPDATE, // No hay actualización
    DOWNLOADING, // Descargando archivos
    DOWNLOAD_COMPLETED, // Descarga finalizada
    APPLYING, // Aplicando la actualización
    COMPLETED, // Actualización completada exitosamente
    FAILED, // Falló algún paso
    
}
