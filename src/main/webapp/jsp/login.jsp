<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Interpol Authorisation</title>
    <link rel="shortcut icon" href="<c:url value="/static/images/interpol-logo.png"/>" type="image/png"/>
    <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <link rel="stylesheet" href="<c:url value="/static/css/loginiki.css"/>">
</head>

<body>
<div class="form">
    <ul class="tab-group">
        <li class="tab"><a href="#sign-up">Sign Up</a></li>
        <li class="tab active"><a href="#login">Log In</a></li>
    </ul>
    <div class="error">
        <p>${wrongCommand}</p>
        <p>${loginError}</p>
        <p>${signUpError}</p>
        <p>${illegalActionError}</p>
    </div>
    <div class="tab-content">
        <div id="login">
            <form action="<c:url value="/controller"/>" method="post">
                <input type="hidden" name="command" value="login">
                <h1>Log In</h1>
                <div class="field-wrap">
                    <label>
                        Login<span class="req">*</span>
                    </label>
                    <input type="text" name="login" required autocomplete="off"/>
                </div>
                <div class="field-wrap">
                    <label>
                        Password<span class="req">*</span>
                    </label>
                    <input type="password" name="password" required autocomplete="off"/>
                </div>
                <button class="button button-block">Log In</button>
            </form>
        </div>
        <div id="sign-up">
            <form action="<c:url value="/controller"/>" method="post">
                <h1>Sign Up</h1>
                <input type="hidden" name="command" value="sign_up">
                <div class="field-wrap">
                    <label>
                        Login<span class="req">*</span>
                    </label>
                    <input type="text" name="login" required autocomplete="off"/>
                </div>
                <div class="field-wrap">
                    <label>
                        Email Address<span class="req">*</span>
                    </label>
                    <input type="email" name="email" required autocomplete="off"/>
                </div>
                <div class="field-wrap">
                    <label>
                        Set A Password<span class="req">*</span>
                    </label>
                    <input type="password" name="password" required autocomplete="off"/>
                </div>
                <button type="submit" class="button button-block">Sign In</button>
            </form>
        </div>
    </div>
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="<c:url value="/static/js/index.js"/>"></script>
</body>
</html>
