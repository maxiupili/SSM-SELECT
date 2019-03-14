<%--
  Created by IntelliJ IDEA.
  User: bukbuk
  Date: 2017/12/10
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
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
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button class="navbar-toggle collapsed" aria-expanded="false" aria-controls="navbar" type="button" data-toggle="collapse" data-target="#navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">选题系统</a>
        </div>
        <div class="navbar-collapse collapse" id="navbar">
            <ul class="nav  navbar-nav ">
                <li class="active"><a href="/select/index">主页</a></li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        功能
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="/select/adminConsole">项目管理</a></li>
                        <li><a href="/select/enrollmentList">选课情况</a></li>
                    </ul>

                </li>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/select/invalidation">登出</a></li>
            </ul>
        </div>

    </div>
</nav>
<div class="container theme-showcase " role="main">
    <div class="jumbotron">
        <h1>课设毕设选题系统</h1>
        <p>此系统能进行课设或毕设的选题，一定程度上的用户管理</p>
        <p>
            <a class="btn btn-lg btn-primary"  role="button">查看文档 »</a>
        </p>
    </div>

</div>
<script src="<%=basePath%>js/jquery-3.2.1.min.js"></script>
<script src="<%=basePath%>bootstrap/js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/vendor/holder.min.js"></script>
</body>
</html>
