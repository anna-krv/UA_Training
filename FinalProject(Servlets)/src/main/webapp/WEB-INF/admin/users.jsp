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
    <div class="row justify-content-center">
        <div class="col-md-8">
            <%@include file ="../fragments/users-list.jsp" %>
            <%@include file ="../fragments/basic-page-nav.jsp" %>
        </div>
    </div>
</div>

</body>
</html>