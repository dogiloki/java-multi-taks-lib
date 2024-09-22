package com.dogiloki.multitaks.http;

import com.dogiloki.multitaks.dataformat.JSON;
import com.dogiloki.multitaks.directory.ListFields;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author _dogi
 */

public class RequestHeaders{
    
    @Expose
    @SerializedName("Authorization")
    public String authorization;
    
    @Expose
    @SerializedName("Content-Type")
    public String content_type;
    
    @Expose
    @SerializedName("Accept")
    public String accept;
    
    @Expose
    @SerializedName("User-Agent")
    public String user_agent;
    
    @Expose
    @SerializedName("Host")
    public String host;
    
    @Expose
    @SerializedName("Referer")
    public String referer;
    
    @Expose
    @SerializedName("Cache-Control")
    public String cache_control;
    
    @Expose
    @SerializedName("Accept-Languaje")
    public String accept_languaje;
    
    @Expose
    @SerializedName("Accept-Encoding")
    public String accept_encoding;
    
    @Expose
    @SerializedName("Connection")
    public String connection;
    
    public ListFields<String> other=new ListFields();
    
    public String toJson(){
        return JSON.builderNotNulls().toJson(this);
    }
    
}
