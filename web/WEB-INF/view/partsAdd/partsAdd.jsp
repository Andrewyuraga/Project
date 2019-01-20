<%--
  Created by IntelliJ IDEA.
  User: andrewyuraga
  Date: 11/21/18
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<ftm:setLocale value="${sessionScope.locale}"/>
<ftm:setBundle basename="messages" var="i18n"/>
<center><h5><ftm:message bundle="${i18n}" key="partsadd.namePage"/></h5></center>
<form id="form_input" action="frontController?command=partsadd" method="post">
    <ftm:message bundle="${i18n}" key="partsadd.producer"/><br/>
    <input type="text" placeholder="producer" name="producer" id="producer" required/><br/>
    <ftm:message bundle="${i18n}" key="partsadd.category"/><br/>
    <input type="text" placeholder="category" name="category" id="category" required/><br/>
    <ftm:message bundle="${i18n}" key="partsadd.img"/><br/>
    <input type="text" placeholder="img" name="img" id="img" required/><br/>
    <ftm:message bundle="${i18n}" key="partsadd.name"/><br/>
    <input type="text" placeholder="name" name="name" id="name" required/><br/>
    <ftm:message bundle="${i18n}" key="partsadd.chatacteristics"/><br/>
    <input type="text" placeholder="chatacteristics" name="chatacteristics" id="chatacteristics" required/><br/>
    <ftm:message bundle="${i18n}" key="partsadd.price"/><br/>
    <input type="text" placeholder="price" name="price" id="price" required pattern="\d+(\.\d{2})?"/><br/>
    <input type="submit" values="
    <p><ftm:message bundle="${i18n}" key="partsadd.submit"/>"></p>
</form>