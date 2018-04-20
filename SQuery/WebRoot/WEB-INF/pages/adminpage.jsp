<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'adminpage.jsp' starting page</title>
    

  </head>
  
  <body>
     <h1>管理员页面</h1> 
     <p><s:property value="#session.admin.aname"/> </p>
     <a href="${pageContext.request.contextPath}/Admin_importMessagePage.action">导入学生数据</a><br> 
     <a href="${pageContext.request.contextPath}/Admin_exportMessagePage.action">导出学生数据</a>
  </body>
</html>
