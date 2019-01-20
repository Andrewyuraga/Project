<%--
  Created by IntelliJ IDEA.
  User: andrewyuraga
  Date: 11/26/18
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ftm:setLocale value="${sessionScope.locale}"/>
<ftm:setBundle basename="messages" var="i18n"/>

<center><h5><ftm:message bundle="${i18n}" key="ord.getall"/></h5></center>
<center>
    <table>
        <tr>
            <th>№</th>
            <th><ftm:message bundle="${i18n}" key="ord.id"/></th>
            <th><ftm:message bundle="${i18n}" key="ord.partsid"/></th>
            <th><ftm:message bundle="${i18n}" key="ord.quantity"/></th>
            <th><ftm:message bundle="${i18n}" key="ord.totalsum"/></th>
            <th><ftm:message bundle="${i18n}" key="ord.userid"/></th>
            <th><ftm:message bundle="${i18n}" key="ord.delete"/></th>
        </tr>
        <c:forEach var="Double" items="${ords}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${Double.id}</td>
                <td>${Double.partsId}</td>
                <td>${Double.quantity}</td>
                <td>${Double.total}</td>
                <td>${Double.userId}</td>
                <td>
                    <form action="frontController?command=deleteord" method="post">
                        <button value="${Double.id}" name="deleteOrd" class="btn"><ftm:message bundle="${i18n}"
                                                                                               key="ord.run"/></button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br/>
</center>
