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
    <title><fmt:message key="set-assessment.title"/></title>
    <link rel="shortcut icon" href="<c:url value="/static/images/interpol-logo.png"/>" type="image/png"/>
    <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css"
          type="text/css">
    <link rel="stylesheet" href="<c:url value="/static/css/loginiki.css"/>" type="text/css">
</head>

<body>
<div class="form">
    <h1><fmt:message key="set-assessment.label.head"/></h1>
    <p>${set_assessment_error}</p>
    <br/>
    <form action="<c:url value="/controller"/>" method="post">
        <input type="hidden" name="command" value="set_assessment">
        <input type="hidden" name="message_id" value="<c:out value="${param.message_id}"/>">
        <input type="hidden" name="user_login" value="<c:out value="${param.user_login}"/>">
        <div class="field-wrap">
            <label class="active highlight">
                <fmt:message key="set-assessment.label.login"/>
            </label>
            <input type="text" autocomplete="off" disabled value="<c:out value="${param.user_login}"/>"/>
        </div>
        <div class="field-wrap">
            <label>
                <fmt:message key="set-assessment.label.assessment"/>...<span class="req">*</span>
            </label>
            <textarea name="assessment_message" rows="4" required autocomplete="off"></textarea>
        </div>
        <div>
            <button type="submit" class="button button-block send-button"><fmt:message key="set-assessment.button.set"/></button>
            <button type="button" class="button button-block back-button" onclick="history.back()"><fmt:message key="set-assessment.button.back"/></button>
        </div>
    </form>
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="<c:url value="/static/js/index.js"/>"></script>
</body>

</html>
