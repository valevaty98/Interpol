<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<c:if test="${user.lang.toString() eq 'rus'}">
    <fmt:setLocale value="ru_RU"/>
</c:if>
<fmt:setBundle basename="props.pagescontent"/>

<html>
<head>
    <title>Header</title>
</head>
<body>
<div id="header">
    <h1 id="logo"><a href="<c:url value="/controller?command=home"/>">INTERPOL</a></h1>
    <div id="navigation">
        <ul class="tab-group">
            <li class="tab active"><a href="#wanted-people-tab"><fmt:message key="header.tab.wanted-people"/> </a></li>
            <li class="tab"><a href="#user-profile-tab"><fmt:message key="header.tab.profile"/> </a></li>
        </ul>
    </div>
</div>
</body>
</html>
