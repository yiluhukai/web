<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <title>文件上传</title>
    <link rel="stylesheet" href="css/updateStyle.css" type="text/css">
</head> 
  
  <body>
   <div class="title">
    <span>专业分流后台管理-成绩表上传</span>
   </div>
     <s:property value="#session.error"/>  
    <div class="update-file">   
      <form action="${pageContext.request.contextPath}/Admin_importData.action" method="post" enctype="multipart/form-data" >
            <input type="file" name="athlete">
            <button type="submit">开始上传</button>
      </form>
     </div>
  </body>
</html>
