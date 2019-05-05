<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
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
</body>
</html>
