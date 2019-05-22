<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<c:if test="${user.lang.toString() eq 'rus'}">
    <fmt:setLocale value="ru_RU"/>
</c:if>
<fmt:setBundle basename="props.pagescontent"/>

<html>
<head>
    <title>User Profile</title>
</head>
<body>
<div id="user-profile-tab">
    <div class="container" style="width: 100%;">
        <div class="row info-table">
            <div class="col-sm-9 col-md-9 center-block">
                <table class="table table-bordered">
                    <tbody>
                    <tr>
                        <th scope="row" colspan="2" class="general-field">
                            <h4><fmt:message key="profile.label.profile-info"/></h4>
                        </th>
                    </tr>
                    <tr>
                        <th scope="row"><fmt:message key="profile.label.login"/></th>
                        <td>${user.login}</td>
                    </tr>
                    <tr>
                        <th scope="row"><fmt:message key="profile.label.email"/></th>
                        <td>${user.email}</td>
                    </tr>
                    <tr>
                        <th scope="row"><fmt:message key="profile.label.role"/></th>
                        <td>${user.role}</td>
                    </tr>
                    <tr>
                        <th scope="row" colspan="2" class="general-field"><h4><fmt:message key="profile.label.assessment"/></h4></th>
                    </tr>
                    <tr>
                        <th scope="row"><fmt:message key="profile.label.messages"/></th>
                        <td>${user.assessment.numberOfMessages}</td>
                    </tr>
                    <tr>
                        <th scope="row" class="col-sm-3"><fmt:message key="profile.label.assessment"/></th>
                        <td>${user.assessment.assessmentText}</td>
                    </tr>
                    </tbody>
                </table>
                <div class="center-btn">
                    <a class="btn send-button us-prof-btn" href="<c:url value="/jsp/edit_email.jsp"/>"><fmt:message key="profile.button.edit-email"/></a>
                    <a class="btn back-button us-prof-btn" href="<c:url value="/controller?command=logout"/>"><fmt:message key="profile.button.logout"/></a>
                    <c:if test="${user.lang.toString() != 'rus'}">
                        <a class="btn back-button us-prof-btn" href="<c:url value="/controller?command=change_lang&lang=rus"/>"><fmt:message key="profile.button.en-rus"/></a>
                    </c:if>
                    <c:if test="${user.lang.toString() eq 'rus'}">
                        <a class="btn back-button us-prof-btn" href="<c:url value="/controller?command=change_lang&lang=eng"/>"><fmt:message key="profile.button.en-eng"/></a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
