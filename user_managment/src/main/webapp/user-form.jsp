<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>User Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: blue">
        <div>
            <a href=<%=request.getContextPath()%>/list" class="navbar-brand"> User Management Application </a>
        </div>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${user != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${user == null}">
                <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${user != null}">
                                Edit User
                            </c:if>
                            <c:if test="${user == null}">
                                Add New User
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${user != null}">
                        <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
                    </c:if>

                        <fieldset class="form-group">
                            <label>Fist name</label> <input type="text"
                                                            value="<c:out value='${user.firstName}' />" class="form-control"
                                                            name="firstName" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Last name</label> <input type="text"
                                                            value="<c:out value='${user.lastName}' />" class="form-control"
                                                            name="lastName" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Birthdate</label> <input type="date"
                                                            value="<c:out value='${user.birthdate}' />" class="form-control"
                                                            name="birthdate" required="required" max='${today}'>
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Phone number</label> <input type="tel"
                                                            value="<c:out value='${user.phoneNumber}' />" class="form-control"
                                                            name="phoneNumber" required="required" pattern='${initParam['phonePattern']}'
                                                               title='${initParam['phoneTitle']}'>
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Email address</label> <input type="email"
                                                             value="<c:out value='${user.email}' />" class="form-control"
                                                             name="email", pattern='${initParam['emailPattern']}' title='${initParam['emailTitle']}'>
                        </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>