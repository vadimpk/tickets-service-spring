package com.naukma.ticketsservice.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

public class RequestLoggingFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fconfig) {
        this.context = fconfig.getServletContext();
        this.context.log("RequestLoggingFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        context.log("filter : " + ((HttpServletRequest)request).getRequestURL());
        chain.doFilter(request, response);
    }
}
