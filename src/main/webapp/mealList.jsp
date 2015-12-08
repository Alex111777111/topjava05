<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 04.12.2015
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Список еды</title>
</head>
<body>
<h1> Список еды</h1>

<div>
    <form id="filter" name="filter" action="MealServlet"
          method="get">
        <p>
            <label for="fromDate">From Date:</label>
            <input type="text" name="fromDate" id="fromDate">
            <label for="toDate">To Date:</label>
            <input type="text" name="toDate" id="toDate">
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
    <form id="addMealList" action="mealEdit" method="get">
        <%-- <INPUT name="addMeal" TYPE="hidden" disabled="disabled" id = "addMeal" form="addMealList" value="*" method="GET" >
     --%> <p>
        <input class="button" type="submit" value="Добавить еду">
    </p>
    </form>

    <p>
        <label for="textfield3">Search:</label>
        <input type="text" name="textfield3" id="textfield3">
    </p>

</div>
<hr/>
<div>
    <form id="edit" action="mealEdit" method="POST">
        <table width="781" border="1">
            <tbody>
            <tr>
                <td width="167"><b>Date</b></td>
                <td width="239"><b>Description</b></td>
                <td width="185"><b>Calories</b></td>
                <td width="70"></td>
                <td width="86"></td>
            </tr>
            <c:forEach items="${list}" var="meal">
                <c:set var="color" scope="session" value=""/>
                <c:choose>
                    <c:when test="${meal.getExceed()}">
                        <c:set var="color" scope="session" value="#E90234"/>
                    </c:when>
                    <c:when test="${!meal.getExceed()}">
                        <c:set var="color" scope="session" value="#06B836"/>
                    </c:when>
                </c:choose>

                <tr style="color: ${color}">
                        <%--  <fmt:parseDate value="${meal.dateTime}" pattern="y-M-dd'T'H:m" var="parsedDateTime"/>
                          <fmt:formatDate value="${parsedDateTime}" pattern="yyyy.MM.dd HH:mm"/>--%>
                    <c:url var="editUrl" value="/mealEdit?action=edit&id=${meal.id}"/>
                    <c:url var="deleteUrl" value="/mealEdit?action=delete&id=${meal.id}"/>
                        <%--<td><c:out value="${parsedDateTime}"/></td>--%>
                    <td><c:out value="${meal.dateTime}"/></td>
                    <td><c:out value="${meal.description}"/></td>
                    <td><c:out value="${meal.calories}"/></td>
                    <td><a href="${editUrl}">Edit</a></td>
                    <td><a href="${deleteUrl}">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>


</body>
</html>