<div class="list-group">
    <c:forEach items="${periodicals}" var="periodical">
        <a  class="list-group-item list-group-item-action flex-column align-items-start"
            href="${pageContext.request.contextPath}/app/periodicals/${periodical.id}">
            <div class="d-flex w-100 justify-content-between">
                <h5 class="mb-1">
                        ${periodical.title}
                </h5>
                <small >
                        ${periodical.topic}
                </small>
            </div>
            <p class="mb-1">
                <%
                    out.println(props.getProperty("price")+": ");
                %>
                    ${periodical.price}
            </p>
        </a>
    </c:forEach>
</div>