<!DOCTYPE html>
<html lang="en">

<%@include file="../fragments/header.jsp"%>

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>
        <%
            out.println(props.getProperty("title"));
        %>
    </title>
</head>
<body>

<div class="container" style="margin-top: 60px">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <h1>
                <%=props.getProperty("action.addPeriodical")%>
            </h1>
            <form method="post" action="${pageContext.request.contextPath}/app/admin/periodicals/add">
                <div class="form-group">
                    <label for="title" >
                        <%=props.getProperty("periodical.title")%>
                    </label>
                    <input type="text" name="title" id="title" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="price" >
                        <%=props.getProperty("price")%>
                    </label>
                    <input type="text" name="price" id="price" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="topic" >
                        <%=props.getProperty("topic")%>
                    </label>
                    <input type="text" name="topic" id="topic" class="form-control"/>
                </div>
                <input type="submit" value="<%=props.getProperty("action.addPeriodical")%>" class="btn btn-success"/>
            </form>
        </div>
    </div>
</div>
</body>
</html>