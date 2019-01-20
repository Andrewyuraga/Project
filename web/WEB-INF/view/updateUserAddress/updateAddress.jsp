<%--
  Created by IntelliJ IDEA.
  User: andrewyuraga
  Date: 11/26/18
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<ftm:setLocale value="${sessionScope.locale}"/>
<ftm:setBundle basename="messages" var="i18n"/>
<center><h5><ftm:message bundle="${i18n}" key="user.update"/></h5></center>
<form id="form_input" action="frontController?command=updateaddress" method="post">
    <ftm:message bundle="${i18n}" key="user.address"/><br/>
    <input type="text" placeholder="address" name="address" id="address" required/><br/>
    <input type="submit" values="
    <p><ftm:message bundle="${i18n}" key="user.submit"/>"></p>
</form>
