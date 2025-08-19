package com.dogiloki.multitaks.updater.enums;

/**
 *
 * @author _dogi
 */

public enum UpdateStatus{
    
    IDLE, // Inactivo
    CHECKING, // Revisando si hay actualizaciones
    NO_UPDATE, // No hay actulizaciones
    DOWNLOADING, // Descargando archivos
    DOWNLOAD_COMPLETED, // Descarga finalizada
    APPLYING, // Aplicando la actualización
    FINALIZED, // Actualización completada exitosamente
    FAILED, // Falloó algún paso
    
}
