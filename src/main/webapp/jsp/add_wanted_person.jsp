<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Wanted Person</title>
    <link rel="shortcut icon" href="<c:url value="/jsp/images/interpol-logo.png"/>" type="image/png"/>
    <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css"
          type="text/css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="/jsp/css/loginiki.css"/>" type="text/css">
</head>

<body>
<div class="form">
    <h1>Add Wanted Person</h1>
    <p>${edit_email_error}</p>
    <form action="<c:url value="controller"/>" method="post" enctype="multipart/form-data">
        <input type="hidden" name="command" value="add_wanted_person">
        <div class="field-wrap">
            <label>
                Name<span class="req">*</span>
            </label>
            <input type="text" name="person_name" required autocomplete="off"/>
        </div>
        <div class="field-wrap">
            <label>
                Surname
            </label>
            <input type="text" name="person_surname" required autocomplete="off"/>
        </div>
        <div class="field-wrap" style="height: 45px;">
            <label class="active highlight">Gender</label>
            <label class="gender-label" for="male"><input type="radio" id="male" class="gender-type" name="gender" value="Male" checked>Male</label>
            <label class="gender-label" for="female"><input type="radio" id="female" class="gender-type" name="gender" value="Female">Female</label>
        </div>
        <div class="field-wrap">
            <label>
                Characteristics
            </label>
            <textarea name="characteristics" rows="3" autocomplete="off"></textarea>
        </div>
        <div class="field-wrap">
            <label>
                Height
            </label>
            <input type="text" name="height" autocomplete="off" pattern="^[\d]+[.]?\d]{1,3}$"/>
        </div>
        <div class="field-wrap">
            <label>
                Weight
            </label>
            <input type="text" name="weight" autocomplete="off" pattern="^[\d]+[.]?\d]{1,3}$"/>
        </div>
        <div class="field-wrap">
            <label>
                Charges<span class="req">*</span>
            </label>
            <textarea name="charges" rows="2" autocomplete="off"></textarea>
        </div>
        <div class="field-wrap">
            <label>
                Place Of Birth<span class="req">*</span>
            </label>
            <input type="text" name="birth_place" required autocomplete="off"/>
        </div>
        <div class="field-wrap">
            <label class="active highlight">
                Date of Birth<span class="req">*</span>
            </label>
            <input type="date" name="birth_date"
                   min="1900-01-01" max="2018-12-31" required autocomplete="off"/>
        </div>
        <div class="field-wrap">
            <label class="active highlight">
                Photo<span class="req">*</span>
            </label>
            <input type="file" name="image" required autocomplete="off" accept="image/png"/>
        </div>
        <div>
            <button type="submit" class="button button-block send-button">Save</button>
            <button type="button" class="button button-block back-button" onclick="history.back()">Back</button>
        </div>
    </form>
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="<c:url value="/jsp/js/loginnn.js"/>"></script>
</body>

</html>