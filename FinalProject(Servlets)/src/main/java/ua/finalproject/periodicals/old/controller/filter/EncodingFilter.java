package ua.finalproject.periodicals.old.controller.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.logging.Logger;

public class EncodingFilter implements Filter {
    private static final Logger logger = Logger.getLogger(EncodingFilter.class.getName());
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("in encoding filter");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
