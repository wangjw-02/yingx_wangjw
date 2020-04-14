<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>应学后台管理系统</title>
    <link rel="icon" href="${path}/bootstrap/img/arrow-up.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.css">

    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/css/css/hot-sneaks/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/boot/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script src="${path}/bootstrap/js/bootstrap.js"></script>
    <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${path}/bootstrap/jqgrid/boot/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/bootstrap/js/ajaxfileupload.js"></script>
    <style>
        p {
            margin: 10px 0 10px;
        }
       .footer {

            margin-top: 200px;
            color: black;
            text-align: center;
            background-color: #e5e5e5 ;
        }
    </style>

</head>
<body>

    <!--顶部导航-->
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <a class="navbar-brand" href="#">应学视频App后台管理系统</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">欢迎：<span style="color: deepskyblue">${sessionScope.admin.name}</span></a></li>
                    <li >
                        <a href="${path}/admin/exit"  role="button" aria-haspopup="true" aria-expanded="false">退出 <span class="glyphicon glyphicon-log-out"></span></a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!--栅格系统-->
    <div class="row">
        <div class="container-fluid">
            <!--左边手风琴部分-->
            <div class="col-md-2">

                <div class="panel-group" id="leftcase">
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" style="background: #f2dede">
                            <h5 class="panel-title" style="width: 100%;text-align: center">
                                <a href="#user" role="button" data-toggle="collapse" data-parent="#leftcase">
                                    <span style="color: red;font-size: large">用户管理</span>
                                </a>
                            </h5>
                        </div>
                        <div id="user" class="panel-collapse collapse" role="tabpanel" >
                            <div class="panel-body" style="width: 100%;text-align: center">
                                <a class="btn btn-warning" href="javascript:$('#mainId').load('${path}/main/user.jsp')">用户展示</a><br><br>
                                <a class="btn btn-warning" href="javascript:$('#mainId').load('${path}/main/userecharts.jsp')">用户统计</a><br><br>
                                <a class="btn btn-warning" href="javascript:$('#mainId').load('${path}/main/userdis.jsp')">用户分布</a>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" style="background: #dff0d8">
                            <h5 class="panel-title" style="width: 100%;text-align: center">
                                <a href="#category" role="button" data-toggle="collapse" data-parent="#leftcase">
                                    <span style="color: #4cae4c;font-size: large" readonly>分类管理</span>
                                </a>
                            </h5>
                        </div>
                        <div id="category" class="panel-collapse collapse" role="tabpanel">
                            <div class="panel-body" style="width: 100%;text-align: center">
                                <a class="btn btn-default" href="javascript:$('#mainId').load('${path}/category/category.jsp')" >分类列表</a><br><br>

                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" style="background: #d9edf7">
                            <h5 class="panel-title" style="width: 100%;text-align: center">
                                <a href="#like" role="button" data-toggle="collapse" data-parent="#leftcase">
                                    <span style="color: #4cae4c;font-size: large">视频管理</span>
                                </a>
                            </h5>
                        </div>
                        <div id="like" class="panel-collapse collapse" role="tabpanel">
                            <div class="panel-body" style="width: 100%;text-align: center">
                                <a class="btn btn-default" href="javascript:$('#mainId').load('${path}/video/video.jsp')">视频列表</a><br><br>
                                <%--<a class="btn btn-default">添加视频</a>--%>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab">
                            <h5 class="panel-title" style="width: 100%;text-align: center">
                                <a href="#log" role="button" data-toggle="collapse" data-parent="#leftcase">
                                    <span style="color: #31b0d5;font-size: large">日志管理</span>
                                </a>
                            </h5>
                        </div>
                        <div id="log" class="panel-collapse collapse" role="tabpanel">
                            <div class="panel-body" style="width: 100%;text-align: center">
                                <button class="btn btn-default">日志列表</button><br><br>
                                <button class="btn btn-default">添加日志</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-10" id="mainId">
                <!--巨幕开始-->
                <div class="jumbotron">
                    <h1>欢迎来到应学App后台管理系统</h1>
                </div>
                <!--右边轮播图部分-->
                <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                    </ol>

                    <!-- Wrapper for slides -->
                    <div class="carousel-inner" role="listbox">
                        <div class="item active">
                            <img class="center-block" src="${path}/bootstrap/img/pic1.jpg" alt="..." >
                        </div>
                        <div class="item">
                            <img class="center-block" src="${path}/bootstrap/img/pic2.jpg" alt="...">
                        </div>
                        <div class="item">
                            <img class="center-block" src="${path}/bootstrap/img/pic3.jpg" alt="..." >
                        </div>
                        <div class="item">
                            <img class="center-block" src="${path}/bootstrap/img/pic4.jpg" alt="...">
                        </div>

                    </div>

                    <!-- Controls -->
                    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>








        </div>
    </div>






















    <!--页脚-->
    <div class="footer">
        <div class="container">
            <p style="">@百知教育wangjw<a href="#">@zparkhr.com</a></p>
        </div>

    </div>

    <!--栅格系统-->

</body>
</html>
