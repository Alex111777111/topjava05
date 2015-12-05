<%--
  Created by IntelliJ IDEA.
  User: B
  Date: 03.12.2015
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Редактировать еду</title>
</head>

<body>
<h1>Редактировать еду</h1>
<hr/>
<p></p>
<table width="408" border="0">
    <tbody>
    <form id="mealEdit" name="mealEdit" action="mealList"
          method="POST">
        <tr>
            <td width="156"><strong>Date</strong></td>
            <td width="242"><input name="datetime-local" type="datetime-local" id="datetime-local"></td>
        </tr>
        <tr>
            <td><strong>Description</strong></td>
            <td><input type="text" name="description" id="description"></td>
        </tr>
        <tr>
            <td><strong>Calories</strong></td>
            <td><input type="text" name="calories" id="calories"></td>
        </tr>
    </form>
    </tbody>
</table>
<p>
    <input type="button" name="button" id="button" value="Save">
</p>
</body>
</html>
