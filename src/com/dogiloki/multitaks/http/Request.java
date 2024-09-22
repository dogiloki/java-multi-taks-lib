package com.dogiloki.multitaks.http;

import com.dogiloki.multitaks.directory.ListFields;
import com.dogiloki.multitaks.http.enums.RequestMethod;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author _dogi
 */

public class Request{
    
    public HttpClient client=HttpClient.newHttpClient();
    public HttpRequest req;
    public String url;
    public String uri;
    public RequestMethod method=RequestMethod.GET;
    public RequestHeaders headers=new RequestHeaders();
    public ListFields<String> query=new ListFields();
    public ListFields<String> body=new ListFields();
    public ListFields<String> params=new ListFields();
    
    public Request(){
        
    }
    
    public Request(String url){
        this.url=url;
    }
    
    public Request url(String value){
        this.url=value;
        return this;
    }
    
    public Request uri(String value){
        this.uri=value;
        return this;
    }
    
    public Request get(){
        this.method=RequestMethod.GET;
        return this;
    }
    
    public Request post(){
        this.method=RequestMethod.POST;
        return this;
    }
    
    public Request put(){
        this.method=RequestMethod.PUT;
        return this;
    }
    
    public Request delete(){
        this.method=RequestMethod.DELETE;
        return this;
    }
    
    public CompletableFuture<HttpResponse<String>> send(){
        this.req=HttpRequest.newBuilder()
              .uri(URI.create(this.url+"/"+this.uri+(this.query.isEmpty()?"?":"")))
              .method(this.method.toString(),this.getBodyPublisher())
              .build();
        CompletableFuture<HttpResponse<String>> res=client
                .sendAsync(req,BodyHandlers.ofString());
        return res;
                
    }
    
    public HttpRequest.BodyPublisher getBodyPublisher(){
        switch(this.method){
            case GET:
            case DELETE: return BodyPublishers.noBody();
            case POST:
            case PUT: return BodyPublishers.ofString(this.body.toJson());
            default: return BodyPublishers.noBody();
        }
    }
    
}
