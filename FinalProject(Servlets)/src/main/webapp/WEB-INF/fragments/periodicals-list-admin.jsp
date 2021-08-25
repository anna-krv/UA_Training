<div class="list-group">
    <c:forEach items="${periodicals}" var="periodical">
        <a  href="${pageContext.request.contextPath}/app/admin/periodicals/${periodical.id}"
           class="list-group-item list-group-item-action flex-column align-items-start">
            <div class="d-flex w-100 justify-content-between">
                <h5 class="mb-1">
                        ${periodical.title}
                </h5>
                <small>${periodical.topic}</small>
            </div>
            <p class="mb-1">
                <%=rb.getString("price")%>: ${periodical.price}
            </p>
        </a>
    </c:forEach>
</div>