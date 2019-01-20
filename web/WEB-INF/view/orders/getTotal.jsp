<%--
  Created by IntelliJ IDEA.
  User: andrewyuraga
  Date: 2018-12-02
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ftm:setLocale value="${sessionScope.locale}"/>
<ftm:setBundle basename="messages" var="i18n"/>
<center><h5><ftm:message bundle="${i18n}" key="ord.title"/></h5></center>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table>
    <tr>
        <th>№</th>
        <th><ftm:message bundle="${i18n}" key="ord.userid"/></th>
        <th><ftm:message bundle="${i18n}" key="ord.totalsum"/></th>
    </tr>
    <c:forEach var="Double" items="${order}" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${Double.userId}</td>
            <td>${Double.total}</td>
        </tr>
    </c:forEach>
</table>
<br/>
