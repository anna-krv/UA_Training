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
            out.println(props.getProperty("register"));
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
                    out.println(props.getProperty("register"));
                %>
            </h1>
            <form style="margin-bottom:30px" method="get"
                  action="${pageContext.request.contextPath}/app/register">
                <div class="form-group">
                    <label for="name">
                        <%
                            out.println(props.getProperty("name"));
                        %>
                    </label>
                    <input type="text" id="name" class="form-control"/>

                </div>
                <div class="form-group">
                    <label for="surname">
                        <%
                            out.println(props.getProperty("surname"));
                        %>
                    </label>
                    <input type="text" id="surname" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="email">
                        <%
                            out.println(props.getProperty("email"));
                        %>
                    </label>
                    <input type="text" id="email" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="username">
                        <%
                            out.println(props.getProperty("login"));
                        %>
                    </label>
                    <input id="username"  type="text" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="password">
                        <%
                            out.println(props.getProperty("password"));
                        %>
                    </label>
                    <input type="password"  id="password" class="form-control"/>
                </div>
                <input type="submit" value="<%=props.getProperty("register")%>"
                       class="btn btn-success"/>
            </form>

        </div>
    </div>

</div>
</body>
</html>