<%@ page isErrorPage="true" isELIgnored="false" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctl" uri="/WEB-INF/tld/custom.tld" %>

<html>
<head>
    <title>Exception</title>
    <link rel="stylesheet" href="<c:url value="/static/css/main-style.css"/>" type="text/css" media="all"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <link rel="shortcut icon" href="<c:url value="/static/images/interpol-logo.png"/>" type="imageEncoded/png"/>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
</head>
<body>
<div class="shell">
    <div id="header">
        <h1 id="logo"><a href="<c:url value="/controller?command=home"/>">INTERPOL</a></h1>
    </div>
    <div class="tab-content">
        <h2>Exception occurred while processing the request</h2>
        <p>Request from ${pageContext.errorData.requestURI} is failed</p>
        <p>Servlet name: ${pageContext.errorData.servletName}</p>
        <p>Status code: ${pageContext.errorData.statusCode}</p>
        <p>Type: ${pageContext.exception}</p>
        <p>Message: ${pageContext.exception.message}</p>
    </div>
    <ctl:custom-footer/>
</div>
</body>
</html>