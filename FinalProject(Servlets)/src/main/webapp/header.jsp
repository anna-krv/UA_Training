<%@ page language="java" import="java.util.*" %>
<%@ page import="java.util.ResourceBundle" %>
<% ResourceBundle rb = ResourceBundle.getBundle("messages",
        Locale.ENGLISH);
%>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/app/">
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
                <a class="nav-link" href="${pageContext.request.contextPath}/app/loginPage" >
                    <%
                        out.println(rb.getString("login.title"));
                    %>
                </a>
            </li>
            <li  class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/app/registerPage" >
                    <%
                        out.println(rb.getString("register"));
                    %>
                </a>
            </li>


        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="?localeData=ua">UA</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="?localeData=en">EN</a>
            </li>
        </ul>
    </div>
</nav>