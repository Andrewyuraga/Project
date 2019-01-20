<%--
  Created by IntelliJ IDEA.
  User: andrewyuraga
  Date: 11/16/18
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${not empty message}">INFO : ${message}</c:if> <br/>
<ftm:setLocale value="${sessionScope.locale}"/>
<ftm:setBundle basename="messages" var="i18n"/>

<center><h5><ftm:message bundle="${i18n}" key="parts.title"/></h5></center>

<div style="font-size: large">
    <c:if test="${not empty message}">INFO : ${message}</c:if> <br/>
</div>

<script>
    function callAlert(parts) {
        alert(parts);
    }
</script>
<c:forEach var="parts" items="${parts}" varStatus="status">
    <li class="product-wrapper">

        <div class="product-item">

            <img src="${parts.img}"/>
            <div class="product-list">
                <h3><b>${parts.producer}</b></h3>
                <h3>${parts.name}</h3>
                <form action="frontController?command=createord" method="post">
                    <input class="id" name="id" value="${parts.id}" readonly>
                    <input class="price" name="price" value="${parts.price}" readonly>
                    <input class="price" value="$">
                    <input type="number"
                           min="0"
                           max="10"
                           step="1"
                           value="1" name="quantity" id="quantity"><br/>
                    <c:if test="${not empty user}">
                        <input class="button" type="submit" values="
    <p><ftm:message bundle="${i18n}" key="ord.create"/>"></p>
                    </c:if>
                </form>
            </div>

        </div>

    </li>
</c:forEach>