<div class="view-box">
    <a class="btn btn-sm btn-info" id="add"><fmt:message key="users.add"/></a>
    <input type="checkbox"
           <c:if test="${user.enabled}">checked</c:if> id="${user.id}"/>
    <td><a class="btn btn-xs btn-primary edit" id="${user.id}">Edit</a></td>
    <td><a class="btn btn-xs btn-danger delete" id="${user.id}">Delete</a></td>
</div>
<jsp:include page="fragments/footer.jsp"/>

<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"><fmt:message key="users.edit"/></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" id="detailsForm">
                    <input type="text" hidden="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3">Name</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="name" name="name" placeholder="Name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="email" class="control-label col-xs-3">Email</label>

                        <div class="col-xs-9">
                            <input type="email" class="form-control" id="email" name="email" placeholder="email">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="password" class="control-label col-xs-3">Password</label>

                        <div class="col-xs-9">
                            <input type="password" class="form-control" id="password" name="password" placeholder="">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="submit" class="btn btn-primary">Save</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="webjars/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/datetimepicker/2.3.4/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.9/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.2.4/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="resources/js/datatablesUtil.js"></script>
<script type="text/javascript">

    var ajaxUrl = 'ajax/admin/users/';
    var oTable_datatable;
    var oTable_datatable_params;

    //            $(document).ready(function () {
    $(function () {
        oTable_datatable = $('#datatable');
        oTable_datatable_params = {
            "bPaginate": false,
            "bInfo": false,
            "aoColumns": [
                {
                    "mData": "name"
                },
                {
                    "mData": "email"
                },
                {
                    "mData": "roles"
                },
                {
                    "mData": "enabled"
                },
                {
                    "mData": "registered"
                },
                {
                    "sDefaultContent": "",
                    "bSortable": false
                },
                {
                    "sDefaultContent": "",
                    "bSortable": false
                }
            ],
            "aaSorting": [
                [
                    0,
                    "asc"
                ]
            ]
        };

        oTable_datatable.dataTable(oTable_datatable_params);
        makeEditable();
    });
</script>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<link rel="stylesheet" href="webjars/datatables/1.10.9/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="webjars/datetimepicker/2.3.4/jquery.datetimepicker.css">

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<table class="table table-striped display" id="datatable">
    <div class="jumbotron">
        <div class="container">
            <div class="shadow">
                <h3><fmt:message key="users.title"/></h3>

                <div class="view-box">
                    <a class="btn btn-sm btn-info"><fmt:message key="users.add"/></a>

                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Roles</th>
                            <th>Active</th>
                            <th>Registered</th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <c:forEach items="${userList}" var="user">
                            <jsp:useBean id="user" scope="page" type="ru.javawebinar.topjava.model.User"/>
                            <tr>
                                <td><c:out value="${user.name}"/></td>
                                <td><a href="mailto:${user.email}">${user.email}</a></td>
                                <td>${user.roles}</td>
                                <td>
                                    <input type="checkbox"
                                           <c:if test="${user.enabled}">checked</c:if>/>
                                </td>
                                <td><fmt:formatDate value="${user.registered}" pattern="dd-MMMM-yyyy"/></td>
                                <td><a class="btn btn-xs btn-primary">Edit</a></td>
                                <td><a class="btn btn-xs btn-danger">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
<jsp:include page="fragments/footer.jsp"/>
</body>
<script type="text/javascript" src="webjars/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/datetimepicker/2.3.4/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.9/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.2.4/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="resources/js/datatablesUtil.js"></script>
<script type="text/javascript">

    var ajaxUrl = 'ajax/admin/users/';
    var oTable_datatable;
    var oTable_datatable_params;

    //            $(document).ready(function () {
    $(function () {
        oTable_datatable = $('#datatable');
        oTable_datatable_params = {
            "bPaginate": false,
            "bInfo": false,
            "aoColumns": [
                {
                    "mData": "name"
                },
                {
                    "mData": "email"
                },
                {
                    "mData": "roles"
                },
                {
                    "mData": "enabled"
                },
                {
                    "mData": "registered"
                },
                {
                    "sDefaultContent": "",
                    "bSortable": false
                },
                {
                    "sDefaultContent": "",
                    "bSortable": false
                }
            ],
            "aaSorting": [
                [
                    0,
                    "asc"
                ]
            ]
        };

        oTable_datatable.dataTable(oTable_datatable_params);
        makeEditable();
    });
</script>
</html>
