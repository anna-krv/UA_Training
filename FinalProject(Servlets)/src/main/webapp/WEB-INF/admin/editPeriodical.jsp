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

<div class="container" style="margin-top: 60px">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <h1 >
                <%=rb.getString("action.editPeriodical")%>
            </h1>
            <%@include file ="../fragments/error-diagnostics.jsp"%>
            <form method="post" action="${pageContext.request.contextPath}/app/admin/periodicals/${periodical.id}/update">
                <div class="form-group">
                    <label for="title">
                        <%=rb.getString("periodical.title")%>
                    </label>
                    <input type="text" name="title" id="title" class="form-control" value="${periodical.title}"/>
                </div>
                <div class="form-group">
                    <label for="price" ><%=rb.getString("price")%></label>
                    <input type="text" name="price" id="price" class="form-control" value="${periodical.price}"/>

                </div>
                <div class="form-group">
                    <label for="topic">
                        <%=rb.getString("topic")%>
                    </label>
                    <input type="text" name="topic" id="topic" value="${periodical.topic}" class="form-control"/>
                </div>
                <input type="submit" value="<%=rb.getString("action.editPeriodical")%>" class="btn btn-success"/>
            </form>
            <form method="post" style="margin-top: 20px"
                  action="${pageContext.request.contextPath}/app/admin/periodicals/${periodical.id}/delete">
                <input type="submit" class="btn btn-danger" value="<%=rb.getString("action.delete")%>">
            </form>

        </div>
    </div>

</div>
</body>
</html>