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
    
    <title>My JSP 'index.jsp' starting page</title>

  </head>
  
  <body>
      <h1>管理员登陆页面</h1>
      <s:actionerror/>
      <s:property value="#session.error"/>
      <form action="${pageContext.request.contextPath}/Admin_adminLogin" method="post">     
             <span>管理员</span><input type="text" name="aname"><s:fielderror fieldName="aname"/>
         <p> <span>密码</span><input type="password" name="apwd"><s:fielderror fieldName="apwd"/></p>
         <input type="submit" value="提交">
      </form>
  </body>
</html>
