package com.naukma.ticketsservice.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RequestLoggingFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fconfig) {
        this.context = fconfig.getServletContext();
        if (context != null )this.context.log("RequestLoggingFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (context != null )context.log("filter : " + ((HttpServletRequest)request).getRequestURL());
        chain.doFilter(request, response);
    }
}
