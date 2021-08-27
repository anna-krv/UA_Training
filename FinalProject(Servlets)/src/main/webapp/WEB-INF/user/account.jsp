<!DOCTYPE html>
<html lang="en">

<%@include file="../fragments/header.jsp"%>

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>
        <%
            out.println(rb.getString("title"));
        %>
    </title>
</head>
<body>

<div class="container" style="margin-top: 60px">

    <h1 >
        <%=rb.getString("account")%>
    </h1>
    <div class="row">
        <div class="col-md-8 col-md-offset-2">

            <form style="margin-bottom: 30px" method="post" action="${pageContext.request.contextPath}/app/account">
                <h3>
                    <%
                        out.println(rb.getString("account.balance")+": ");
                    %>
                    ${balance}
                </h3>
                <div class="form-group">
                    <label for="moneyToPut">
                        <%
                            out.println(rb.getString("account.putMoney"));
                        %>
                    </label>
                    <input type="text" name="moneyToPut" id="moneyToPut" class="form-control"/>
                </div>
                <input type="submit" value="<%=rb.getString("account.putMoney")%>"
                       class="btn btn-success"/>
            </form>
            <%@include file ="../fragments/error-diagnostics.jsp"%>
        </div>
    </div>
</div>

</body>
</html>