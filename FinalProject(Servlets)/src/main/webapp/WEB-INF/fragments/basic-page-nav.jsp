<div style="margin-top:30px" >
    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">
            <li class="${number>0 ? 'page-item' : 'page-item disabled'}">
                <a class="page-link" href="?number=${number-1}">
                    <%=rb.getString("navigation.prev")%>
                </a>
            </li>
            <li class="page-item">
                <a class="page-link" href="?number=${number}">
                    ${number+1}
                </a>
            </li>
            <li class="page-item">
                <a class="page-link" href="?number=${number+1}">
                    <%=rb.getString("navigation.next")%>
                </a>
            </li>
        </ul>
    </nav>
</div>