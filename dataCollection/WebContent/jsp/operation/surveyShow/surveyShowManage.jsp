<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>参与调查</title>
	<!-- easyui加载 -->
	<script type="text/javascript" src="<%=basePath%>jsp/resource/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>jsp/resource/easyui/jquery.easyui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>jsp/resource/easyui/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>jsp/resource/easyui/themes/icon.css" />
	<!-- 公共js加载 -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>jsp/operation/home/css/default.css">
	<script type="text/javascript" src="<%=basePath%>jsp/operation/extends.js"></script>
	<script type="text/javascript" src="<%=basePath%>jsp/operation/common.js"></script>
	<!-- 自定义js加载 -->
	<script type="text/javascript" src="<%=basePath%>/jsp/operation/surveyShow/js/surveyShowManage.js"></script>
</head>

<body class="easyui-layout" >
<div id="body" region="center"  > 
  <!-- 查询条件区域 -->
  <!-- 数据表格区域 -->
  <table id="tt" style="table-layout:fixed;"></table>
</div>
</body>
</html>
