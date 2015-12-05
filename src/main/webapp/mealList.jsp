<%--
  Created by IntelliJ IDEA.
  User: B
  Date: 04.12.2015
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="ru.javawebinar.topjava.web.MealServlet"%>
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
            <label for="textfield">From Date:</label>
            <input type="text" name="fromDate" id="fromDate">
            <label for="textfield">To Date:</label>
            <input type="text" name="toDate" id="toDate">
        </p>
        <p>
            <label for="textfield">Text Field:</label>
            <input type="text" name="textfield" id="textfield">
            <label for="textfield2">Text Field:</label>
            <input type="text" name="textfield2" id="textfield2">
        </p>
        <p>
            <input type="button" name="button2" id="button2" value="Filter">
        </p>
    </form>
</div>

<div>
    <form id="mealList" action="mealEdit"  method="get">
  <%--    <input type="text" --%>
        <%--<INPUT TYPE="hidden" id = "addMeal" disabled="disabled" form="mealList" action="MealEditServlet"  method="GET" >
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
            <tr>
                <c:set var="color" value=""/>

                <c:if test="${meal.getExceed()}">
                <p>color = " style="color: #DB072F"" <p>
                </c:if>
                <c:if test="${!meal.getExceed()}">
                <p>color = " style="color: #06B836""<p>
                </c:if>

                <td style="color: ${color}"><c:out value="${meal.dateTime}"/></td>
                <td ${color}><c:out value="${meal.description}"/></td>
                <td ${color}><c:out value="${meal.calories}"/></td>
                <td ${color}></td>
                <td ${color}></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

</body>
</html>