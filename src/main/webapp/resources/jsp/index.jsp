<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>图书管理系统-首页</title>
<link href="<%=basePath %>resources/css/index.css" rel="stylesheet" type="text/css" />
 </head>
<body>
<div id="container">
	<!-- <img src="<%=basePath %>resources/images/logo.gif" /> -->
	<div id="banner"></div>
	<div id="globallink">
		<ul>
			<li><a href="<%=basePath %>resources/jsp/index.jsp">首页</a></li>
			<li><a href="<%=basePath %>BookType/frontlist" target="OfficeMain">图书类型</a></li> 
			<li><a href="<%=basePath %>Book/frontlist" target="OfficeMain">图书</a></li> 
			<li><a href="<%=basePath %>resources/jsp/login.jsp">管理系统</a></li> 
		</ul>
		<br />
	</div> 
	<div id="main">
	 <iframe id="frame1" src="<%=basePath %>resources/jsp/desk.jsp" name="OfficeMain" width="100%" height="100%" scrolling="yes" marginwidth="0" marginheight="0" frameborder="0" vspace="0" hspace="0" >
	 </iframe>
	</div>
	
</div>
</body>
</html>
