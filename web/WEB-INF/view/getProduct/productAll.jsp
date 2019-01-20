<%--
  Created by IntelliJ IDEA.
  User: andrewyuraga
  Date: 11/22/18
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ftm:setLocale value="${sessionScope.locale}"/>
<ftm:setBundle basename="messages" var="i18n"/>
<center><h5><ftm:message bundle="${i18n}" key="partsall.title"/></h5></center>
<center>
    <table>
        <tr>
            <th>№</th>
            <th><ftm:message bundle="${i18n}" key="parts.id"/></th>
            <th><ftm:message bundle="${i18n}" key="parts.producer"/></th>
            <th><ftm:message bundle="${i18n}" key="parts.category"/></th>
            <th><ftm:message bundle="${i18n}" key="parts.name"/></th>
            <th><ftm:message bundle="${i18n}" key="parts.cataristics"/></th>
            <th><ftm:message bundle="${i18n}" key="parts.price"/></th>
            <th><ftm:message bundle="${i18n}" key="parts.delete"/></th>
        </tr>
        <c:forEach var="parts" items="${parts}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${parts.id}</td>
                <td>${parts.producer}</td>
                <td>${parts.category}</td>
                <td>${parts.name}</td>
                <td>${parts.chatacteristics}</td>
                <td>${parts.price}</td>
                <td>
                    <form action="frontController?command=deleteproduct" method="post">
                        <button value="${parts.id}" name="delete" class="btn"><ftm:message bundle="${i18n}"
                                                                                           key="parts.delete"/></button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br/>
</center>
