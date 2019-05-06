<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Wanted People</title>
</head>
<body>
<div id="wanted-people-tab">
    <div class="wanted-persons">
        <ul>
            <c:forEach var="person" items="${wantedPeople}">
                <li>
                    <a href="../controller?command=show_full_person&person_id=<c:out value="${person.id}"/>">
                        <img src="data:image/png;base64,<c:out value="${person.image}"/>" alt="<c:out value="${person.surname}"/>"/>
                        <div class="person-info">
                            <h4><c:out value="${person.name}"/> <c:out value="${person.surname}"/></h4>
                            <div class="person-desc">
                                <p><c:out value="${person.birthDate}"/></p>
                                <strong class="nationality-info">
                                    <c:set var="nationality_str" value="${person.nationality}" />
                                    <c:forTokens var="nationality_token" items="${nationality_str}" delims="[]">
                                        <c:out value="${nationality_token}"/>
                                    </c:forTokens>
                                </strong>
                            </div>
                        </div>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div id="sidebar">
        <c:if test="${user.role eq 'ADMIN'}">
            <div>
                <a class="btn admin-main-btn" style="margin-bottom: 10px;"
                   href="<c:url value="/jsp/add_wanted_person.jsp"/>">Add Wanted Person</a>
            </div>
            <div>
                <a class="btn admin-main-btn" style="margin-bottom: 10px;"
                   href="<c:url value="/controller?command=show_all_messages"/>">Show all messages</a>
            </div>
        </c:if>
        <div class="box search">
            <h2>Filter</h2>
            <div class="box-content">
                <form action="../controller" method="post">
                    <input type="hidden" name="command" value="search">
                    <div class="form-group">
                        <label for="person_name" class="filter-label">Name:</label>
                        <input type="text" class="form-control form-control-sm" id="person_name"
                               name="wanted_person_name" value="<c:out value="${personName}"/>">
                    </div>
                    <div class="form-group">
                        <label for="person_surname" class="filter-label">Surname:</label>
                        <input type="text" class="form-control form-control-sm" id="person_surname"
                               name="wanted_person_surname" value="${personSurname}">
                    </div>
                    <div class="gender-group">
                        <label class="filter-label">Gender:</label>
                        <c:choose>
                            <c:when test="${personGender eq 'male'}">
                                <label class="radio-inline male-label"><input type="radio" name="gender"
                                                                              value="male" checked>Male</label>
                                <label class="radio-inline"><input type="radio" name="gender" value="female">Female</label>
                                <label class="radio-inline"><input type="radio" name="gender" value="unknown">Unknown</label>
                            </c:when>
                            <c:when test="${personGender eq 'female'}">
                                <label class="radio-inline male-label"><input type="radio" name="gender"
                                                                              value="male">Male</label>
                                <label class="radio-inline"><input type="radio" name="gender" value="female" checked>Female</label>
                                <label class="radio-inline"><input type="radio" name="gender" value="unknown">Unknown</label>
                            </c:when>
                            <c:otherwise>
                                <label class="radio-inline male-label"><input type="radio" name="gender"
                                                                              value="male">Male</label>
                                <label class="radio-inline"><input type="radio" name="gender" value="female">Female</label>
                                <label class="radio-inline"><input type="radio" name="gender" value="unknown"
                                                                   checked>Unknown</label>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="form-group">
                        <label class="filter-label">Age:</label>
                        <p>From:
                            <input class="form-control form-control-lg" pattern="^[1-9]\d{0,2}$"
                                   type="text" name="fromAge" value="${fromAge}" maxlength="3"/>
                            To:
                            <input class="form-control form-control-sm" pattern="^[1-9]\d{0,2}$"
                                   type="text" name="toAge" value="${toAge}" maxlength="3"/>
                        </p>
                    </div>
                    <div class="form-group">
                        <label for="nationality-select" class="filter-label">Nationality:</label>
                        <select class="form-control" id="nationality-select" name="nation">
                            <option>${nation}</option>
                            <c:if test="${not empty nation}">
                                <option></option>
                            </c:if>
                            <c:forEach var="nationVar" items="${nationalities}">
                                <option value="<c:out value="${nationVar}"/>">${nationVar}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="search-submit">Search</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
