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
<title>角色管理</title>
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
	<script type="text/javascript" src="<%=basePath%>/jsp/operation/role/js/roleManage.js"></script>
</head>

<body class="easyui-layout" id="content">
        <div data-options="region:'west',collapsible:false,border:false">
        	<div id="westContent"  class="easyui-panel"  title="院系树"   data-options="fit:true"   >
					<ul id="departmentTree"  class="easyui-tree"></ul>
			</div>  
        </div>
        <div id="body" data-options="region:'center',collapsible:false,border:false" >
      		<div id="centerContent"  class="easyui-panel" title="角色管理"   data-options="fit:true"   >
					<ul id="roleTree"  class="easyui-tree"></ul>
			</div>     
        </div>
        <div data-options="region:'east',collapsible:false,border:false" >
			<div id="eastContent"  class="easyui-panel" title="菜单树"   data-options="fit:true,tools:'#topButton'"   >
				<ul id="menuTree"  class="easyui-tree"></ul>
			</div>        
        </div>
        <div id="topButton">
        <a href="javascript:void(0)" class="icon-save" title="保存角色与菜单的授权关系"  onclick="addRoleMenu();"></a>
    </div>
</body>
</html>
