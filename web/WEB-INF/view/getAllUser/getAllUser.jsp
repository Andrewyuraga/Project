<%--
  Created by IntelliJ IDEA.
  User: andrewyuraga
  Date: 11/22/18
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<ftm:setLocale value="${sessionScope.locale}"/>
<ftm:setBundle basename="messages" var="i18n"/>
<center><h5><ftm:message bundle="${i18n}" key="users.title"/></h5></center>
<center>
    <table>
        <tr>
            <th>№</th>
            <th><ftm:message bundle="${i18n}" key="users.id"/></th>
            <th><ftm:message bundle="${i18n}" key="users.names"/></th>
            <th><ftm:message bundle="${i18n}" key="users.emails"/></th>
            <th><ftm:message bundle="${i18n}" key="email.password"/></th>
            <th><ftm:message bundle="${i18n}" key="users.status"/></th>
            <th><ftm:message bundle="${i18n}" key="users.right"/></th>
        </tr>
        <c:forEach var="users" items="${users}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${users.id}</td>
                <td>${users.name}</td>
                <td>${users.email}</td>
                <td>${users.password}</td>
                <td>${users.status}</td>
                <td>${users.rights}</td>
            </tr>
        </c:forEach>
    </table>
    <br/>
</center>

