<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <link rel="stylesheet" href="webjars/datatables/1.10.9/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="webjars/datetimepicker/2.3.4/jquery.datetimepicker.css">
    <%--http://stackoverflow.com/questions/10327390/how-should-i-get-root-folder-path-in-jsp-page--%>
    <h3><a href="${pageContext.request.contextPath}">Home</a></h3>
    <div class="jumbotron">
        <div class="container">
            <div class="shadow">
                <h3><fmt:message key="meals.title"/></h3>
                <div class="view-box">
                    <form method="post" action="meals/filter">
                        <div>
                            <table cellspacing="150">
                                <tr>
                                    <dl>
                                        <td>
                                            <dt>From Date:</dt>
                                        </td>
                                        <td>
                                            <dd><input type="date" name="startDate" value="${startDate}"></dd>

                                        <td>
                                            <dt align="center">To Date:</dt>
                                        </td>
                                        <td>
                                            <dd><input type="date" name="endDate" value="${endDate}"></dd>
                                    </dl>
                                    </td>

                                </tr>
                                <tr>
                                    <td>
                                        <p></p>
                                    </td>
                                </tr>


                                <tr>
                                    <td>
                                        <dt>From Time:</dt>
                                    </td>
                                    <td>
                                        <dd><input type="time" name="startTime" value="${startTime}"></dd>
                                    </td>

                                    <td align="center">
                                        <dt>To Time:</dt>
                                    </td>
                                    <td>
                                        <dd><input type="time" name="endTime" value="${endTime}"></dd>
                                        </dl>
                                    </td>
                                </tr>

                                <td>
                                    <p></p>
                                    <button type="submit" a class="btn btn-xs btn-primary">Filter</button>
                                </td>

                            </table>
                        </div>
                    </form>
                    <hr>

                    <a class="btn btn-sm btn-info" href="meals/create">Add Meal</a>

                    <hr>
                    <table class="table table-striped display" id="datatable">

                        <h3><fmt:message key="meals.title"/></h3>


                        <table class="table table-striped">
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
                                <jsp:useBean id="meal" scope="page"
                                             type="ru.javawebinar.topjava.to.UserMealWithExceed"/>
                                <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                                    <td>
                                            <%--<fmt:parseDate value="${meal.dateTime}" pattern="y-M-dd'T'H:m" var="parsedDate"/>--%>
                                            <%--<fmt:formatDate value="${parsedDate}" pattern="yyyy.MM.dd HH:mm" />--%>
                                        <%=TimeUtil.toString(meal.getDateTime())%>
                                    </td>
                                    <td>${meal.description}</td>
                                    <td>${meal.calories}</td>
                                    <td><a class="btn btn-xs btn-primary" href="meals/update?id=${meal.id}">Update</a>
                                    </td>
                                    <td><a class="btn btn-xs btn-danger" href="meals/delete?id=${meal.id}">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </table>
                </div>
            </div>
        </div>


</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
