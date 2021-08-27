<!DOCTYPE html>
<html lang="en">

<%@include file="/WEB-INF/fragments/header.jsp"%>

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>
        <%=rb.getString("title")%>
    </title>
</head>
<body>
<c:if test="${role!=null}">
    <h3>
        <%=rb.getString("message.welcome.logged")%>
    </h3>
</c:if>
<c:if test="${role==null}">
    <h3>
        <%=rb.getString("message.welcome.notLogged")%>
    </h3>
</c:if>

</body>
</html>