<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>Send Message</title>
    <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/css/login.css" type="text/css">
</head>

<body>

<div class="form">
            <h1>Edit Email</h1>
            <p>${edit_email_error}</p>
            <form action="../controller" method="post">
                <input type="hidden" name="command" value="edit_email">

                <div class="field-wrap">
                    <label>
                        New email...
                    </label>
                    <input type="email" name="email" required autocomplete="off"/>
                </div>

                <div class="field-wrap">
                    <label>
                        Password<span class="req">*</span>
                    </label>
                    <input type="password" name="password" required autocomplete="off"/>
                </div>
                <div>
                    <button type="submit" class="button button-block send-button">Save</button>
                    <button type="button" class="button button-block back-button" onclick="history.back()">Back</button>
                </div>
            </form>
        </div>

    </div><!-- tab-content -->

</div> <!-- /form -->
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="${pageContext.request.contextPath}/jsp/js/login-ind.js"></script>
</body>

</html>
