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
    <title><fmt:message key="send-message.title"/></title>
    <link rel="shortcut icon" href="<c:url value="/static/images/interpol-logo.png"/>" type="imageEncoded/png"/>
    <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css"
          type="text/css">
    <link rel="stylesheet" href="<c:url value="/static/css/input-pages-style.css"/>" type="text/css">
</head>

<body>
<div class="form">
    <p>${sendMessageError}</p>
    <h1><fmt:message key="send-message.label.head"/></h1>
    <form action="<c:url value="/controller"/>" method="post">
        <input type="hidden" name="command" value="send_message"/>
        <input type="hidden" name="person_id" value="<c:out value="${param.person_id}"/>"/>
        <div class="field-wrap">
            <label>
                <fmt:message key="send-message.label.subject"/><span class="req">*</span>
            </label>
            <input type="text" name="subject" maxlength="50" required autocomplete="off"/>
        </div>
        <div class="field-wrap">
            <label>
                <fmt:message key="send-message.label.write"/>..<span class="req">*</span>
            </label>
            <textarea name="message" rows="4" maxlength="500" required autocomplete="off"></textarea>
        </div>
        <div>
            <button type="submit" class="button button-block send-button"><fmt:message key="send-message.button.send"/></button>
            <button type="button" class="button button-block back-button" onclick="history.back()"><fmt:message key="send-message.button.back"/></button>
        </div>
    </form>
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="<c:url value="/static/js/input-page-index.js"/>"></script>
</body>
</html>
