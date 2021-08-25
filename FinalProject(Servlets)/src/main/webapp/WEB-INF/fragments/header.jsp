<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" import="java.util.*" %>
<%@ page import="java.util.ResourceBundle" %>
<% ResourceBundle rb = ResourceBundle.getBundle("messages",
        new Locale(
                (String) request.getSession().getAttribute("localeData")));
%>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">
        <%
            out.println(rb.getString("title"));
        %>
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
            aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="navbar-collapse collapse" id="navbarNavAltMarkup">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/app/periodicals/subscribed">
                    <%
                        out.println(rb.getString("menu.personalPage"));
                    %>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/app/periodicals">
                    <%
                        out.println(rb.getString("action.subscribe"));
                    %>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/app/account">
                    <%=rb.getString("account")%>
                </a>
            </li>
            <c:if test="${role=='ADMIN'}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/app/admin/users">
                        <%=rb.getString("menu.users.manage")%>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/app/admin/periodicals">
                        <%=rb.getString("menu.periodicals.manage")%>
                    </a>
                </li>
            </c:if>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/app/logout">
                    <%=rb.getString("action.logout")%>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="?localeData=ua">UA</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="?localeData=en">EN</a>
            </li>
        </ul>

    </div>
</nav>