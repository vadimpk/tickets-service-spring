package com.naukma.ticketsservice.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RequestLoggingFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fconfig) throws ServletException {
        this.context = fconfig.getServletContext();
        this.context.log("RequestLoggingFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
//        Enumeration<String> params = req.getParameterNames();
//        while(params.hasMoreElements()){
//            String name = params.nextElement();
//            String value = request.getParameter(name);
//            this.context.log(req.getRemoteAddr() + "::Request Params::{"+name+"="+value+"}");
//        }
//        // pass the request along the filter chain
        chain.doFilter(request, response);
    }
}
