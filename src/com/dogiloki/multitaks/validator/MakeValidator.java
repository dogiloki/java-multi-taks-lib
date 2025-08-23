package com.dogiloki.multitaks.validator;

import com.dogiloki.multitaks.Singleton;
import com.dogiloki.multitaks.validator.enums.ValidatorRule;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.function.BiFunction;

/**
 *
 * @author _dogi
 */

public class MakeValidator{
    
    public MakeValidator(){
        Singleton.singleton(Validator.class).make(ValidatorRule.REQUIRED,(Object key, Object value, MapValues values, Object[] params)->{
            return values.containsKey(key);
        },":key es obligatorio");
        Singleton.singleton(Validator.class).make(ValidatorRule.STRING,(Object key, Object value, MapValues values, Object[] params)->{
            return value instanceof String;
        },":key debe ser un texto");
        Singleton.singleton(Validator.class).make(ValidatorRule.INTEGER,(Object key, Object value, MapValues values, Object[] params)->{
            try{
                if(value instanceof Integer) return true;
                Integer.parseInt(value.toString());
                return true;
            }catch(Exception ex){
                return false;
            }
        },":key debe ser un número entero");
        Singleton.singleton(Validator.class).make(ValidatorRule.DECIMAL,(Object key, Object value, MapValues values, Object[] params)->{
            try{
                if(value instanceof Float || value instanceof Double) return true;
                Double.parseDouble(value.toString());
                return true;
            }catch(Exception ex){
                return false;
            }
        },":key debe ser decimal");
        Singleton.singleton(Validator.class).make(ValidatorRule.NUMERIC,(Object key, Object value, MapValues values, Object[] params)->{
            try{
                Double.parseDouble(value.toString());
                return true;
            }catch(Exception ex){
                return false;
            }
        },":key debse se un número");
        Singleton.singleton(Validator.class).make(ValidatorRule.BOOLEAN,(Object key, Object value, MapValues values, Object[] params)->{
            return "true".equalsIgnoreCase(value.toString()) || "false".equalsIgnoreCase(value.toString());
        },":key debe ser falso/verdadero");
        Singleton.singleton(Validator.class).make(ValidatorRule.DATE,(Object key, Object value, MapValues values, Object[] params)->{
            try{
                LocalDate.parse(value.toString());
                return true;
            }catch(Exception ex){
                return false;
            }
        },":key debe ser una fecha");
        Singleton.singleton(Validator.class).make(ValidatorRule.MIN,(Object key, Object value, MapValues values, Object[] params)->{
            return Double.parseDouble(value.toString())>=Double.parseDouble(params[0].toString());
        },":key debe ser mínimo :0");
        Singleton.singleton(Validator.class).make(ValidatorRule.MAX,(Object key, Object value, MapValues values, Object[] params)->{
            return Double.parseDouble(value.toString())<=Double.parseDouble(params[0].toString());
        },":key debe ser máximo :0");
        Singleton.singleton(Validator.class).make(ValidatorRule.BETWEEN,(Object key, Object value, MapValues values, Object[] params)->{
            double val=Double.parseDouble(value.toString());
            double min=Double.parseDouble(params[0].toString());
            double max=Double.parseDouble(params[1].toString());
            return val>=min && val<=max;
        },":key debe estar entre :0 y :1");
        Singleton.singleton(Validator.class).make(ValidatorRule.EMAIL,(Object key, Object value, MapValues values, Object[] params)->{
            return value.toString().matches("^[\\w\\.-]+@[\\w\\.-]+\\.\\w+$");
        },":key debe ser un email");
        Singleton.singleton(Validator.class).make(ValidatorRule.JSON,(Object key, Object value, MapValues values, Object[] params)->{
            try{
                new ObjectMapper().readTree(value.toString());
                return true;
            }catch(Exception ex){
                return false;
            }
        },":key debe ser un json");
        Singleton.singleton(Validator.class).make(ValidatorRule.URL,(Object key, Object value, MapValues values, Object[] params)->{
            try{
                new URL(value.toString());
                return true;
            }catch(Exception ex){
                return false;
            }
        },":key debe ser una url");
        Singleton.singleton(Validator.class).make(ValidatorRule.REGEX,(Object key, Object value, MapValues values, Object[] params)->{
            return value.toString().matches(params[0].toString());
        },":key debe tener un formato válido");
        Singleton.singleton(Validator.class).make(ValidatorRule.SAME,(Object key, Object value, MapValues values, Object[] params)->{
            Object other=values.get(params[0].toString());
            return value!=null && values.equals(other);
        },":key debe ser igual a :0");
        Singleton.singleton(Validator.class).make(ValidatorRule.DIFERED,(Object key, Object value, MapValues values, Object[] params)->{
            Object other=values.get(params[0].toString());
            return value==null || !value.equals(other);
        },":key debe ser diferente a :0");
        Singleton.singleton(Validator.class).make(ValidatorRule.CONFIRMED,(Object key, Object value, MapValues values, Object[] params)->{
            Object confirmation=values.get(key.toString()+"_confirmation");
            return value!=null && value.equals(confirmation);
        },":key la confirmación no es correcta");
        Singleton.singleton(Validator.class).make(ValidatorRule.LENGTH,(Object key, Object value, MapValues values, Object[] params)->{
            int length=value.toString().length();
            int expected=Integer.parseInt(params[0].toString());
            return length==expected;
        },":key debe tener una longitud de :0 caracteres");
        Singleton.singleton(Validator.class).make(ValidatorRule.MIN_LENGTH,(Object key, Object value, MapValues values, Object[] params)->{
            return value.toString().length()>=Integer.parseInt(params[0].toString());
        },":key debe tener al menos :0 caracteres");
        Singleton.singleton(Validator.class).make(ValidatorRule.MAX_LENGTH,(Object key, Object value, MapValues values, Object[] params)->{
            return value.toString().length()<=Integer.parseInt(params[0].toString());
        },":key debe tener máximo :0 caracteres");
        Singleton.singleton(Validator.class).make(ValidatorRule.IN,(Object key, Object value, MapValues values, Object[] params)->{
            for(Object param:params){
                if(value.equals(param)) return true;
            }
            return false;
        },":key no es valor válido");
        Singleton.singleton(Validator.class).make(ValidatorRule.NOT_IN,(Object key, Object value, MapValues values, Object[] params)->{
            for(Object param:params){
                if(value.equals(param)) return false;
            }
            return true;
        },":key tien un valor prohido");
        Singleton.singleton(Validator.class).make(ValidatorRule.UNIQUE,(Object key, Object value, MapValues values, Object[] params)->{
            Collection<?> collection=(Collection<?>) params[0];
            return !collection.contains(value);
        },":key ya existe");
        Singleton.singleton(Validator.class).make(ValidatorRule.DATE_TIME_FORMAT,(Object key, Object value, MapValues values, Object[] params)->{
            try{
                DateTimeFormatter fmt=DateTimeFormatter.ofPattern(params[0].toString());
                LocalDateTime.parse(value.toString(),fmt);
                return true;
            }catch(Exception ex){
                return false;
            }
        },":key no tiene el formato :0");
        Singleton.singleton(Validator.class).make(ValidatorRule.CALLBACK,(Object key, Object value, MapValues values, Object[] params)->{
            BiFunction<Object,Object,Boolean> fn=(BiFunction<Object,Object,Boolean>) params[0];
            return fn.apply(key,value);
        },":key no pasa la válidación personalizada");
        this.builder();
    }
    
    public void builder(){
        
    }
    
}
