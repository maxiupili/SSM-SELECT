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
<!-- 新增项目模态框-->
<div class="modal fade" id="projectAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">项目添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="projectName_add_input" class="col-sm-2 control-label">项目名称</label>
                        <div class="col-sm-10">
                            <input type="text" name="projectName" class="form-control" id="projectName_add_input"
                                   placeholder="项目名称">
                            <span class="help-block"></span>

                        </div>
                    </div>
                    <div class="form-group">
                        <label for="stock_add_input" class="col-sm-2 control-label">库存</label>
                        <div class="col-sm-10">
                            <input type="text" name="stock" class="form-control" id="stock_add_input" placeholder="库存">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="projectDescription_add_input" class="col-sm-2 control-label">项目描述</label>
                        <div class="col-sm-10">
                            <textarea name="projectDescription" class="form-control" rows="3"
                                      id="projectDescription_add_input" placeholder="项目描述"></textarea>
                            <%--<input type="text" name="projectDescription" class="form-control" id="projectDescription_add_input" placeholder="项目描述">--%>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="project_save_btn">保存</button>
            </div>
        </div>
    </div>
</div>
<!--编辑项目模态框-->
<div class="modal fade" id="projectEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel1">项目编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="projectName_add_input" class="col-sm-2 control-label">项目名称</label>
                        <div class="col-sm-10">
                            <p class="form-control-static" id="projectName_edit_static"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="stock_add_input" class="col-sm-2 control-label">库存</label>
                        <div class="col-sm-10">
                            <input type="text" name="stock" class="form-control" id="stock_edit_input" placeholder="库存">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="projectDescription_add_input" class="col-sm-2 control-label">项目描述</label>
                        <div class="col-sm-10">
                            <textarea name="projectDescription" class="form-control" rows="3"
                                      id="projectDescription_edit_input" placeholder="项目描述"></textarea>
                            <%--<input type="text" name="projectDescription" class="form-control" id="projectDescription_add_input" placeholder="项目描述">--%>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="project_update_btn">更新</button>
            </div>
        </div>
    </div>
</div>
<!---->
<div class="container-fluid">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>项目列表</h2>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="clo-md-1 col-md-offset-11">
                    <button class="btn btn-primary" id="project_add_modal_btn">新增</button>
                </div>
            </div>
            <div class="row">
                <table class="table table-hover" id="projects_table">
                    <thead>
                    <tr>
                        <th>#</th>
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
    </div>
    <%--<div id="toolbar">
        <button id="btn_add" type="button" class="btn btn-default">
            查看已选课程
        </button>
    </div>--%>
</div>
<script src="<%=basePath%>js/jquery-3.2.1.min.js"></script>
<script src="<%=basePath%>bootstrap/js/bootstrap.min.js"></script>
<script>
    $(function () {
        $.ajax({
            url: "/select/projects",
            type: "GET",
            success: function (result) {
                console.log(result);
                build_project_table(result);
            }
        });
    });

    function build_project_table(result) {
        $.each(result, function (index, item) {
            var projectIdTd = $("<td></td>").append(item.projectId);
            var projectNameTd = $("<td></td>").append(item.projectName);
            var stockTd = $("<td></td>").append(item.stock);
            var projectDescriptionTd = $("<td></td>").append(item.projectDescription);
            var editBtn = $("<button></button>")
                .addClass("btn btn-primary edit_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-pencil"))
                .append("编辑");
            editBtn.attr("edit_id", item.projectId);
            var deleteBtn = $("<button></button>")
                .addClass("btn btn-danger delete_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-trash"))
                .append("删除");
            deleteBtn.attr("delete_id", item.projectId);
            var btnTd = $("<td></td>").append(editBtn).append(" ").append(deleteBtn);
            $("<tr></tr>").append(projectIdTd)
                .append(projectNameTd)
                .append(stockTd)
                .append(projectDescriptionTd)
                .append(btnTd)
                .appendTo("#projects_table tbody");
        })
    }

    $("#project_add_modal_btn").click(function () {
        //$("#projectAddModal form").empty();
        $("#projectAddModal").modal({
            backdrop: "static"
        })
    });

    function show_validate_message(ele, success, message) {
        $(ele).parent().removeClass("has-error has-success");
        if (success == "error") {
            $(ele).parent().addClass("has-error");
            $(ele).next("span").text(message);
            return false;
        }
        else{
            $(ele).parent().addClass("has-success");
            $(ele).next("span").text("");
        }

    }

    function validateProjectForm() {
        var projectName = $("#projectName_add_input").val();
        var regName = /(^[a-zA-Z0-9_-]{3,120}$)|(^[\u2E80-\u9FFF]{3,40}$)/;
        if (!regName.test(projectName)) {
            show_validate_message("#projectName_add_input","error","项目名称为3-120为英文和字母的组合或3-40为中文！");
            //alert("项目名称为3-120为英文和字母的组合或3-40为中文！");
          /*  $("#projectName_add_input").parent().removeClass("has-error has-success");
            $("#projectName_add_input").parent().addClass("has-error");
            $("#projectName_add_input").next("span").text("项目名称为3-120为英文和字母的组合或3-40为中文！");
            return false;*/
          return false;
        }
        else {
            show_validate_message("#projectName_add_input","error","");
           /* $("#projectName_add_input").parent().removeClass("has-error has-success");
            $("#projectName_add_input").parent().addClass("has-success");
            $("#projectName_add_input").next("span").text("");*/
        }
        var stock = $("#stock_add_input").val();
        var regStock = /^\d+$/;
        if (!regStock.test(stock)) {
            //alert("库存为数字");
            $("#stock_add_input").parent().removeClass("has-error has-success");
            $("#stock_add_input").parent().addClass("has-error");
            $("#stock_add_input").next("span").text("库存为数字");
            return false;
        }
        else {
            $("#stock_add_input").parent().removeClass("has-error has-success");
            $("#stock_add_input").parent().addClass("has-success");
            $("#stock_add_input").next("span").text("");
        }
        return true;
    }

    $("#project_save_btn").click(function () {
        /* alert($("#projectAddModal form").serialize());*/

        if (!validateProjectForm()) {
            return false;
        }
        console.log($("#projectAddModal form").serialize());
        $.ajax({
            url: "/select/project",
            type: "POST",
            data: $("#projectAddModal form").serialize(),
            success: function (result) {
                /* alert(result.success);*/
                $("#projectAddModal").modal("hide");
                window.location.reload();
            }
        })

    });
    $(document).on("click", ".edit_btn", function () {
        $.ajax({
            url: "/select/project/" + $(this).attr("edit_id"),
            type: "GET",
            success: function (result) {
                var project = result.data;
                $("#projectName_edit_static").text(project.projectName);
                $("#stock_edit_input").val(project.stock);
                $("#projectDescription_edit_input").val(project.projectDescription);
            }
        })
        $("#project_update_btn").attr("edit_id", $(this).attr("edit_id"));
        $("#projectEditModal").modal({
            backdrop: "static"
        })
    });
    $("#project_update_btn").click(function () {

        $.ajax({
            url: "/select/project/" + $(this).attr("edit_id"),
            type: "POST",
            data: $("#projectEditModal form").serialize() + "&_method=PUT",
            success: function () {
                $("#projectEditModal").modal("hide");
                window.location.reload();
            }
        })

    })
    $(document).on("click", ".delete_btn", function () {
        $.ajax({
            url: "/select/project/" + $(this).attr("delete_id"),
            type: "POST",
            data: {
                _method: "DELETE"
            },
            success: function () {
                alert("删除成功");
                window.location.reload();
            }
        })
    });
</script>
</body>
</html>
