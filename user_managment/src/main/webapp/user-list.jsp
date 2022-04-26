<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
            <a href="http://localhost:8080/user_managment_war_exploded/" class="navbar-brand"> User
                Management Application </a>
        </div>
    </nav>
</header>
<br>

<div class="row">

    <div class="container">
        <h3 class="text-center">List of Users</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/new" style="margin-right: 20cm;" class="btn btn-success">Add
                New User</a> <input id='myInput' onkeyup='searchTable()' type='text' placeholder="Type to search">
        </div>
        <br>
        <table class="table table-bordered" id="myTable">
            <thead>
            <tr>
                <th>First name</th>
                <th onclick="sortTable(0)">Last name</th>
                <th onclick="sortTable(1)">Birthdate</th>
                <th>Phone number</th>
                <th>Email</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="user" items="${listUser}">

                <tr>
                    <td><c:out value="${user.firstName}" /></td>
                    <td><c:out value="${user.lastName}" /></td>
                    <td><c:out value="${user.birthdate}" /></td>
                    <td><c:out value="${user.phoneNumber}" /></td>
                    <td><c:out value="${user.email}" /></td>
                    <td><a href="edit?id=<c:out value='${user.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                                href="delete?id=<c:out value='${user.id}' />">Delete</a></td>
                </tr>
            </c:forEach>

            </tbody>

        </table>

        <script>
            function searchTable() {
                let input, filter, found, table, tr, td, i, j;
                input = document.getElementById("myInput");
                filter = input.value.toUpperCase();
                table = document.getElementById("myTable");
                tr = table.getElementsByTagName("tr");
                for (i = 1; i < tr.length; i++) {
                    td = tr[i].getElementsByTagName("td");
                    for (j = 0; j < td.length; j++) {
                        if (td[j].innerHTML.toUpperCase().indexOf(filter) > -1) {
                            found = true;
                        }
                    }
                    if (found) {
                        tr[i].style.display = "";
                        found = false;
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }

            function sortTable(n) {
                let table, rows, switching, i, x, y, shouldSwitch, dir, switchCount = 0, isDate;
                table = document.getElementById("myTable");
                switching = true;
                // Set the sorting direction to ascending:
                dir = "asc";
                /* Make a loop that will continue until
                no switching has been done: */
                isDate = n == 1;
                while (switching) {
                    // Start by saying: no switching is done:
                    switching = false;
                    rows = table.rows;
                    /* Loop through all table rows (except the
                    first, which contains table headers): */
                    for (i = 1; i < (rows.length - 1); i++) {
                        // Start by saying there should be no switching:
                        shouldSwitch = false;
                        /* Get the two elements you want to compare,
                        one from current row and one from the next: */
                        x = rows[i].getElementsByTagName("TD")[n];
                        y = rows[i + 1].getElementsByTagName("TD")[n];
                        /* Check if the two rows should switch place,
                        based on the direction, asc or desc: */
                        if (isDate) {
                            if (dir == "asc") {
                                if (Date.parse(x.innerHTML) > Date.parse(y.innerHTML)) {
                                    console.log("x value is: " + Date.parse(x.innerHTML))
                                    console.log("y's value is: " + Date.parse(y.innerHTML))
                                    // If so, mark as a switch and break the loop:
                                    shouldSwitch = true;
                                    break;
                                }
                            } else if (dir == "desc") {
                                if (Date.parse(x.innerHTML) < Date.parse(y.innerHTML)) {
                                    // If so, mark as a switch and break the loop:
                                    shouldSwitch = true;
                                    break;
                                }
                            }
                        } else {
                            if (dir == "asc") {
                                if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                                    // If so, mark as a switch and break the loop:
                                    shouldSwitch = true;
                                    break;
                                }
                            } else if (dir == "desc") {
                                if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                                    // If so, mark as a switch and break the loop:
                                    shouldSwitch = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (shouldSwitch) {
                        /* If a switch has been marked, make the switch
                        and mark that a switch has been done: */
                        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                        switching = true;
                        // Each time a switch is done, increase this count by 1:
                        switchCount ++;
                    } else {
                        /* If no switching has been done AND the direction is "asc",
                        set the direction to "desc" and run the while loop again. */
                        if (switchCount == 0 && dir == "asc") {
                            dir = "desc";
                            switching = true;
                        }
                    }
                }
            }
        </script>
    </div>
</div>
</body>
</html>