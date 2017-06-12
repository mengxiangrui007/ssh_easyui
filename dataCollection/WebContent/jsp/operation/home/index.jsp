<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>**测评管理系统</title>
	<!-- easyui加载 -->
	<script type="text/javascript" src="<%=basePath%>jsp/resource/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>jsp/resource/easyui/jquery.easyui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>jsp/resource/easyui/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>jsp/resource/easyui/themes/icon.css" />
	<!-- ztree加载 -->
	<script type="text/javascript" src="<%=basePath%>jsp/resource/ztree/jquery.ztree.all-3.5.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>jsp/resource/ztree/css/zTreeStyle/zTreeStyle.css">
	<!-- 公共js加载 -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>jsp/operation/home/css/default.css">
	<script type="text/javascript" src="<%=basePath%>jsp/operation/extends.js"></script>
	<script type="text/javascript" src="<%=basePath%>jsp/operation/common.js"></script>
	<!-- 自定义js加载 -->
	<script type="text/javascript" src="<%=basePath%>jsp/operation/home/js/index.js"></script>
</head>

<body class="easyui-layout">
<!-- 头部标题 -->
<div data-options="region:'north',border:false" style="height:60px; padding:5px; background:#F3F3F3" id="header"> 
	<a href="www.solooo.net"><span class="northTitle">**测评管理系统</span></a>
    <span class="loginInfo">登录用户：${session.root_web_session_user.account}&nbsp;&nbsp;姓名：${session.root_web_session_user.fullname}&nbsp;&nbsp;角色：${session.root_web_session_user.role.name}&nbsp;&nbsp;<a href="#" onclick="removeUser();">注销</a></span>
</div>
<!-- 左侧导航 -->
<div data-options="region:'west',split:true,title:'导航菜单', fit:false" style="width:200px;" id="left"> 
  <ul id="menuTree" class="ztree">
  </ul>
</div>

<!-- 页脚信息 -->
<div data-options="region:'south',border:false" style="height:20px; background:#F3F3F3; padding:2px; vertical-align:middle;" id="bottom">
	<span id="sysVersion">系统版本：V1.0</span>
    <span id="nowTime"></span>
</div>

<!-- 内容tabs -->
<div id="center" data-options="region:'center'">
  <div id="tabs" class="easyui-tabs">
    <div title="首页" style="padding:5px;display:block;" >
    </div>
  </div>
	<!-- 用于弹出框 -->
	<div id="parent_win"></div>
</div>
</body>
</html>
</html>