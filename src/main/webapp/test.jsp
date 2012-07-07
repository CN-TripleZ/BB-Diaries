<%@page import="org.apache.commons.dbutils.handlers.ArrayHandler"%>
<%@page import="org.apache.commons.dbutils.QueryRunner"%>
<%@page import="com.bb.diaries.database.DbManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
	<%
	QueryRunner qr = DbManager.getQueryRunner();
	Object[] result =  qr.query("select 1 from dual", new ArrayHandler());
	%>
<body>
	<%
	if(result!=null && result.length>0){ 
	%>
		数据库存连接成功...
	<%} %>
</body>
</html>