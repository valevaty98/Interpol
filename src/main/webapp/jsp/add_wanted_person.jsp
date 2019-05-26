<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<c:if test="${user.lang.toString() eq 'rus'}">
    <fmt:setLocale value="ru_RU"/>
</c:if>
<fmt:setBundle basename="props.pagescontent"/>

<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="add-person.title"/></title>
    <link rel="shortcut icon" href="<c:url value="/static/images/interpol-logo.png"/>" type="imageEncoded/png"/>
    <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css"
          type="text/css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="/static/css/input-pages-style.css"/>" type="text/css">
</head>

<body>
<div class="form">
    <h1><fmt:message key="add-person.label.head"/></h1>
    <p>${edit_email_error}</p>
    <form action="<c:url value="/uploadServlet"/>" method="post" enctype="multipart/form-data">
        <input type="hidden" name="command" value="add_wanted_person">
        <div class="field-wrap">
            <label>
                <fmt:message key="add-person.label.name"/><span class="req">*</span>
            </label>
            <input type="text" name="person_name" pattern="^[a-zA-Z ,.'-]+$" required autocomplete="off"/>
        </div>
        <div class="field-wrap">
            <label>
                <fmt:message key="add-person.label.surname"/>
            </label>
            <input type="text" name="person_surname" pattern="^[a-zA-Z ,.'-]+$" autocomplete="off"/>
        </div>
        <div class="field-wrap" style="height: 45px;">
            <label class="active highlight"><fmt:message key="add-person.label.gender"/></label>
            <label class="gender-label" for="male"><input type="radio" id="male" class="gender-type" name="gender" value="Male" checked><fmt:message key="add-person.label.gender.male"/></label>
            <label class="gender-label" for="female"><input type="radio" id="female" class="gender-type" name="gender" value="Female"><fmt:message key="add-person.label.gender.female"/></label>
        </div>
        <div class="field-wrap">
            <label>
                <fmt:message key="add-person.label.characteristics"/>
            </label>
            <textarea name="characteristics" rows="3" autocomplete="off"></textarea>
        </div>
        <div class="field-wrap">
            <label>
                <fmt:message key="add-person.label.height"/>
            </label>
            <input type="text" name="height" autocomplete="off" pattern="[\d]+[.]?[\d]{0,3}$"/>
        </div>
        <div class="field-wrap">
            <label>
                <fmt:message key="add-person.label.weight"/>
            </label>
            <input type="text" name="weight" autocomplete="off" pattern="^[\d]+[.]?[\d]{0,3}$"/>
        </div>
        <div class="field-wrap">
            <label>
                <fmt:message key="add-person.label.charges"/><span class="req">*</span>
            </label>
            <textarea name="charges" rows="2" required autocomplete="off"></textarea>
        </div>
        <div class="field-wrap">
            <label>
                <fmt:message key="add-person.label.birth-place"/><span class="req">*</span>
            </label>
            <input type="text" name="birth_place" required autocomplete="off"/>
        </div>
        <div class="field-wrap">
            <label>
                <fmt:message key="add-person.label.nationalities"/><span class="req">*</span>
            </label>
            <input type="text" name="nationalities" required autocomplete="off"
            pattern="^[a-zA-z -]+[,]?[a-zA-Z -]+$"/>
        </div>
        <div class="field-wrap">
            <label class="active highlight">
                <fmt:message key="add-person.label.birth-date"/><span class="req">*</span>
            </label>
            <input type="date" name="birth_date"
                   min="1900-01-01" max="2018-12-31" required autocomplete="off"/>
        </div>
        <div class="field-wrap">
            <label class="active highlight">
                <fmt:message key="add-person.label.photo"/><span class="req">*</span>
            </label>
            <input type="file" name="image" required autocomplete="off" accept="image/png"/>
        </div>
        <div>
            <button type="submit" class="button button-block send-button"><fmt:message key="add-person.button.add"/></button>
            <button type="button" class="button button-block back-button" onclick="history.back()"><fmt:message key="add-person.button.back"/></button>
        </div>
    </form>
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="<c:url value="/static/js/input-page-index.js"/>"></script>
</body>

</html>