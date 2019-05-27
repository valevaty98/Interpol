<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctl" uri="/WEB-INF/tld/custom.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<c:if test="${user.lang.toString() eq 'rus'}">
    <fmt:setLocale value="ru_RU"/>
</c:if>
<fmt:setBundle basename="props.pagescontent"/>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
    <title>Interpol</title>
    <link rel="stylesheet" href="<c:url value="/static/css/main-style.css"/>" type="text/css" media="all"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <link rel="shortcut icon" href="<c:url value="/static/images/interpol-logo.png"/>" type="imageEncoded/png"/>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
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
                    <img src="data:imageEncoded/png;base64,<c:out value="${wantedPerson.imageEncoded}"/>"
                         alt="${wantedPerson.surname}" class="img-rounded img-responsive"/>
                </div>
                <div class="col-sm-8 col-md-8">
                    <table class="table table-bordered">
                        <tbody>
                        <tr>
                            <th scope="row"><fmt:message key="full-wanted.label.name"/></th>
                            <td>${wantedPerson.name}</td>
                        </tr>
                        <c:if test="${wantedPerson.surname != null}">
                            <tr>
                                <th scope="row"><fmt:message key="full-wanted.label.surname"/></th>
                                <td>${wantedPerson.surname}</td>
                            </tr>
                        </c:if>
                        <tr>
                            <th scope="row"><fmt:message key="full-wanted.label.gender"/></th>
                            <td>${wantedPerson.gender}</td>
                        </tr>
                        <tr>
                            <th scope="row"><fmt:message key="full-wanted.label.birth-date"/></th>
                            <td>${wantedPerson.birthDate}</td>
                        </tr>
                        <tr>
                            <th scope="row"><fmt:message key="full-wanted.label.birth-place"/></th>
                            <td>${wantedPerson.birthPlace.name}</td>
                        </tr>
                        <tr>
                            <th scope="row"><fmt:message key="full-wanted.label.nationalities"/></th>
                            <c:set var="nationality_str" value="${wantedPerson.nationality}" />
                            <td>
                                <c:forTokens var="national" items="${nationality_str}" delims="[]">
                                    <c:out value="${national}"/>
                                </c:forTokens>
                            </td>
                        </tr>
                        <c:if test="${wantedPerson.height != null}">
                            <tr>
                                <th scope="row"><fmt:message key="full-wanted.label.height"/></th>
                                <td>${wantedPerson.height} <fmt:message key="full-wanted.text.m"/></td>
                            </tr>
                        </c:if>
                        <c:if test="${wantedPerson.weight != null}">
                            <tr>
                                <th scope="row"><fmt:message key="full-wanted.label.weight"/></th>
                                <td>${wantedPerson.weight} <fmt:message key="full-wanted.text.kg"/></td>
                            </tr>
                        </c:if>
                        <tr>
                            <th scope="row"><fmt:message key="full-wanted.label.charges"/></th>
                            <td>${wantedPerson.charges}</td>
                        </tr>
                        <c:if test="${wantedPerson.characteristics != null}">
                            <tr>
                                <th scope="row" class="col-sm-3"><fmt:message key="full-wanted.label.description"/></th>
                                <td>${wantedPerson.characteristics}</td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
                    <div>
                        <c:if test="${user.role eq 'ADMIN'}">
                            <a class="btn send-button" href="<c:url value="/controller?command=delete_person&person_id=${wantedPerson.id}"/>"><fmt:message key="full-wanted.button.delete"/></a>
                        </c:if>
                        <a class="btn send-button" href="<c:url value="/jsp/send_message.jsp?person_id=${wantedPerson.id}"/>"><fmt:message key="full-wanted.button.send-message"/></a>
                        <a class="btn send-button" href="<c:url value="/controller?command=home"/>"><fmt:message key="full-wanted.button.back"/></a>
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
<script src="<c:url value="/static/js/main-page-index.js"/>"></script>
</body>
</html>