<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
    <title>Interpol</title>
    <link rel="stylesheet" href="css/maipagst.css" type="text/css" media="all"/>
    <!--[if lte IE 6]>
    <link rel="stylesheet" href="css/ie6.css" type="text/css" media="all"/><![endif]-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <link rel="shortcut icon" href="images/interpol-logo.png" type="image/png"/>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <!-- JS -->
    <!-- End JS -->

</head>
<body>
<%--<p>Hello, ${user}!!!</p>--%>
<%--<p><a href="../controller?command=logout">Logout</a></p>--%>
<!-- Shell -->
<div class="shell">

    <!-- Header -->
    <div id="header">
        <h1 id="logo"><a href="../controller?command=home">INTERPOL</a></h1>

        <!-- Navigation -->
        <div id="navigation">
            <ul class="tab-group">
                <li class="tab active"><a href="#main">Wanted persons</a></li>
                <li class="tab"><a href="#profile">My Account</a></li>
            </ul>
        </div>
        <!-- End Navigation -->
    </div>
    <!-- End Header -->

    <div class="tab-content">
        <!-- Main -->
        <div id="main">
            <div class="wanted-persons">
                <ul>
                    <c:forEach var="person" items="${wantedPeople}">
                        <li>
                            <a href="../controller?command=show_full_person&person_id=<c:out value="${person.id}"/>">
                                <img src="data:image/png;base64,<c:out value="${person.image}"/>" alt=""/>
                                <div class="person-info">
                                    <h4><c:out value="${person.name}"/> <c:out value="${person.surname}"/></h4>
                                    <div class="person-desc">
                                        <p class="birthDate-info">Date of birth: <c:out value="${person.birthDate}"/></p>
                                        <strong class="state-info">
                                            <c:set var="nationality_str" value="${person.nationality}" />
                                            <c:forTokens var="national" items="${nationality_str}" delims="[]">
                                                <c:out value="${national}"/>
                                            </c:forTokens>
                                        </strong>
                                    </div>
                                </div>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <!-- Sidebar -->
            <div id="sidebar">
                <!-- Search -->
                <div class="box search">
                    <h2>Filter</h2>
                    <div class="box-content">
                        <form action="../controller" method="post">
                            <input type="hidden" name="command" value="search">
                            <div class="form-group">
                                <label for="usr" class="filter-label">Name:</label>
                                <input type="text" class="form-control form-control-sm" id="usr"
                                       name="wanted_person_name" value="<c:out value="${personName}"/>">
                            </div>
                            <div class="form-group">
                                <label for="surname" class="filter-label">Surname:</label>
                                <input type="text" class="form-control form-control-sm" id="surname"
                                       name="wanted_person_surname" value="${personSurname}">
                            </div>
                            <div class="gender-group">
                                <label class="filter-label">Gender:</label>
                                <c:choose>
                                    <c:when test="${personGender eq 'Male'}">
                                        <label class="radio-inline male-label"><input type="radio" name="gender"
                                                                                      value="Male" checked>Male</label>
                                        <label class="radio-inline"><input type="radio" name="gender" value="Female">Female</label>
                                        <label class="radio-inline"><input type="radio" name="gender" value="Unknown">Unknown</label>
                                    </c:when>
                                    <c:when test="${personGender eq 'Female'}">
                                        <label class="radio-inline male-label"><input type="radio" name="gender"
                                                                                      value="Male">Male</label>
                                        <label class="radio-inline"><input type="radio" name="gender" value="Female" checked>Female</label>
                                        <label class="radio-inline"><input type="radio" name="gender" value="Unknown">Unknown</label>
                                    </c:when>
                                    <c:otherwise>
                                        <label class="radio-inline male-label"><input type="radio" name="gender"
                                                                                      value="Male">Male</label>
                                        <label class="radio-inline"><input type="radio" name="gender" value="Female">Female</label>
                                        <label class="radio-inline"><input type="radio" name="gender" value="Unknown"
                                                                           checked>Unknown</label>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                            <div class="form-group">
                                <label class="filter-label">Current birthDate:</label>
                                <p>From:
                                    <input class="form-control form-control-lg" pattern="[0-9]{1,3}" size="2"
                                           type="text"
                                           name="fromAge"
                                           id="minAge"
                                           value="${fromAge}" maxlength="3"/>
                                    To:
                                    <input class="form-control form-control-sm" size="2" type="text" name="toAge"
                                           id="maxAge" value="${toAge}"
                                           maxlength="3"/>
                                </p>
                            </div>
                            <div class="form-group">
                                <label for="state" class="filter-label">Nationality:</label>
                                <select class="form-control" id="state" name="nation">
                                    <c:if test="${not empty nation}">
                                        <option></option>
                                    </c:if>
                                    <option>${nation}</option>
                                    <c:forEach var="nationVar" items="${nationalities}">
                                        <option value="<c:out value="${nationVar}"/>">${nationVar}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <button type="submit" class="search-submit">Search</button>
                        </form>
                    </div>
                </div>
                <!-- End Search -->

            </div>
            <!-- End Sidebar -->

            <div class="cl">&nbsp;</div>
        </div>
        <!-- End Main -->
        <div id="profile">
            <div class="container" style="width: 100%;">
                <div class="row info-table">

                    <div class="col-sm-8 col-md-8 center-block">
                        <table class="table table-bordered">
                            <tbody>
                            <tr>
                                <th scope="row" colspan="2" class="general-field"><h4>Profile info</h4></th>
                            </tr>
                            <tr>
                                <th scope="row">Login</th>
                                <td>${user.login}</td>
                            </tr>
                            <tr>
                                <th scope="row">Email</th>
                                <td>${user.email}</td>
                            </tr>
                            <tr>
                                <th scope="row">Role</th>
                                <td>${user.role}</td>
                            </tr>
                            <tr>
                                <th scope="row" colspan="2" class="general-field"><h4>Assessment</h4></th>
                            </tr>
                            <tr>
                                <th scope="row">Messages</th>
                                <td>${user.assessment.numberOfMessages}</td>
                            </tr>
                            <tr>
                                <th scope="row" class="col-sm-3">Assessment</th>
                                <td>${user.assessment.assessmentText}</td>
                            </tr>
                            </tbody>
                        </table>
                        <div class="center-btn">
                            <a class="btn send-button us-prof-btn" href="edit_email.jsp">Edit Email</a>
                            <a class="btn back-button us-prof-btn" href="../controller?command=logout">Log out</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

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
                                <th scope="row">Birth Date</th>
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

<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="${pageContext.request.contextPath}/jsp/js/login-ind.js"></script>
</body>
</html>