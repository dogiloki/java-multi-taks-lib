package com.dogiloki.multitaks.server.sse;

import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author _dogi
 */

public class SSESever extends HttpServlet{
    
    private final SSEClientList clients=new SSEClientList();
    private final Object lock=new Object();
    private String encoding="UTF-8";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res){
        try{
            res.setContentType("text/event-stream");
            res.setCharacterEncoding(this.encoding);

            PrintWriter writer=res.getWriter();
            
            synchronized (this.lock){
                this.clients.put(res,writer);
            }
        }catch(){
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
        
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res){
        
    }
    
    public void send(String event, String data){
        try{
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
