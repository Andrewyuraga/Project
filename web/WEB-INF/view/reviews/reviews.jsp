<%--
  Created by IntelliJ IDEA.
  User: andrewyuraga
  Date: 11/23/18
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<ftm:setLocale value="${sessionScope.locale}"/>
<ftm:setBundle basename="messages" var="i18n"/>

<div style="font-size: large">
    <c:if test="${not empty message}">INFO : ${message}</c:if> <br/>
</div>
<center>
    <c:forEach var="Reviews" items="${reviews}" varStatus="status">
        <div class="revw">
            <h5><ftm:message bundle="${i18n}" key="user.usernumber"/> №${Reviews.userId}</h5><br/>
            <h3><i>${Reviews.reviews}</i></h3>
            <c:if test="${not empty user and user.rights eq 'ADMIN'}">
                <form action="frontController?command=deleteReview" method="post">
                    <button value="${Reviews.id}" name="id" class="btn"><ftm:message bundle="${i18n}"
                                                                                     key="parts.delete"/></button>
                </form>
            </c:if>
            <br/>
        </div>
        <br/>
    </c:forEach>
</center>
<c:if test="${not empty user}">
    <center><h5><ftm:message bundle="${i18n}" key="reviews.title"/></h5></center>
    <div class="review_form">
        <form id="form_input" action="frontController?command=createReview" method="post">
            <ftm:setLocale value="${sessionScope.locale}"/>
            <ftm:setBundle basename="messages" var="i18n"/>
                <ftm:message bundle="${i18n}" key="reviews.text"/></br>
            <input class="text" type="text" name="text" placeholder="text" maxlenght="255" required></br>
            <input type="submit" values="
        <ftm:message bundle="${i18n}" key="reviews.submit"/>"><br/>
        </form>
    </div>
    <br/>
</c:if>