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

    <div class="tab-content">
        <div id="signup">
            <h1>Send Message</h1>

            <form action="mailServlet" method="post">

                <!--                <div class="top-row">-->
                <!--                    <div class="field-wrap">-->
                <!--                        <label>-->
                <!--                            First Name<span class="req">*</span>-->
                <!--                        </label>-->
                <!--                        <input type="text" required autocomplete="off" />-->
                <!--                    </div>-->

                <!--                    <div class="field-wrap">-->
                <!--                        <label>-->
                <!--                            Last Name<span class="req">*</span>-->
                <!--                        </label>-->
                <!--                        <input type="text"required autocomplete="off"/>-->
                <!--                    </div>-->
                <!--                </div>-->

                <div class="field-wrap">
                    <label>
                        Subject<span class="req">*</span>
                    </label>
                    <input type="text" name="subject" required autocomplete="off"/>
                </div>

                <div class="field-wrap">
                    <label>
                        Email Address (if you don't want to use account email)
                    </label>
                    <input type="email" autocomplete="off"/>
                </div>

                <div class="field-wrap">
                    <label>
                        Write A Message..<span class="req">*</span>
                    </label>
                    <textarea name="message" rows="4"></textarea>
                    <!--                    <input type="text"required autocomplete="off"/>-->
                </div>

                <div>
                    <button type="submit" class="button button-block send-button">Send</button>
                    <button type="submit" class="button button-block back-button">Back</button>
                </div>

            </form>

        </div>

        <div id="login">

        </div>

    </div><!-- tab-content -->

</div> <!-- /form -->
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="${pageContext.request.contextPath}/jsp/js/login-ind.js"></script>
</body>

</html>
