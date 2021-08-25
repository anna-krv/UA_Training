<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<%@ page language="java" import="java.util.*" %>
<%@ page import="java.util.ResourceBundle" %>
<% Properties props = new Properties();
    props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("messages.properties"));
%>


<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>
        <%
            out.println(props.getProperty("login.title"));
        %>
    </title>
</head>



<body>

<%@include file="header.jsp"%>

<div class="container" style="margin-top: 60px">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <h1>
                <%
                    out.println(props.getProperty("login.title"));
                %>
            </h1>
            <form style="margin-bottom: 30px" method="post"
                  action="${pageContext.request.contextPath}/app/login">
                <div class="form-group">
                    <label for="username">
                        <%
                            out.println(props.getProperty("login"));
                        %>
                    </label>
                    <input type="text" id="username" name="username"
                           class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="password">
                       <%
                           out.println(props.getProperty("password"));
                       %>
                    </label>
                    <input type="password" id="password" name="password"
                           class="form-control"/>
                </div>
                <input type="submit"
                       class="btn btn-success" value="<%=props.getProperty("login.title")%>"/>
            </form>
        </div>

    </div>
</div>
</body>
</html>