<!DOCTYPE html>
<html lang="en">

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
            out.println(props.getProperty("title"));
        %>
    </title>
</head>
<body>

<%@include file="header.jsp"%>

</body>
</html>