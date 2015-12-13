<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h3>User list</h3>
<form action="users" id="userId" method="post">
    <select name="userId">
        <option selected="selected" value="1">Admin</option>
        <option value="2">User</option>
    </select>
    <p>
        <input type="submit" value="Select">
        <%--   <a href="meals?action=changeUser&userId=">Select</a></p>--%>
    </p>
</form>
</body>
</html>
