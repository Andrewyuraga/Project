<%--
  Created by IntelliJ IDEA.
  User: andrewyuraga
  Date: 11/18/18
  Time: 23:42
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<ftm:setLocale value="${sessionScope.locale}"/>
<ftm:setBundle basename="messages" var="i18n"/>
<center><h5><ftm:message bundle="${i18n}" key="register.namePage"/></h5></center>
<br/>
<form id="form_input" action="frontController?command=registration" method="post">
    <ftm:setLocale value="${sessionScope.locale}"/>
    <ftm:setBundle basename="messages" var="i18n"/>
    <ftm:message bundle="${i18n}" key="register.name"/><br/>
    <input type="text" placeholder="name" name="name" id="name" pattern="^[a-zA-Z]+$"/><br/>
    <ftm:message bundle="${i18n}" key="register.email"/><br/>
    <input type="text" placeholder="email" name="email" id="email" required
           pattern="/\A[^@]+@([^@\.]+\.)+[^@\.]+\z/"/><br/>
    <ftm:message bundle="${i18n}" key="register.password"/><br/>
    <input type="password" placeholder="password" name="password" id="password" required/><br/>
    <ftm:message bundle="${i18n}" key="register.address"/><br/>
    <input type="text" placeholder="address" name="address" id="address" required/><br/>
    <input type="submit" values="
    <p><ftm:message bundle="${i18n}" key="register.submit"/>"></p>
</form>