<%--
  Created by IntelliJ IDEA.
  User: bukbuk
  Date: 2017/12/7
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%--<%@ include file="common/tag.jsp" %>--%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>项目列表</title>
    <%@include file="common/head.jsp" %>
</head>
<body>

<div class="modal fade" id="projectInfoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel1">项目信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="projectName_info_static" class="col-sm-2 control-label">项目名称</label>
                        <div class="col-sm-10">
                            <p class="form-control-static" id="projectName_info_static"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="stock_info_static" class="col-sm-2 control-label">库存</label>
                        <div class="col-sm-10">
                            <p class="form-control-static" id="stock_info_static"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="projectDescription_info_static" class="col-sm-2 control-label">项目描述</label>
                        <div class="col-sm-10">
                            <p class="form-control-static" id="projectDescription_info_static"></p>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid">

    <div class="panel panel-default">
        <div class="panel-heading text-center">
        <h2>项目列表</h2>
    </div>
        <div class="panel-body">
            <table class="table table-hover" id="projects_table">
                <thead>
                <tr>
                    <th>项目名称</th>
                    <th>库存</th>
                    <th>项目描述</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>

    </div>
    <div id="toolbar">
        <button id="btn_info" type="button" class="btn btn-default">
            查看已选课程
        </button>
    </div>

</div>
<script src="<%=basePath%>js/jquery-3.2.1.min.js"></script>
<script src="<%=basePath%>bootstrap/js/bootstrap.min.js"></script>
<script>
    $(function(){
        $.ajax({
            url:"/select/projects",
            type:"GET",
            success:function (result) {
                    console.log(result);
                    build_project_table(result);
            }
        });
    });
    function build_project_table(result){
        $.each(result,function (index,item) {
            var projectNameTd = $("<td></td>").append(item.projectName);
            var stockTd=$("<td></td>").append(item.stock);
            var projectDescriptionTd = $("<td></td>").append(item.projectDescription);
            var selectBtn=$("<button></button>")
                .addClass("btn btn-primary select_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-plus"))
                .append("选择");
            selectBtn.attr("select-id",item.projectId);
            var btnTd = $("<td></td>").append(selectBtn);
           $("<tr></tr>").append(projectNameTd)
               .append(stockTd)
               .append(projectDescriptionTd)
               .append(btnTd)
               .appendTo("#projects_table tbody");
        })
    }
    $(document).on("click",".select_btn",function () {
        if(confirm("确认选择此项目吗？")){
        /*$.ajax({
            url:"/select/enrollment",
            type:"POST",
            data:{
                _method:"DELETE"
            }
        });*/
        $.ajax({
            url:"/select/enrollment/"+$(this).attr("select-id"),
            type:"POST",
            success:function () {
                alert("选题成功");
                window.location.reload();
            }
        });
    }
    })
    $("#btn_info").click(function () {
        $.ajax({
            url:"/select/enrollment",
            type:"GET",
            success:function (result) {
               // console.log(result);
                var project = result.data;
                $("#projectName_info_static").text(project.projectName);
                $("#stock_info_static").text(project.stock);
                $("#projectDescription_info_static").text(project.projectDescription);
            }
        });
        $("#projectInfoModal").modal({
            backdrop:"static"
        })
    })
</script>
</body>
</html>
