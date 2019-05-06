<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
    <title>Interpol</title>
    <link rel="stylesheet" href="<c:url value="/jsp/css/style.css"/>" type="text/css" media="all"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <link rel="shortcut icon" href="<c:url value="/jsp/images/interpol-logo.png"/>" type="image/png"/>
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
        <div id="wanted-full">
        <div class="container" style="width: 100%;">
            <div class="row info-table">
                <div class="col-sm-4 col-md-4">
                    <img src="data:image/png;base64,<c:out value="${wantedPerson.image}"/>"
                         alt="${wantedPerson.surname}" class="img-rounded img-responsive"/>
                </div>
                <div class="col-sm-8 col-md-8">
                    <table class="table table-bordered">
                        <tbody>
                        <tr>
                            <th scope="row">Name</th>
                            <td>${wantedPerson.name}</td>
                        </tr>
                        <tr>
                            <th scope="row">Surname</th>
                            <td>${wantedPerson.surname}</td>
                        </tr>
                        <tr>
                            <th scope="row">Gender</th>
                            <td>${wantedPerson.gender}</td>
                        </tr>
                        <tr>
                            <th scope="row">Birth Date</th>
                            <td>${wantedPerson.birthDate}</td>
                        </tr>
                        <tr>
                            <th scope="row">Birth Place</th>
                            <td>${wantedPerson.birthPlace.name}</td>
                        </tr>
                        <tr>
                            <th scope="row">Nationality</th>
                            <c:set var="nationality_str" value="${wantedPerson.nationality}" />
                            <td>
                                <c:forTokens var="national" items="${nationality_str}" delims="[]">
                                    <c:out value="${national}"/>
                                </c:forTokens>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">Height</th>
                            <td>${wantedPerson.height} m</td>
                        </tr>
                        <tr>
                            <th scope="row">Weight</th>
                            <td>${wantedPerson.weight} kg</td>
                        </tr>
                        <tr>
                            <th scope="row">Charges</th>
                            <td>${wantedPerson.charges}</td>
                        </tr>
                        <tr>
                            <th scope="row" class="col-sm-3">Description</th>
                            <td>${wantedPerson.characteristics}
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div>
                        <c:if test="${user.role eq 'ADMIN'}">
                            <a class="btn send-button" href="<c:url value="/controller?command=delete_person&person_id=${wantedPerson.id}"/>">Delete Wanted Person</a>
                        </c:if>
                        <a class="btn send-button" href="send_message.jsp">Send message</a>
                        <button type="button" class="btn back-button" onclick="history.back()">Back</button>
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
<script src="<c:url value="/jsp/js/index.js"/>"></script>
</body>
</html>