<%@page import="model.table.News" %>
<%@page import="java.util.List" %>
<%
    List<News> news = (List<News>) request.getAttribute("allNews");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>all news</title>
</head>
<body>
    <%@ include file="../../includes/front-office/css.jsp" %>
    <%@ include file="../../includes/front-office/header.jsp" %>

    <section class="contents">
        <% for (News n : news) { %>
            <div class="news">
                <div class="photo">
                    <img src="${pageContext.request.contextPath}/<%= n.getImagesCouverture() %>" alt="<%= n.getAltImagesCouverture() %>">
                </div>
                <div class="description">
                    <h2><%= n.getTitle() %></h2>
                    <div class="action">
                        <a href="<%= n.getHref() %>">
                            <i class="bi bi-eye"></i>
                        </a>
                        <span><%= n.getDatePublication() %></span>
                    </div>
                </div>
            </div>
        <%  } %>
    </section>
</body>
</html>