package com.jlhc.web.filter;

import com.jlhc.ApplicationJlhc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 过滤器添加的是访问跨域的头,启用
 *
 * @author renzhong
 * @version 1.0 , 2018-1-18 14:55
 */
public class ActionAuthenFilter implements Filter{

    //private static Log log = LogFactory.getLog(ActionAuthenFilter.class);
    private final static Logger logger = LoggerFactory.getLogger(ApplicationJlhc.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException{
        /*if (logger.isDebugEnabled()) {
            logger.debug("do ActionAuthenFilter befor.");
        }*/
        HttpServletRequest request = (HttpServletRequest) arg0;
    	HttpServletResponse httpResponse = (HttpServletResponse) arg1;
    	
        httpResponse.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        httpResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        httpResponse.setHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Credentials");
        httpResponse.setHeader("Access-Control-Allow-Credentials","true");
        httpResponse.setHeader("Access-Control-Max-Age", "3600");
        httpResponse.addHeader("Access-Control-Allow-Headers", "Authorization, Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With");

        httpResponse.setHeader("Pragma","No-cache"); 
    	httpResponse.setHeader("Cache-Control", "no-cache");  
    	httpResponse.setDateHeader("Expires", 0); 
    	
    	
        HttpServletRequest httpReq = (HttpServletRequest)arg0;
        if(httpReq.getMethod().equals(RequestMethod.OPTIONS.name())){
        	httpResponse.setStatus(HttpStatus.OK.value());
            httpResponse.flushBuffer();
            return;
        }
        arg2.doFilter(arg0, httpResponse);
    }

    @Override
    public void destroy() {

    }
}
