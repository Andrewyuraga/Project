<%--
  Created by IntelliJ IDEA.
  User: andrewyuraga
  Date: 11/18/18
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ftm:setLocale value="${sessionScope.locale}"/>
<ftm:setBundle basename="messages" var="i18n"/>

<center><h5><ftm:message bundle="${i18n}" key="ord.title"/></h5></center>
<center>
    <table>
        <tr>
            <th>№</th>
            <th><ftm:message bundle="${i18n}" key="ord.id"/></th>
            <th><ftm:message bundle="${i18n}" key="ord.partsid"/></th>
            <th><ftm:message bundle="${i18n}" key="ord.quantity"/></th>
            <th><ftm:message bundle="${i18n}" key="ord.totalsum"/></th>
            <th><ftm:message bundle="${i18n}" key="ord.delete"/></th>
        </tr>
        <c:forEach var="Double" items="${orders}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${Double.id}</td>
                <td>${Double.partsId}</td>
                <td>${Double.quantity}</td>
                <td>${Double.total}</td>
                <td>
                    <form action="frontController?command=deleteord" method="post">
                        <button value="${Double.id}" name="deleteOrd" class="btn"><ftm:message bundle="${i18n}"
                                                                                               key="parts.delete"/></button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <button type="submit" class="btn"><a
            href="${pageContext.request.contextPath}/frontController?command=getTotal"><ftm:message
            bundle="${i18n}" key="ord.title"/></a></button>
    <br/>
</center>