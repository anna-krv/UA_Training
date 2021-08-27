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
<div class="container">
    <div class="row justify-content-center" style="margin-top: 30px">
        <div class="col-md-10">
            <h1>
                <c:if test = "${personalPage}">
                    <%
                        out.println(rb.getString("subscriptions.my"));
                    %>
                </c:if>

                <c:if test = "${!personalPage}">
                    <%
                        out.println(rb.getString("subscriptions.available"));
                    %>
                </c:if>
            </h1>
        </div>
    </div>
    <div class="row justify-content-center" style="margin-top: 30px">
        <div class="col-md-3">
           <%@include file ="../fragments/search-filter-form.jsp" %>
        </div>

        <div class="col-md-8">
            <%@include file ="../fragments/error-diagnostics.jsp"%>
            <%@include file ="../fragments/periodicals-list.jsp"%>
            <%@include file ="../fragments/periodicals-page-nav.jsp"%>
        </div>

    </div>
</div>
</body>
</html>