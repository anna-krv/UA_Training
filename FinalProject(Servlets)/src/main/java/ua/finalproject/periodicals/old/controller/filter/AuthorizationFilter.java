package ua.finalproject.periodicals.old.controller.filter;

import ua.finalproject.periodicals.old.controller.RequestUtil;
import ua.finalproject.periodicals.old.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String uri = httpServletRequest.getRequestURI();
        boolean isAdminPath = RequestUtil.isAdminPath(uri);
        boolean isAdminRole = httpServletRequest.getSession().getAttribute("role") == Role.ADMIN;

        if (isAdminPath && !isAdminRole){
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
        else{
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
