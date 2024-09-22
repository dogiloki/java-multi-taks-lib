package com.dogiloki.multitaks.server.web;

import com.dogiloki.multitaks.GlobalVar;
import com.dogiloki.multitaks.directory.ConfigFile;
import com.dogiloki.multitaks.directory.DirectoryList;
import com.dogiloki.multitaks.directory.Storage;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import com.dogiloki.multitaks.server.web.httpd.HttpConfig;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Path;

/**
 *
 * @author _dogi
 */

public class ServerWeb{
    
    public HttpServer server;
    public HttpConfig config;
    
    public ServerWeb(){
        this.config=ConfigFile.load(HttpConfig.class,this.path("httpd.conf"));
    }
    
    public String path(String path){
        return GlobalVar.group("server").get("name")+"/"+path;
    }
    
    public void listen() throws IOException{
        this.server=HttpServer.create(new InetSocketAddress(this.config.listen_port),0);
        this.server.createContext("/",new HttpHandler(){
            @Override
            public void handle(HttpExchange exchange) throws IOException{
                String uri=Storage.getDir()+"/"+path(config.server_root)+exchange.getRequestURI().getPath();
                Storage directory=Storage.instance(uri);
                String response;
                if(directory.isFolder()){
                    response="Error 404";
                }else{
                    response=directory.read();
                }
                //exchange.getResponseHeaders().add("Content-Type","text/html");
                exchange.sendResponseHeaders(200,response.getBytes().length);
                OutputStream os=exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });
        this.server.setExecutor(null);
        this.server.start();
    }
    
}
