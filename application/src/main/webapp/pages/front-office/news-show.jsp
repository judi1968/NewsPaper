<%@page import="model.table.News" %>
<%
    News news = (News) request.getAttribute("news");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description">
    <title><%= news.getTitle() %></title>

    <%@ include file="../../includes/front-office/cssShow.jsp" %>
</head>
<body>

    <%@ include file="../../includes/front-office/header.jsp" %>

    <section class="contents">
        <div class="n">
            <img width="150" style="display: block; margin-left: 40px;" src="${pageContext.request.contextPath}/<%= news.getImagesCouverture() %>" alt="<%= news.getAltImagesCouverture() %>">

            <div id="mce-content-body">
                    <%= news.getContenu() %>
            </div>
            <div class="info">
                <span class="date"><%= news.getDatePublication() %></span>
            </div>
        </div>
    </section>
</body>
</html>