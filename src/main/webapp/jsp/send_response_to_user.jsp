<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Send Message To Interpol</title>
    <link rel="shortcut icon" href="<c:url value="/static/images/interpol-logo.png"/>" type="image/png"/>
    <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css"
          type="text/css">
    <link rel="stylesheet" href="<c:url value="/static/css/loginiki.css"/>" type="text/css">
</head>

<body>
<div class="form">
    <h1>Send Message</h1>
    <p>${edit_email_error}</p>
    <form action="<c:url value="/mailServlet"/>" method="post">
        <input type="hidden" name="command" value="send_response_to_user"/>
        <input type="hidden" name="message_id" value="<c:out value="${param.message_id}"/>"/>
        <input type="hidden" name="email" value="<c:out value="${param.user_email}"/>"/>
        <div class="field-wrap">
            <label>
                Subject<span class="req">*</span>
            </label>
            <input type="text" name="subject" required autocomplete="off"/>
        </div>
        <div class="field-wrap">
            <label class="active highlight">
                User Email Address
            </label>
            <input type="email" autocomplete="off" disabled value="<c:out value="${param.user_email}"/>"/>
        </div>
        <div class="field-wrap">
            <label>
                Write A Message..<span class="req">*</span>
            </label>
            <textarea name="message" rows="4" required autocomplete="off"></textarea>
        </div>
        <div>
            <button type="submit" class="button button-block send-button">Send Response</button>
            <button type="button" class="button button-block back-button" onclick="history.back()">Back</button>
        </div>
    </form>
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="<c:url value="/static/js/index.js"/>"></script>
</body>
</html>
