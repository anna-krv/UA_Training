package ua.finalproject.periodicals.old.controller.filter;

import ua.finalproject.periodicals.old.controller.RequestUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class AuthenticationFilter implements Filter {
    private final Logger logger = Logger.getLogger(AuthenticationFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ((HttpServletResponse)servletResponse).setHeader("Cache-Control", "no-store");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String uri = httpServletRequest.getRequestURI();
        boolean isLoggedIn = httpServletRequest.getSession().getAttribute("role") != null;
        boolean isPublicPath = RequestUtil.isPublic(uri);

        if (isPublicPath && isLoggedIn){
            logger.info("public and logged");
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/WEB-INF/index.jsp");
            dispatcher.forward(servletRequest, servletResponse);
            return;
        }
        else if (isLoggedIn || isPublicPath) {
            logger.info("is logged or is public path");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else {
            logger.info("not public and no credentials, redirect to login page");
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/login.jsp");
            dispatcher.forward(servletRequest, servletResponse);
        }
    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
