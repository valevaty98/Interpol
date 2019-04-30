<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
    <title>Interpol</title>
    <link rel="stylesheet" href="css/mapag-style.css" type="text/css" media="all"/>
    <!--[if lte IE 6]>
    <link rel="stylesheet" href="css/ie6.css" type="text/css" media="all"/><![endif]-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="shortcut icon" href="images/interpol-logo.png" type="image/png"/>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>


</head>
<body>
<!-- Shell -->
<div class="shell">

    <!-- Header -->
    <div id="header">
        <h1 id="logo"><a href="#../controller?command=refresh">INTERPOL</a></h1>

        <!-- Navigation -->
        <div id="navigation">
            <ul class="tab-group">
                <li class="tab active"><a href="../controller?command=login">Wanted persons</a></li>
                <li class="tab"><a href="#profile">My Account</a></li>
            </ul>
        </div>
        <!-- End Navigation -->
    </div>
    <!-- End Header -->

    <div class="tab-content">
        <div id="wanted-full">
            <div class="container">
                <div class="row info-table">
                    <div class="col-sm-4 col-md-4">
                        <img src="images/interpol-logo.png"
                             alt="" class="img-rounded img-responsive"/>
                    </div>
                    <div class="col-sm-8 col-md-8">
                        <table class="table table-bordered">
                            <tbody>
                            <tr>
                                <th scope="row">Name</th>
                                <td>Mark</td>
                            </tr>
                            <tr>
                                <th scope="row">Surname</th>
                                <td>Otto</td>
                            </tr>
                            <tr>
                                <th scope="row">Gender</th>
                                <td>Male</td>
                            </tr>
                            <tr>
                                <th scope="row">Age</th>
                                <td>35</td>
                            </tr>
                            <tr>
                                <th scope="row">State</th>
                                <td>Belarus</td>
                            </tr>
                            <tr>
                                <th scope="row">Height</th>
                                <td>1.75m</td>
                            </tr>
                            <tr>
                                <th scope="row">Weight</th>
                                <td>75kg</td>
                            </tr>
                            <tr>
                                <th scope="row">Charges</th>
                                <td>murderer</td>
                            </tr>
                            <tr>
                                <th scope="row" class="col-sm-3">Description</th>
                                <td>kssssssssssssssssss sssssssssssssssssssssss sssssssssssssssssssssssss
                                    sssssssssssssssssss sssssssss
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <div>
                            <button type="button" class="btn send-button">Send message</button>
                            <button type="button" class="btn back-button" onclick="history.back()">Back</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <div id="footer">
        <p class="right">
            &copy; 2019 Interpol.
            Designed by Vladislav Valevaty
        </p>
    </div>
    <!-- End Footer -->

</div>
<!-- End Shell -->
<!-- jQuery -->
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="${pageContext.request.contextPath}/jsp/js/login-ind.js"></script>
</body>
</html>