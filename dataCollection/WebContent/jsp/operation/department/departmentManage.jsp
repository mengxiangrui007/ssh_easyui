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
<title>院系管理</title>
	<!-- easyui加载 -->
	<script type="text/javascript" src="<%=basePath%>jsp/resource/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>jsp/resource/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>jsp/resource/easyui/extension/treegrid-dnd/treegrid-dnd.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>jsp/resource/easyui/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>jsp/resource/easyui/themes/icon.css" />
	<!-- 公共js加载 -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>jsp/operation/home/css/default.css">
	<script type="text/javascript" src="<%=basePath%>jsp/operation/extends.js"></script>
	<script type="text/javascript" src="<%=basePath%>jsp/operation/common.js"></script>
	<!-- 自定义js加载 -->
	<script type="text/javascript" src="<%=basePath%>/jsp/operation/department/js/departmentManage.js"></script>
</head>

<body class="easyui-layout" id="content">
	<div id="body" data-options="region:'center',border:false" > 	
	 	  <!-- 数据表格区域 -->
 		 <table id="tt" style="table-layout:fixed;"></table>
	</div>
	<!-- 右面 -->
	<div id="east" data-options="region:'east',border:false,collapsible:false" >
		<div id="eastContent"  class="easyui-panel"  title="菜单树" data-options="fit:true,border:false,tools:'#topButton'"   >
			<ul id="menuTree"  class="easyui-tree"></ul>
		</div>
	</div> 
	  <div id="topButton">
        <a href="javascript:void(0)" class="icon-save" title="保存院系与菜单的授权关系"  onclick="addDepartmentMenu();"></a>
    </div>
</body>
</html>
