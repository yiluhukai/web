<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'exportMessagePage.jsp' starting page</title>
    <script type="text/javascript">
    function test()
    {
     var myselect=document.getElementById("category");
     var c=myselect.selectedIndex;
     document.getElementById("hide").value=myselect.options[c].text+".xls";   
    }
    </script>
  </head>
  
  <body>
    <h1>生成excel</h1> <br>
    <form action="Admin_exportData.action" method="post">
             学生类型：<select name="category" id="category">
                     <option value="0">没有确认学生</option>
                     <option value="1">确认得学生</option>
                     <option value="2">有问题得学生</option>
                     <option value="4">所有学生</option>
               </select>  
               <input type="submit" value="提交" onclick="test()">
               <input type="hidden" name="athleteFileName" id="hide" >
    </form>
  </body>
</html>
