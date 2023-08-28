package com.dogiloki.multitaks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dogi_
 */
public class Http{
    
    private String url_web;
    private HttpURLConnection connection;
    private Map<String,Object> params=new HashMap<>();

    public Http(String url_web){
        this.url_web=url_web;
    }
    
    public Object get(String uri) throws ProtocolException, MalformedURLException, IOException{
        return this.request("GET",uri);
    }
    
    public Object post(String uri) throws ProtocolException, MalformedURLException, IOException{
        return this.request("POST",uri);
    }
    
    public Object put(String uri) throws ProtocolException, MalformedURLException, IOException{
        return this.request("PUT",uri);
    }
    
    public Object delete(String uri) throws ProtocolException, MalformedURLException, IOException{
        return this.request("DELETE",uri);
    }
    
    public Object request(String method, String uri) throws MalformedURLException, IOException{
        String params="?";
        for(Map.Entry<String,Object> param:this.params.entrySet()){
            params+=param.getKey()+"="+URLEncoder.encode(param.getValue().toString())+"&";
        }
        params=params.substring(0,params.length()-1);
        URL url=new URL(this.url_web+"/"+uri+(params.length()<=0?"":params));
        this.connection=(HttpURLConnection)url.openConnection();
        this.connection.setRequestMethod(method);
        int code=this.connection.getResponseCode();
        BufferedReader in=new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
        String line;
        StringBuilder res=new StringBuilder();
        while((line=in.readLine())!=null){
            res.append(line);
        }
        in.close();
        return res;
    }
    
    public Map<String,Object> getParams(){
        return this.params;
    }
    
}
