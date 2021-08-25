<div style="margin-top:30px">
    <nav aria-label="Page navigation for periodicals">
        <ul class="pagination justify-content-center">
            <li class="${number>0 ? 'page-item' : 'page-item disabled'}">
                <a class="page-link"
                   href="?sort=${sort}&search=${search}&topic=${topicsSelected}&number=${number-1}">
                    <%
                        out.println(props.getProperty("navigation.prev"));
                    %>
                </a>
            </li>

            <li class="page-item">
                <a class="page-link"
                   href="?sort=${sort}&search=${search}&topic=${topicsSelected}&number=${number}">

                ${number+1}

                </a>
            </li>


            <li class="page-item">
                <a class="page-link"
                   href="?(sort=${sort}&search=${search}&topic=${topicsSelected}&number=${number+1}">
                    <%
                        out.println(props.getProperty("navigation.next"));
                    %>
                </a>
            </li>

        </ul>
    </nav>
</div>
