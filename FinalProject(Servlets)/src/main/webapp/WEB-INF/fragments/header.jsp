<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">
        <%
            out.println(props.getProperty("title"));
        %>
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
            aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="navbar-collapse collapse" id="navbarNavAltMarkup">
        <ul class="navbar-nav mr-auto">
            <li  class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/app/periodicals/subscribed">
                    <%
                        out.println(props.getProperty("menu.personalPage"));
                    %>
                </a>
            </li>
            <li  class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/app/periodicals">
                    <%
                        out.println(props.getProperty("action.subscribe"));
                    %>
                </a>
            </li>
            <li  class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/app/account" >
                    <%
                        out.println(props.getProperty("account"));
                    %>
                </a>
            </li>
            <!--
            <li sec:authorize="hasAuthority('ADMIN')" class="nav-item">
                <a class="nav-link" href="/admin/users" th:text="#{menu.users.manage}"></a>
            </li>
            <li sec:authorize="hasAuthority('ADMIN')" class="nav-item">
                <a class="nav-link" href="/admin/periodicals" th:text="#{menu.periodicals.manage}"></a>
            </li>
            -->

        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/app/logout" >
                    <%
                        out.println(props.getProperty("action.logout"));
                    %>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="?localeData=ua">UA</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="?localeData=en">EN</a>
            </li>
        </ul>
    </div>
</nav>