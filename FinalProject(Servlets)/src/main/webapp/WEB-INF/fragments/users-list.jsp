<div class="list-group" >
    <c:forEach items="${users}" var="user">
        <a href="${pageContext.request.contextPath}/app/admin/users/${user.id}"
           class="list-group-item list-group-item-action flex-column align-items-start">
            <div class="d-flex w-100 justify-content-between">
                <h5 class="mb-1">
                        ${user.name} ${user.surname}
                </h5>
                <small>
                    <%=rb.getString("login")%>: ${user.username}
                </small>
            </div>
            <p class="mb-1">
                <c:if test="${user.accountNonLocked}">
                    <%=rb.getString("user.account")%> <%=rb.getString("nonLocked")%>
                </c:if>
                <c:if test="${!user.accountNonLocked}">
                    <%=rb.getString("user.account")%> <%=rb.getString("locked")%>
                </c:if>
            </p>
        </a>
    </c:forEach>
</div>