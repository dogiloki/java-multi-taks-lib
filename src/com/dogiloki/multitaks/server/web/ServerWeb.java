package com.dogiloki.multitaks.server.web;

import com.dogiloki.multitaks.directory.Storage;
import com.dogiloki.multitaks.server.web.httpd.HttpConfig;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 *
 * @author _dogi
 */

public class ServerWeb{
    
    private HttpServer server;
    private HttpConfig config;
    
    public ServerWeb(){
        this.config=new HttpConfig().builder();
        System.out.println(this.config);
    }
    
    public void listen() throws IOException{
        this.server=HttpServer.create(new InetSocketAddress(this.config.listen_port),0);
        this.server.createContext("/",new RootHandler());
        this.server.setExecutor(null);
        this.server.start();
    }
    
    static class RootHandler implements HttpHandler{
        @Override
        public void handle(HttpExchange exchange) throws IOException{
            String response=Storage.instance("E:\\Github\\pipati-js\\index.html").read();
            exchange.getResponseHeaders().add("Content-Type","text/html");
            exchange.sendResponseHeaders(200,response.getBytes().length);
            OutputStream os=exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    
}
