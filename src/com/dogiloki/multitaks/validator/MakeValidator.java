package com.dogiloki.multitaks.validator;

import com.dogiloki.multitaks.Singleton;

/**
 *
 * @author _dogi
 */

public class MakeValidator{
    
    public MakeValidator(){
        Singleton.singleton(Validator.class).make("required",(Object key, Object value, MapValues values, Object[] params)->{
            return values.containsKey(key);
        },":key es obligatorio");
        Singleton.singleton(Validator.class).make("string",(Object key, Object value, MapValues values, Object[] params)->{
            return false;
        },":key debe ser un texto");
        Singleton.singleton(Validator.class).make("integer",(Object key, Object value, MapValues values, Object[] params)->{
            return false;
        },":key debe ser un número entero");
        Singleton.singleton(Validator.class).make("decimal",(Object key, Object value, MapValues values, Object[] params)->{
            return false;
        },":key debe ser decimal");
        Singleton.singleton(Validator.class).make("numeric",(Object key, Object value, MapValues values, Object[] params)->{
            return false;
        },":key debse se un número");
        Singleton.singleton(Validator.class).make("boolean",(Object key, Object value, MapValues values, Object[] params)->{
            return false;
        },":key debe ser falso/verdadero");
        Singleton.singleton(Validator.class).make("date",(Object key, Object value, MapValues values, Object[] params)->{
            return false;
        },":key debe ser una fecha");
        Singleton.singleton(Validator.class).make("min",(Object key, Object value, MapValues values, Object[] params)->{
            return false;
        },":key debe ser minimo :0");
        Singleton.singleton(Validator.class).make("max",(Object key, Object value, MapValues values, Object[] params)->{
            return false;
        },":key debe ser máximo :0");
        Singleton.singleton(Validator.class).make("between",(Object key, Object value, MapValues values, Object[] params)->{
            return false;
        },":key debe estar entre :0 y :1");
        Singleton.singleton(Validator.class).make("email",(Object key, Object value, MapValues values, Object[] params)->{
            return false;
        },":key debe ser un email");
        Singleton.singleton(Validator.class).make("json",(Object key, Object value, MapValues values, Object[] params)->{
            return false;
        },":key debe ser un json");
        Singleton.singleton(Validator.class).make("url",(Object key, Object value, MapValues values, Object[] params)->{
            return false;
        },":key debe ser una url");
        Singleton.singleton(Validator.class).make("regex",(Object key, Object value, MapValues values, Object[] params)->{
            return false;
        },":key debe tener un formato válido");
        Singleton.singleton(Validator.class).make("same",(Object key, Object value, MapValues values, Object[] params)->{
            return false;
        },":key debe ser igual a :0");
        Singleton.singleton(Validator.class).make("differed",(Object key, Object value, MapValues values, Object[] params)->{
            return false;
        },":key debe ser diferente a :0");
        Singleton.singleton(Validator.class).make("confirmed",(Object key, Object value, MapValues values, Object[] params)->{
            return false;
        },":key la confirmación no es correcta");
        this.builder();
    }
    
    public void builder(){
        
    }
    
}
