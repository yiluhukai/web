<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>学生成绩查询</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/indexStyle.css" rel="stylesheet" type="text/css">

</head>
<body>

<div class="container-fluid">
    <div class="row top-nav">
        <img src="${pageContext.request.contextPath}/images/logo.png" class="img-responsive">
    </div>
    <div class="row">
        <div class="title">
            <span>电信学院2015级<br>大类专业分流</span>
        </div>
    </div>
    <div class="row">
        <div class="col-md-8 col-xs-8 col-sm-8 col-sm-offset-2 col-xs-offset-2 col-md-offset-2">
             <a href="${pageContext.request.contextPath}/Admin_scorequery.action" class="link-btn">成绩查询</a>
        </div>
    </div>
    <div class="row">
        <div class="col-md-8 col-xs-8 col-sm-8 col-sm-offset-2 col-xs-offset-2 col-md-offset-2">
            <a href="http://dianxinstudio.mikecrm.com/EOLNL2l"  class="link-btn">志愿填报</a>
        </div>
    </div>
</div>
<div class="footer">
    <img src="${pageContext.request.contextPath}/images/logo2.jpg">
    <span>&copy;点心工作室</span>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
</html>
