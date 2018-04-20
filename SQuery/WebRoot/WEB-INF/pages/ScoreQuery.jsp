<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
        <a class="back" href="${pageContext.request.contextPath}/index.action"><span class="glyphicon glyphicon-home"></span></a>
    </div>
    <div class="row">
        <div class="title">
            <span>成绩查询</span>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-10 col-sm-10 col-md-6 col-lg-6 col-lg-offset-3 col-md-offset-3 col-xs-offset-1 col-sm-offset-1">
            <div class="container-fluid">
                <div class="form-horizontal">
                    <div class="form-group has-error">
                        <div class="col-sm-9 col-xs-9 col-md-9 col-lg-9">
                            <input type="text" class="form-control" id="snum" name="snum" placeholder="请输入学号..." onclick="test()">
                            <span id="span8" class="help-block"></span>
                        </div>
                        <div class="col-sm-3 col-xs-3 col-md-3 col-lg-3">
                            <button  class="btn btn-default" id="submit" onclick="CheckUserName()">查&nbsp;&nbsp;询</button>
                        </div>
                    </div>
                </div>
                <div class="col-sm-12 col-xs-12 col-md-12 col-lg-12">
                    <table class="table">
                        <tbody>
                            <tr>
                                <td>学&nbsp;&nbsp;号：</td>
                                <td id="span1"></td>
                            </tr>
                            <tr>
                                <td>姓&nbsp;&nbsp;名：</td>
                                <td id="span2"></td>
                            </tr>
                            <tr>
                                <td>班&nbsp;&nbsp;级</td>
                                <td id="span3"></td>
                            </tr>
                            <tr>
                                <td>平均成绩：</td>
                                <td class="score" id="span5"></td>
                            </tr>

                            <tr>
                                <td>平均绩点：</td>
                                <td class="score" id="span7"></td>
                            </tr>

                            <tr>
                                <td class="chack-btn" colspan="2">
                                    请核对上面各项内容是否有误？<br>
                                    <button type="button" id="bt" class="btn btn-primary" onclick="Chack(1)" disabled>无误戳我</button>
                                    <button type="button" id="bt1" class="btn btn-danger" onclick="Chack(2)" disabled>报错戳我</button>
                                </td>
                            </tr>

                        </tbody>
                    </table>
                </div>
            </div>



        </div>
    </div>
</div>
<div class="footer">
    <img src="${pageContext.request.contextPath}/images/logo2.jpg">
    <span>&copy;点心工作室</span>
</div>
 <script type="text/javascript">
         var state;      
        function CheckUserName()
        {
           var bt=document.getElementById("bt");
           var bt1=document.getElementById("bt1");
          var snum=document.getElementById("snum").value;
          if(snum!=null && snum!="")
          {
            var xhr=new XMLHttpRequest();
            xhr.onreadystatechange=function(){
            if(xhr.readyState==4 &&xhr.status==200)
            {
               
                var json=xhr.responseText;  
                var jsonStr=eval("("+json+")");   
                if(jsonStr!="")
                {             
                document.getElementById("span1").innerHTML=jsonStr[0].snum;
                document.getElementById("span2").innerHTML=jsonStr[0].sname;
                document.getElementById("span3").innerHTML=jsonStr[0].sclass;
               // document.getElementById("span4").innerHTML=jsonStr[0].totalScore;
                document.getElementById("span5").innerHTML=jsonStr[0].avgScore.toFixed(2);
                //document.getElementById("span6").innerHTML=jsonStr[0].totalPoint;
                var  avgPoint=Math.round(jsonStr[0].avgPoint*100)/100;
                document.getElementById("span7").innerHTML=avgPoint;
                document.getElementById("span8").innerHTML="";
                state=jsonStr[0].state;
                bt.disabled=false;
                //bt.style.background="green";
                bt1.disabled=false;
               // bt1.style.background="green";            
                }else
                {
                document.getElementById("span1").innerHTML="";
                document.getElementById("span2").innerHTML="";
                document.getElementById("span3").innerHTML="";
                //document.getElementById("span4").innerHTML="";
                document.getElementById("span5").innerHTML="";
                //document.getElementById("span6").innerHTML="";
                document.getElementById("span7").innerHTML="";
                document.getElementById("span8").innerHTML="学号不正确！";
                bt.disabled=true;
                //bt.style.background="red";
                bt1.disabled=true;
               // bt1.style.background="#red";      
                }
            }
           }
          xhr.open("get","${pageContext.request.contextPath}/Student_queryPoint.action?time="+new Date().getTime()+"&snum="+snum,"true");
          xhr.send(null);
          }else
          {
           document.getElementById("span8").innerHTML="学号不能为空！";
          }
        }
        
        
         function Chack(num)
       {
        if(state==1)
        {
          alert("你已经确认过！");         
        }else if(state==2)
        {
          alert("你的问题已经提交了");
        }
        else{
          var snum=document.getElementById("span1").innerHTML;
           var xhr=new XMLHttpRequest();
            xhr.onreadystatechange=function(){
               if(xhr.readyState==4 &&xhr.status==200)
               {             
                var json=xhr.responseText;  
                alert(json);
               } 
           }     
          xhr.open("get","${pageContext.request.contextPath}/Student_ensurePoint.action?time="+new Date().getTime()+"&snum="+snum+"&num="+num,"true");
          xhr.send(null);
          state=num;
          }          
        }
    
       function test(){
            document.getElementById("bt").disabled=true;
              //document.getElementById("bt").style.background="#fff";
               document.getElementById("bt1").disabled=true;
             // document.getElementById("bt1").style.background="#fff"             
        }
    </script>
</body>
</html>
