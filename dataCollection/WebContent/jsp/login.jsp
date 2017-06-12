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
<title>登陆</title>
	<!-- easyui加载 -->
	<script type="text/javascript" src="<%=basePath%>jsp/resource/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>jsp/resource/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>jsp/resource/easyui/extension/treegrid-dnd/treegrid-dnd.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>jsp/resource/easyui/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>jsp/resource/easyui/themes/icon.css" />
	<style type="text/css"></style>
	<!-- 公共js加载 -->
	<link href="<%=basePath%>jsp/images/login.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=basePath%>jsp/operation/extends.js"></script>
	<script type="text/javascript" src="<%=basePath%>jsp/operation/common.js"></script>
	<script type="text/javascript">
	function login() {
		var rootPath=getRootPath();
		$.ajax({
			   type: "POST",
			   url: "./login.verifyUser.action",
			   data: {
				   "username":$("#username").val(),
				   "passwd":$("#passwd").val()
			   },
			   success: function(result){
				   var dataObject = JSON.parse(result);
				   if(!dataObject.Flag){
					   $.messager.show({
		                   title: '登陆提示',
		                   msg: dataObject.Message
		              });
				   }else{
					   window.location.href=rootPath;
				   }
			   }
			});
	}
	function clearForm(){
		$("#username").val("");
		$("#passwd").val("");
	}
	$(function() {
		 $("body").keyup(function () {  
             if (event.which == 13){  
            	if($("#username").val().length==0){
            		 $.messager.show({
		                   title: '登陆提示',
		                   msg: "用户名不得为空"
		              });
            	}else if($("#passwd").val().length==0){
            		 $.messager.show({
		                   title: '登陆提示',
		                   msg: "密码不得为空"
		              });
            	}else{
            		 login();
            	}
             }  
         });
	})
	</script>
</head>
<body bgcolor="white">
    <div id="login">
	     <div id="top">
		      <div id="top_left"><img src="<%=basePath%>jsp/images/login_03.gif" /></div>
			  <div id="top_center"></div>
		 </div>
		 
		 <div id="center">
		      <div id="center_left"></div>
			  <div id="center_middle">
			       <div id="user">用 户
			         <input id="username" type="text" name="textfield" />
			       </div>
				   <div id="password">密   码
				     <input id="passwd" type="password" name="textfield2" />
				   </div>
				   <div id="btn"><a onclick="login();"  href="#">登录</a><a href="#" onclick="clearForm();">清空</a></div>
			  
			  </div>
			  <div id="center_right"></div>		 
		 </div>
		 <div id="down">
		      <div id="down_left">
			      <div id="inf">
                       <span class="inf_text">版本信息</span>
					   <span class="copyright">系统版本：V1.0</span>
			      </div>
			  </div>
			  <div id="down_center"></div>		 
		 </div>

	</div>
</body>
</html>
