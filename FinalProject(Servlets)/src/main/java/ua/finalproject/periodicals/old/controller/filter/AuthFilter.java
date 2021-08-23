package ua.finalproject.periodicals.old.controller.filter;

import ua.finalproject.periodicals.old.controller.PathUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Logger;

public class AuthFilter implements Filter {
    private final Logger logger = Logger.getLogger(AuthFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        logger.info("init filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("filtering");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String uri = httpServletRequest.getRequestURI();
        boolean isLoggedIn = httpServletRequest.getSession().getAttribute("role")!=null;
        boolean isLoginRequest = PathUtil.isLoginRequest(uri);
        boolean isPublicPath = PathUtil.isPublic(uri);
        logger.info(isPublicPath+"is public");
        if (isLoggedIn || isPublicPath) {
            logger.info("public or good credentials");
            filterChain.doFilter(servletRequest, servletResponse);
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
