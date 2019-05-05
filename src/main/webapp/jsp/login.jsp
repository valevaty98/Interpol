<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Interpol Authorisation</title>
    <link rel="shortcut icon" href="<c:url value="/jsp/images/interpol-logo.png"/>" type="image/png"/>
    <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <link rel="stylesheet" href="<c:url value="/jsp/css/loginiki.css"/>">
</head>

<body>
<div class="form">
    <ul class="tab-group">
        <li class="tab active"><a href="#sign-up">Sign Up</a></li>
        <li class="tab"><a href="#login">Log In</a></li>
    </ul>

    <div class="tab-content">
        <div id="sign-up">
            <h1>Sign Up</h1>
            <p>${error}</p>
            <form action="controller" method="post">
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

        <div id="login">
            <h1>Log In</h1>
            <form action="controller" method="post">
                <input type="hidden" name="command" value="login">
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
                <p class="forgot"><a href="#">Forgot Password?</a></p>
                <button class="button button-block">Log In</button>
            </form>
        </div>
    </div>
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="<c:url value="/jsp/js/loginnn.js"/>"></script>
</body>
</html>
