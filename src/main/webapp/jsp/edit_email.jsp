<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<c:if test="${user.lang.toString() eq 'rus'}">
    <fmt:setLocale value="ru_RU"/>
</c:if>
<fmt:setBundle basename="props.pagescontent"/>

<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="edit-email.title"</title>
    <link rel="shortcut icon" href="<c:url value="/static/images/interpol-logo.png"/>" type="image/png"/>
    <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css"
          type="text/css">
    <link rel="stylesheet" href="<c:url value="/static/css/loginiki.css"/>" type="text/css">
</head>

<body>
<div class="form">
    <h1><fmt:message key="edit-email.label.head"/></h1>
    <p>${edit_email_error}</p>
    <form action="<c:url value="/controller"/>" method="post">
        <input type="hidden" name="command" value="edit_email">
        <div class="field-wrap">
            <label>
                <fmt:message key="edit-email.label.new-email"/>...
            </label>
            <input type="email" name="email" required autocomplete="off"/>
        </div>
        <div class="field-wrap">
            <label>
                <fmt:message key="edit-email.label.password"/><span class="req">*</span>
            </label>
            <input type="password" name="password" required autocomplete="off"/>
        </div>
        <div>
            <button type="submit" class="button button-block send-button"><fmt:message key="edit-email.button.save"/></button>
            <button type="button" class="button button-block back-button" onclick="history.back()"><fmt:message key="edit-email.button.back"/></button>
        </div>
    </form>
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="<c:url value="/static/js/index.js"/>"></script>
</body>

</html>
