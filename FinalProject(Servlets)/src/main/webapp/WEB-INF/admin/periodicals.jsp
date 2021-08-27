<!DOCTYPE html>
<html lang="en">

<%@include file="../fragments/header.jsp"%>

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>
        <%=rb.getString("title")%>
    </title>
</head>
<body>
<div class="container" style="margin-top: 30px">
    <div class="row justify-content-center">
        <div class="col-md-2">
            <a class="btn btn-success"
               href="${pageContext.request.contextPath}/app/admin/periodicals/addPage">
                <%=rb.getString("action.addPeriodical")%>
            </a>
        </div>
        <div class="col-md-8">
            <%@include file ="../fragments/error-diagnostics.jsp"%>
            <%@include file="../fragments/periodicals-list-admin.jsp"%>
            <%@include file="../fragments/basic-page-nav.jsp"%>
        </div>
    </div>
</div>

</body>
</html>