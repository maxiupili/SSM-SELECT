<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>主页</title>
    <%@include file="common/head.jsp" %>
    <link href="<%=basePath%>bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">

</head>
<body>
<div class="container-fluid">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>选课情况</h2>
        </div>
        <div class="panel-body">
            <table class="table table-hover" id="enrollments_table">
                <thead>
                <tr>
                    <th>姓名</th>
                    <th>所选项目</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="<%=basePath%>js/jquery-3.2.1.min.js"></script>
<script src="<%=basePath%>bootstrap/js/bootstrap.min.js"></script>
<script>
    $(function () {
        $.ajax({
            url: "/select/enrollments",
            type: "GET",
            success: function (result) {
                console.log(result);
                build_project_table(result.data);
            }
        });
    });

    function build_project_table(data) {
        $.each(data, function (index, item) {
            var projectNameTd = $("<td></td>").append(item.project.projectName);
            var realNameTd = $("<td></td>").append(item.user.realname);
            $("<tr></tr>").append(realNameTd)
                .append(projectNameTd)
                .appendTo("#enrollments_table tbody");
        })
    }


</script>
</body>
</html>