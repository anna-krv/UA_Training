<html>

<%@include file="/WEB-INF/fragments/header.jsp"%>

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>
        <%=rb.getString("register") %>
    </title>
</head>
<body>


<div class="container" style="margin-top: 60px">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <h1>
                <%=rb.getString("register")%>
            </h1>
            <form style="margin-bottom:30px" method="get"
                  action="${pageContext.request.contextPath}/app/register">
                <div class="form-group">
                    <label for="name">
                        <%=rb.getString("name")%>
                    </label>
                    <input type="text" name="name" id="name" class="form-control" value="${user.name}"/>

                </div>
                <div class="form-group">
                    <label for="surname">
                        <%=rb.getString("surname")%>
                    </label>
                    <input type="text" name="surname" id="surname" class="form-control" value="${user.surname}"/>
                </div>
                <div class="form-group">
                    <label for="email">
                        <%=rb.getString("email")%>
                    </label>
                    <input type="text" name="email" id="email" class="form-control" value="${user.email}"/>
                </div>
                <div class="form-group">
                    <label for="username">
                        <%=rb.getString("login")%>
                    </label>
                    <input id="username"  name="username" type="text" class="form-control" value="${user.username}"/>
                </div>
                <div class="form-group">
                    <label for="password">
                        <%=rb.getString("password")%>
                    </label>
                    <input type="password"  name="password" id="password" class="form-control" value="${user.password}"/>
                </div>
                <input type="submit" value="<%=rb.getString("register")%>"
                       class="btn btn-success"/>
            </form>
            <%@include file="WEB-INF/fragments/error-diagnostics.jsp"%>
        </div>
    </div>

</div>
</body>
</html>