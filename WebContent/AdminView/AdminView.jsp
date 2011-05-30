<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="StyleSheet" href="css/mainPage.css" type="text/css" title="Main CSS">
<title>Insert title here</title>
</head>
<body>
<%
if(session.getAttribute("adminIsLoggedIn")==null){
	 response.sendRedirect("main");
}
String adminIsLoggedIn = (String)session.getAttribute("adminIsLoggedIn");
%>

<div class="headerwrapper"> 
<%@include file="../include/header-content.html"%>
</div>

<div class="menuwrapper"> 

<%@include file="../include/menu-admin.html"%>

</div>

<div class="contentwrapper"> 

<div align="center" class="entry">
Administration Page! :D

</div>

</div>

</body>
</html>