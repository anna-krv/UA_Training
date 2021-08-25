<form>
    <div class="form-group">

        <input class="form-control" id="search" name="search"
               placeholder="<%=rb.getString("searchBy.title")%>" type="search"/>
    </div>
    <div class="form-group">
        <input class="btn btn-dark" value="<%=rb.getString("apply")%>" type="submit">
    </div>
    <legend text="<%=rb.getString("sort")%>"></legend>
    <div class="form-check">
        <input autocomplete="off" class="form-check-input" id="sortByName" name="sort"
        ${sort=="title" ? "checked" : "" } type="radio" value="title"/>
        <label class="form-check-label" for="sortByName">
            <%
                out.println(rb.getString("periodical.title"));
            %>
        </label>
    </div>
    <div class="form-check">
        <input autocomplete="off" class="form-check-input" id="sortByPrice" name="sort"
        ${sort=="price" ? "checked" : ""} type="radio" value="price"/>
        <label class="form-check-label" for="sortByPrice">
            <%
                out.println(rb.getString("price"));
            %>
        </label>
    </div>
    <c:if test="${topics!=null && topics.size()!=0}">
        <legend text="<%=rb.getString("topic")%>"></legend>
    </c:if>
    <c:forEach items="${topics}" var="topic">
        <div>
            <input name="topic" id="${topic}" value="${topic}"
                   type="checkbox" ${topicsSelected.contains(topic) ? "checked" : ""}/>
            <label for="${topic}">${topic}</label>
        </div>
    </c:forEach>

</form>