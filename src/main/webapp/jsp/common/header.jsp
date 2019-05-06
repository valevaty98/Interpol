<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Header</title>
</head>
<body>
<div id="header">
    <h1 id="logo"><a href="<c:url value="/controller?command=home"/>">INTERPOL</a></h1>
    <div id="navigation">
        <ul class="tab-group">
            <li class="tab active"><a href="#wanted-people-tab">Wanted persons</a></li>
            <li class="tab"><a href="#user-profile-tab">My Account</a></li>
        </ul>
    </div>
</div>
</body>
</html>
