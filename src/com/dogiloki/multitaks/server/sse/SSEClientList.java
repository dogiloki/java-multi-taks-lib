package com.dogiloki.multitaks.server.sse;

import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author _dogi
 */

public class SSEClientList extends ConcurrentHashMap<HttpServletResponse,PrintWriter>{
    
}
