<%--
  Created by IntelliJ IDEA.
  User: andrewyuraga
  Date: 11/18/18
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav>
    <ul class="menu">
        <li><a href="Index.jsp">Главная</a></li>
        <li><a href="oil.html">Масла</a></li>
        <li><a href="${pageContext.request.contextPath}/frontController?command=Email">Вход</a></li>
        <li><a href="parts.html">Автозапчасти</a></li>
        <li><a href="service.html">Сервис</a></li>
        <li><a href="#!">Оплата</a></li>
    </ul>
</nav>
