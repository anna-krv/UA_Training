package ua.finalproject.periodicals.old.controller.filter;

import ua.finalproject.periodicals.old.config.Configurations;
import ua.finalproject.periodicals.old.config.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LocaleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String localeData = request.getParameter("localeData");
        HttpSession session = ((HttpServletRequest)request).getSession();
        if (session!=null){
            if (localeData!=null){
                session.setAttribute("localeData", localeData);
            } else if (session.getAttribute("localeData")==null){
                session.setAttribute("localeData", Configurations.getProperty(Constants.LOCALE));
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
