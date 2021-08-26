<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<%@include file="header.jsp"%>


<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>
        <%
            out.println(rb.getString("login.title"));
        %>
    </title>
</head>



<body>


<div class="container" style="margin-top: 60px">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <h1>
                <%
                    out.println(rb.getString("login.title"));
                %>
            </h1>
            <form style="margin-bottom: 30px" method="post"
                  action="${pageContext.request.contextPath}/app/login">
                <div class="form-group">
                    <label for="username">
                        <%
                            out.println(rb.getString("login"));
                        %>
                    </label>
                    <input type="text" id="username" name="username"
                           class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="password">
                       <%
                           out.println(rb.getString("password"));
                       %>
                    </label>
                    <input type="password" id="password" name="password"
                           class="form-control"/>
                </div>
                <input type="submit"
                       class="btn btn-success" value="<%=rb.getString("login.title")%>"/>
            </form>
        </div>

    </div>
</div>
</body>
</html>