<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 03.12.2015
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Редактировать еду</title>
</head>

<body>
<h1>Редактировать еду</h1>
<hr/>
<p></p>
<tbody>
<c:set var="ume" value="${uMeal}"/>
<form id="mealEdit" action="mealEdit" method="POST">
    <table width="408" border="0">

        <tr>
            <td width="156"><strong>Date</strong></td>
            <td width="242"><input name="date" type="datetime-local" id="date" value="${ume.dateTime}"></td>
        </tr>
        <tr>
            <td><strong>Description</strong></td>
            <td><input type="text" name="description" id="description" value="${ume.description}"></td>
        </tr>
        <tr>
            <td><strong>Calories</strong></td>
            <td><input type="text" name="calories" id="calories" value="${ume.calories}"></td>
        </tr>
        <tr>
            <td></td>
            <td><INPUT name="id" TYPE="hidden" <%--disabled="disabled"--%> id="id" value="${ume.id}"></td>
        </tr>
        <tr>
            <td></td>
            <td><h3>${message}</h3></td>
        </tr>


    </table>
    <p>
        <input class="button" type="submit" value="Save">
    </p>
</form>
</tbody>
</body>
</html>
