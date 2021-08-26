<html>

<%@include file="header.jsp"%>

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>
        <%
            out.println(rb.getString("register"));
        %>
    </title>
</head>
<body>


<div class="container" style="margin-top: 60px">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <h1>
                <%
                    out.println(rb.getString("register"));
                %>
            </h1>
            <form style="margin-bottom:30px" method="get"
                  action="${pageContext.request.contextPath}/app/register">
                <div class="form-group">
                    <label for="name">
                        <%
                            out.println(rb.getString("name"));
                        %>
                    </label>
                    <input type="text" id="name" class="form-control"/>

                </div>
                <div class="form-group">
                    <label for="surname">
                        <%
                            out.println(rb.getString("surname"));
                        %>
                    </label>
                    <input type="text" id="surname" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="email">
                        <%
                            out.println(rb.getString("email"));
                        %>
                    </label>
                    <input type="text" id="email" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="username">
                        <%
                            out.println(rb.getString("login"));
                        %>
                    </label>
                    <input id="username"  type="text" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="password">
                        <%
                            out.println(rb.getString("password"));
                        %>
                    </label>
                    <input type="password"  id="password" class="form-control"/>
                </div>
                <input type="submit" value="<%=rb.getString("register")%>"
                       class="btn btn-success"/>
            </form>

        </div>
    </div>

</div>
</body>
</html>