<div>
    <c:if test="${success}">
        <div class="alert alert-success" text="<%=rb.getString("success")%>"></div>
    </c:if>
    <c:if test="${moneyError}">
        <div class="alert alert-danger" text="<%=rb.getString("error.money")%>">
        </div>
    </c:if>
    <c:if test="${formatError}">
        <div class="alert alert-danger" text="<%=rb.getString("error.format")%>">
        </div>
    </c:if>
    <c:if test="${accountError}">
        <div class="alert alert-danger" text="<%=rb.getString("error.account")%>">
        </div>
    </c:if>
    <c:if test="${subscriptionError}">
        <div class="alert alert-danger" text="<%=rb.getString("error.subscription")%>">
        </div>
    </c:if>
    <c:if test="${searchError}">
        <div class="alert alert-danger" text="<%=rb.getString("error.search")%>">
        </div>
    </c:if>
    <c:if test="${loginError}">
        <div class="alert alert-danger" text="<%=rb.getString("error.login")%>">
        </div>
    </c:if>
    <c:if test="${errorNotUnique}">
        <div class="alert alert-danger" text="<%=rb.getString("error.notUnique")%>">
        </div>
    </c:if>
    <c:if test="${resourceError}">
        <div class="alert alert-danger" text="<%=rb.getString("error.resource")%>">
        </div>
    </c:if>
    <c:if test="${notUniqueUserError}">
        <div class="alert alert-danger" text="<%=rb.getString("error.user.notUnique")%>">
        </div>
    </c:if>
    <c:if test="${error}">
        <div class="alert alert-danger" text="<%=rb.getString("error")%>">
        </div>
    </c:if>
</div>
