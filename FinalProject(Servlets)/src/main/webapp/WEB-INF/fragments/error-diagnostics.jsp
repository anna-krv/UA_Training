<div>
    <c:if test="${error!=null}">
        <div class="${error.toString()=='error.none' ? 'alert alert-success':'alert alert-danger'}">
            <%=rb.getString(request.getAttribute("error").toString())%>
        </div>
    </c:if>
</div>
