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
<div class="container" style="margin-top: 30px">
    <c:if test="${user!=null}">
        <div class="row justify-content-center">
            <div class="col-md-4">
                <div class="d-flex w-100 justify-content-between">
                    <h5 class="mb-1">
                            ${user.name} ${user.surname}
                    </h5>
                    <small>
                        <%=rb.getString("login")%>: ${user.username}
                    </small>
                </div>
                <p class="mb-1">
                    <%=rb.getString("email")%>: ${user.email}
                </p>
                <p class="mb-1" text="{user.account}+' ' +{nonLocked}" if="${user.accountNonLocked}">
                    <%=rb.getString("user.account")%> <c:if test="${!user.accountNonLocked}"><%=rb.getString("locked")%></c:if>
                    <c:if test="${user.accountNonLocked}"><%=rb.getString("nonLocked")%></c:if>
                </p>
            </div>
            <div class="col-md-3">
                <form method="post" action="${pageContext.request.contextPath}/app/admin/users/${user.id}/changeBlockStatus">
                    <c:if test="${user.accountNonLocked}">
                        <input class="btn btn-primary"  value="<%=rb.getString("action.block")%>" type="submit"/>
                    </c:if>
                    <c:if test="${!user.accountNonLocked}">
                        <input class="btn btn-secondary"  value="<%=rb.getString("action.unblock")%>" type="submit"/>
                    </c:if>
                </form>
            </div>
        </div>
    </c:if>
    <div class="row justify-content-center">
        <div class="col-md-4" style="margin-top:30px">
            <%@include file ="../fragments/error-diagnostics.jsp"%>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>
</body>
</html>