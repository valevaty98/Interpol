<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctl" uri="/WEB-INF/tld/custom.tld" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
    <title>Interpol</title>
    <link rel="stylesheet" href="<c:url value="/static/css/style.css"/>" type="text/css" media="all"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <link rel="shortcut icon" href="<c:url value="/static/images/interpol-logo.png"/>" type="image/png"/>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
<div class="shell">
    <!-- Header -->
    <c:import url="common/header.jsp" charEncoding="utf-8"/>
    <!-- End Header -->
    <div class="tab-content">
        <div id="full-message">
            <div class="container" style="width: 100%;">
                <div class="row info-table">
                    <div class="col-sm-4 col-md-4">
                        <img src="data:image/png;base64,<c:out value="${message.wantedPersonImage}"/>"
                             alt="${message.wantedPersonSurname}" class="img-rounded img-responsive"/>
                    </div>
                    <div class="col-sm-8 col-md-8">
                        <table class="table table-bordered">
                            <tbody>
                            <tr>
                                <th scope="row" class="col-sm-3 col-md-3">Wanted Person</th>
                                <td class="col-sm-9 col-md-9">${message.wantedPersonName} ${message.wantedPersonSurname} </td>
                            </tr>
                            <tr>
                                <th scope="row">User Login</th>
                                <td>${message.userLogin}</td>
                            </tr>
                            <tr>
                                <th scope="row">User Email</th>
                                <td>${message.userEmail}</td>
                            </tr>
                            <tr>
                                <th scope="row">Subject</th>
                                <td>${message.message.subject}</td>
                            </tr>
                            <tr>
                                <th scope="row">Date</th>
                                <td>${message.message.date}</td>
                            </tr>
                            <tr>
                                <th scope="row">Message</th>
                                <td>${message.message.message}</td>
                            </tr>
                            <tr>
                                <th scope="row">Message Status</th>
                                <td>${message.message.status}</td>
                            </tr>
                            </tbody>
                        </table>
                        <div>
                            <a class="btn send-button"
                               href="<c:url value="/jsp/send_response_to_user.jsp?message_id=${message.message.id}&user_email=${message.userEmail}"/>">
                                Send response to user
                            </a>
                            <a class="btn send-button"
                               href="<c:url value="/controller?command=show_all_messages"/>">
                                Back
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <c:import url="common/user_profile_tab.jsp" charEncoding="utf-8"/>
        <c:import url="common/wanted_people_tab.jsp" charEncoding="utf-8"/>

    </div>
    <!-- Footer -->
    <ctl:custom-footer/>
    <!-- End Footer -->
</div>

<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
<script src="<c:url value="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"/>"></script>
<script src="<c:url value="/static/js/main-ind.js"/>"></script>
</body>
</html>