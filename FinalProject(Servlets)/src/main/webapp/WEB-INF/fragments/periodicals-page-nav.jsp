<%@ page import="java.util.stream.Collectors" %>
<div style="margin-top:30px">
    <nav aria-label="Page navigation for periodicals">
        <%
            List<String> topicsSelected = (List<String>) request.getSession().getAttribute("topicsSelected");
            String topicsQueryParam = topicsSelected.stream().collect(Collectors.joining("&topic="));
        %>

        <ul class="pagination justify-content-center">
            <li class="${number>0 ? 'page-item' : 'page-item disabled'}">

                <a class="page-link"
                   href="?sort=${sort}&search=${search}&topic=<%=topicsQueryParam%>&number=${number-1}">
                    <%
                        out.println(rb.getString("navigation.prev"));
                    %>
                </a>
            </li>

            <li class="page-item">
                <a class="page-link"
                   href="?sort=${sort}&search=${search}&topic=<%=topicsQueryParam%>&number=${number}">

                ${number+1}

                </a>
            </li>


            <li class="page-item">
                <a class="page-link"
                   href="?sort=${sort}&search=${search}&topic=<%=topicsQueryParam%>&number=${number+1}">
                    <%
                        out.println(rb.getString("navigation.next"));
                    %>
                </a>
            </li>

        </ul>
    </nav>
</div>
