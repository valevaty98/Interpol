<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
    <title>Interpol</title>
    <link rel="stylesheet" href="css/maipai.css" type="text/css" media="all"/>
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
                                        <p class="age-info"><c:out value="${person.age}"/> years old</p>
                                        <strong class="state-info"><c:out value="${person.birthPlace}"/></strong>
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
                                <label class="radio-inline male-label"><input type="radio" name="gender"
                                                                              value="Male">Male</label>
                                <label class="radio-inline"><input type="radio" name="gender" value="Female">Female</label>
                                <label class="radio-inline"><input type="radio" name="gender" value="Unknown"
                                                                   checked>Unknown</label>
                            </div>

                            <div class="form-group">
                                <label class="filter-label">Current age:</label>
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
                                    <option></option>
                                    <option value="AF">Afghanistan</option>
                                    <option value="AL">Albania</option>
                                    <option value="DZ">Algeria</option>
                                    <option value="AS">American Samoa, United States</option>
                                    <option value="AD">Andorra</option>
                                    <option value="AO">Angola</option>
                                    <option value="AI">Anguilla, United Kingdom</option>
                                    <option value="AG">Antigua and Barbuda</option>
                                    <option value="AR">Argentina</option>
                                    <option value="AM">Armenia</option>
                                    <option value="AU">Australia</option>
                                    <option value="AT">Austria</option>
                                    <option value="AZ">Azerbaijan</option>
                                    <option value="BS">Bahamas</option>
                                    <option value="BH">Bahrain</option>
                                    <option value="BD">Bangladesh</option>
                                    <option value="BB">Barbados</option>
                                    <option value="BY">Belarus</option>
                                    <option value="BE">Belgium</option>
                                    <option value="BZ">Belize</option>
                                    <option value="BJ">Benin</option>
                                    <option value="BT">Bhutan</option>
                                    <option value="BO">Bolivia</option>
                                    <option value="BA">Bosnia and Herzegovina</option>
                                    <option value="BW">Botswana</option>
                                    <option value="BR">Brazil</option>
                                    <option value="BN">Brunei</option>
                                    <option value="BG">Bulgaria</option>
                                    <option value="BF">Burkina Faso</option>
                                    <option value="BI">Burundi</option>
                                    <option value="KH">Cambodia</option>
                                    <option value="CM">Cameroon</option>
                                    <option value="CA">Canada</option>
                                    <option value="CV">Cape Verde</option>
                                    <option value="CF">Central African Republic</option>
                                    <option value="TD">Chad</option>
                                    <option value="CL">Chile</option>
                                    <option value="CN">China</option>
                                    <option value="CO">Colombia</option>
                                    <option value="KM">Comoros</option>
                                    <option value="CG">Congo</option>
                                    <option value="CD">Congo (Democratic Republic of)</option>
                                    <option value="CR">Costa Rica</option>
                                    <option value="HR">Croatia</option>
                                    <option value="CU">Cuba</option>
                                    <option value="CY">Cyprus</option>
                                    <option value="CZ">Czech Republic</option>
                                    <option value="CI">Côte d'Ivoire</option>
                                    <option value="DK">Denmark</option>
                                    <option value="DJ">Djibouti</option>
                                    <option value="DM">Dominica</option>
                                    <option value="DO">Dominican Republic</option>
                                    <option value="EC">Ecuador</option>
                                    <option value="EG">Egypt</option>
                                    <option value="SV">El Salvador</option>
                                    <option value="GQ">Equatorial Guinea</option>
                                    <option value="ER">Eritrea</option>
                                    <option value="EE">Estonia</option>
                                    <option value="SZ">Eswatini</option>
                                    <option value="ET">Ethiopia</option>
                                    <option value="FJ">Fiji</option>
                                    <option value="FI">Finland</option>
                                    <option value="FR">France</option>
                                    <option value="GA">Gabon</option>
                                    <option value="GM">Gambia</option>
                                    <option value="GE">Georgia</option>
                                    <option value="DE">Germany</option>
                                    <option value="GH">Ghana</option>
                                    <option value="GR">Greece</option>
                                    <option value="GD">Grenada</option>
                                    <option value="GT">Guatemala</option>
                                    <option value="GN">Guinea</option>
                                    <option value="GW">Guinea Bissau</option>
                                    <option value="GY">Guyana</option>
                                    <option value="HT">Haiti</option>
                                    <option value="HN">Honduras</option>
                                    <option value="HU">Hungary</option>
                                    <option value="IS">Iceland</option>
                                    <option value="IN">India</option>
                                    <option value="ID">Indonesia</option>
                                    <option value="IR">Iran</option>
                                    <option value="IQ">Iraq</option>
                                    <option value="IE">Ireland</option>
                                    <option value="IL">Israel</option>
                                    <option value="IT">Italy</option>
                                    <option value="JM">Jamaica</option>
                                    <option value="JP">Japan</option>
                                    <option value="JO">Jordan</option>
                                    <option value="KZ">Kazakhstan</option>
                                    <option value="KE">Kenya</option>
                                    <option value="KI">Kiribati</option>
                                    <option value="KR">Korea (Republic of)</option>
                                    <option value="KW">Kuwait</option>
                                    <option value="KG">Kyrgyzstan</option>
                                    <option value="LA">Laos</option>
                                    <option value="LV">Latvia</option>
                                    <option value="LB">Lebanon</option>
                                    <option value="LS">Lesotho</option>
                                    <option value="LR">Liberia</option>
                                    <option value="LY">Libya</option>
                                    <option value="LI">Liechtenstein</option>
                                    <option value="LT">Lithuania</option>
                                    <option value="LU">Luxembourg</option>
                                    <option value="MO">Macao, China</option>
                                    <option value="MG">Madagascar</option>
                                    <option value="MW">Malawi</option>
                                    <option value="MY">Malaysia</option>
                                    <option value="MV">Maldives</option>
                                    <option value="ML">Mali</option>
                                    <option value="MT">Malta</option>
                                    <option value="MH">Marshall Islands</option>
                                    <option value="MR">Mauritania</option>
                                    <option value="MU">Mauritius</option>
                                    <option value="MX">Mexico</option>
                                    <option value="FM">Micronesia, Federated States of</option>
                                    <option value="MD">Moldova</option>
                                    <option value="MC">Monaco</option>
                                    <option value="MN">Mongolia</option>
                                    <option value="ME">Montenegro</option>
                                    <option value="MA">Morocco</option>
                                    <option value="MZ">Mozambique</option>
                                    <option value="MM">Myanmar</option>
                                    <option value="NA">Namibia</option>
                                    <option value="NR">Nauru</option>
                                    <option value="NP">Nepal</option>
                                    <option value="NL">Netherlands</option>
                                    <option value="NZ">New Zealand</option>
                                    <option value="NI">Nicaragua</option>
                                    <option value="NE">Niger</option>
                                    <option value="NG">Nigeria</option>
                                    <option value="MK">North Macedonia</option>
                                    <option value="NO">Norway</option>
                                    <option value="OM">Oman</option>
                                    <option value="PK">Pakistan</option>
                                    <option value="PW">Palau</option>
                                    <option value="PS">Palestine, State of</option>
                                    <option value="PA">Panama</option>
                                    <option value="PG">Papua New Guinea</option>
                                    <option value="PY">Paraguay</option>
                                    <option value="PE">Peru</option>
                                    <option value="PH">Philippines</option>
                                    <option value="PL">Poland</option>
                                    <option value="PT">Portugal</option>
                                    <option value="QA">Qatar</option>
                                    <option value="RO">Romania</option>
                                    <option value="RU">Russia</option>
                                    <option value="RW">Rwanda</option>
                                    <option value="KN">Saint Kitts and Nevis</option>
                                    <option value="LC">Saint Lucia</option>
                                    <option value="VC">Saint Vincent and the Grenadines</option>
                                    <option value="WS">Samoa</option>
                                    <option value="SM">San Marino</option>
                                    <option value="ST">Sao Tome and Principe</option>
                                    <option value="SA">Saudi Arabia</option>
                                    <option value="SN">Senegal</option>
                                    <option value="RS">Serbia</option>
                                    <option value="SC">Seychelles</option>
                                    <option value="SL">Sierra Leone</option>
                                    <option value="SG">Singapore</option>
                                    <option value="SK">Slovakia</option>
                                    <option value="SI">Slovenia</option>
                                    <option value="SB">Solomon Islands</option>
                                    <option value="SO">Somalia</option>
                                    <option value="ZA">South Africa</option>
                                    <option value="SS">South Sudan</option>
                                    <option value="ES">Spain</option>
                                    <option value="LK">Sri Lanka</option>
                                    <option value="916">STL (Special Tribunal for Lebanon)</option>
                                    <option value="SD">Sudan</option>
                                    <option value="SR">Suriname</option>
                                    <option value="SE">Sweden</option>
                                    <option value="CH">Switzerland</option>
                                    <option value="SY">Syria</option>
                                    <option value="TJ">Tajikistan</option>
                                    <option value="TZ">Tanzania</option>
                                    <option value="TH">Thailand</option>
                                    <option value="TL">Timor-Leste</option>
                                    <option value="TG">Togo</option>
                                    <option value="TO">Tonga</option>
                                    <option value="TT">Trinidad and Tobago</option>
                                    <option value="TN">Tunisia</option>
                                    <option value="TR">Turkey</option>
                                    <option value="TM">Turkmenistan</option>
                                    <option value="TV">Tuvalu</option>
                                    <option value="UG">Uganda</option>
                                    <option value="UA">Ukraine</option>
                                    <option value="UNK">under UNMIK mandate (Kosovo)</option>
                                    <option value="AE">United Arab Emirates</option>
                                    <option value="GB">United Kingdom</option>
                                    <option value="US">United States</option>
                                    <option value="UY">Uruguay</option>
                                    <option value="UZ">Uzbekistan</option>
                                    <option value="VU">Vanuatu</option>
                                    <option value="VA">Vatican City State</option>
                                    <option value="VE">Venezuela</option>
                                    <option value="VN">Viet Nam</option>
                                    <option value="YE">Yemen</option>
                                    <option value="ZM">Zambia</option>
                                    <option value="ZW">Zimbabwe</option>
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
            <nav aria-label="Navigation for countries">
                <ul class="pagination">
                    <c:if test="${currentPage != 1}">
                        <li class="page-item"><a class="page-link"
                                                 href="../controller?command=get_page&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
                        </li>
                    </c:if>

                    <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <li class="page-item active"><a class="page-link">
                                        ${i} <span class="sr-only">(current)</span></a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link"
                                                         href="../controller?command=get_page&recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:if test="${currentPage lt noOfPages}">
                        <li class="page-item"><a class="page-link"
                                                 href="../controller?command=get_page&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
                        </li>
                    </c:if>
                </ul>
            </nav>
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

<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="${pageContext.request.contextPath}/jsp/js/login-ind.js"></script>
</body>
</html>