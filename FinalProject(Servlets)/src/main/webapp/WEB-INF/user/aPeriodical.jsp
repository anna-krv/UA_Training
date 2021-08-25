<%@ page import="ua.finalproject.periodicals.old.entity.Subscription" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="../fragments/header.jsp" %>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>
        <%
            out.println(rb.getString("title"));
        %>
    </title>
</head>

<body>
<div class="container" style="margin-top: 30px">
    <div class="row justify-content-center">
        <c:if test="${periodical!=null}">
            <div class="col-md-6">
                <div class="d-flex w-100 justify-content-between">
                    <h5 class="mb-1">
                            ${periodical.title}
                    </h5>
                    <small>
                        <%
                            out.println(rb.getString("topic") + ": ");
                        %>
                            ${periodical.topic}
                    </small>
                </div>
                <p class="mb-1">
                    <%
                        out.println(rb.getString("price") + ": ");
                    %>
                        ${periodical.price}
                </p>
                <% Subscription subscription = (Subscription) request.getSession().getAttribute("subscription");
                %>
                <c:if test="${subscription!=null}">
                    <p class="mb-1">
                        <%
                            out.println(rb.getString("payment.last") + ": "+
                                    subscription.getLastPaymentDateTime().format(DateTimeFormatter.ofPattern(
                                            rb.getString("format.data")
                                    ))
                            );
                        %>
                    </p>
                    <p class="mb-1">
                        <%
                            out.println(rb.getString("payment.next") + ": " +
                                    subscription.getNextPaymentDateTime().format(DateTimeFormatter.ofPattern(
                                            rb.getString("format.data")
                                    ))
                            );
                        %>
                    </p>
                </c:if>

            </div>

            <div class="col-md-3">
                <c:if test="${subscription==null}">
                    <a class="btn btn-primary"
                       href="${pageContext.request.contextPath}/app/periodicals/${periodical.id}/subscribe">
                        <%
                            out.println(rb.getString("action.subscribe"));
                        %>
                    </a>
                </c:if>
                <c:if test="${subscription!=null}">
                    <a class="btn btn-secondary"
                       href="${pageContext.request.contextPath}/app/periodicals/${periodical.id}/unsubscribe">
                        <%
                            out.println(rb.getString("action.unsubscribe"));
                        %>
                    </a>
                </c:if>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>