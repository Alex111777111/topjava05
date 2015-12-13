<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .exceeded {
            color: red;
        }
    </style>
</head>
<body>
<section>
    <h2><a href="index.html">Home</a></h2>
    <h3>Meal list</h3>
    <c:set var="userId" value="${userId}"/>

    <div>


        <form id="filter" name="filter" action="meals"
              method="post">
            <p>
                <label for="fromDate">From Date:</label>
                <input type="text" name="fromDate" id="fromDate">
                <label for="toDate">To Date:</label>
                <input type="text" name="toDate" id="toDate">
                <%--   <input type="hidden" name="userId" id="idUser" value="${userId}">--%>

            </p>

            <p>
                <label for="fromTime">From Time:</label>
                <input type="text" name="fromTime" id="fromTime">
                <label for="toTime">To Time:</label>
                <input type="text" name="toTime" id="toTime">
            </p>

            <p>
                <input class="button" type="submit" value="Filter">
            </p>
        </form>
    </div>
    <div>

        <p>

            <a href="meals?action=create">Add Meal</a></p>
    <hr>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${mealList}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.UserMealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <td>
                        <%--<fmt:parseDate value="${meal.dateTime}" pattern="y-M-dd'T'H:m" var="parsedDate"/>--%>
                        <%--<fmt:formatDate value="${parsedDate}" pattern="yyyy.MM.dd HH:mm" />--%>
                    <%=TimeUtil.toString(meal.getDateTime())%>
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    </div>
</section>
</body>
</html>