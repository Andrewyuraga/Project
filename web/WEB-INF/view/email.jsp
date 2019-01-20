<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h5>Input form</h5>
<div class="container text-center">
    <form id="form_input" action="frontController?command=email" method="post">
        <ftm:setLocale value="${sessionScope.locale}"/>
        <ftm:setBundle basename="messages" var="i18n"/>
        <ftm:message bundle="${i18n}" key="email.email"/></br>
        <input type="text" name="email" placeholder="email" maxlenght="30"/></br>
        <ftm:message bundle="${i18n}" key="email.password"/></br>
        <input type="password" name="password" placeholder="password" maxlenght="30"/><br/>
        <input type="submit" values="
        <ftm:message bundle="${i18n}" key="email.submit"/>"><br/>
    </form>
</div>
<br/>
