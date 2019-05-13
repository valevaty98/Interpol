<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <!--<style>
        #wanted-people-tab {
            display: none;
        }
    </style>
    -->
</head>
<body>
<div class="shell">
    <!-- Header -->
    <c:import url="common/header.jsp" charEncoding="utf-8"/>
    <!-- End Header -->
    <div class="tab-content">
        <div id="all-users-messages">
            <div class="container" style="width: 100%;">
                <div class="row info-table">
                    <div class="col-sm-12 col-md-12">
                        <table class="table table-bordered">
                            <tbody>
                            <c:forEach var="message" items="${messages_info_list}">
                                <c:if test="${message.message.status.toString() eq 'checked'}">
                                    <tr class="message-row" style="background-color: #a7c3d8;"
                                        onclick="window.location='<c:url value="/controller?command=show_full_message&message_id=${message.message.id}"/>';">
                                        <th scope="row" class="col-sm-2">${message.userLogin}</th>
                                        <td class="col-sm-2">${message.message.date}</td>
                                        <td class="col-sm-4">${message.wantedPersonName} ${message.wantedPersonSurname}</td>
                                        <td class="col-sm-4">${message.message.subject}</td>
                                    </tr>
                                </c:if>
                                <c:if test="${message.message.status.toString() eq 'unchecked'}">
                                    <tr class="message-row"
                                        onclick="window.location='<c:url value="/controller?command=show_full_message&message_id=${message.message.id}"/>';">
                                        <th scope="row" class="col-sm-2">${message.userLogin}</th>
                                        <td class="col-sm-2">${message.message.date}</td>
                                        <td class="col-sm-4">${message.wantedPersonName} ${message.wantedPersonSurname}</td>
                                        <td class="col-sm-4">${message.message.subject}</td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div>
<%--                            <c:if test="${user.role eq 'ADMIN'}">--%>
<%--                                <a class="btn send-button" href="<c:url value="/controller?command=delete_person&person_id=${wantedPerson.id}"/>">Delete Wanted Person</a>--%>
<%--                            </c:if>--%>
<%--                            <a class="btn send-button" href="send_message.jsp">Send message</a>--%>
                            <a class="btn send-button" href="<c:url value="/controller?command=home"/>">Back to Wanted Persons</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <c:import url="common/user_profile_tab.jsp" charEncoding="utf-8"/>
        <c:import url="common/wanted_people_tab.jsp" charEncoding="utf-8"/>

    </div>
    <!-- Footer -->
    <c:import url="common/footer.jsp" charEncoding="utf-8"/>
    <!-- End Footer -->
</div>

<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
<script src="<c:url value="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"/>"></script>
<script src="<c:url value="/static/js/main-ind.js"/>"></script>
</body>
</html>