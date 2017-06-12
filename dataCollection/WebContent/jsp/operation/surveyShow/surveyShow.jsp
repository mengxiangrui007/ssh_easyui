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
<title>填写调查</title>
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
	<script type="text/javascript" src="<%=basePath%>jsp/operation/surveyShow/js/surveyShow.js"></script>
	<style type="text/css">
	.style_cheng_14_bold {
	     font-size: 14px; 
	    color: #f87622; 
	     font-weight: bold; 
	}
		.style_hei_16_bold {
		    font-size: 16px;
		    color: #333333;
		    font-weight: bold;
		}
		.style_shenhui_12 {
		    font-size: 12px;
		    color: #8a8a8a;
		}
		.style_hong_12 {
	    font-size: 12px;
	    color: #FF3737;
		}
		.style_hui_14 {
		    font-size: 14px;
		    color: #555555;
		}
		a.lan_12:visited {
		    text-decoration: none;
		    font-size: 12px;
		    color: #2366a9;
		}
		a.lan_12:link {
		    text-decoration: none;
		    font-size: 12px;
		    color: #2366a9;
		}
	</style>
</head>

<body class="easyui-layout" >
	<div id="body" region="center" style="padding: 20px;overflow:auto;"> 
		<div class="style_cheng_14_bold" style="border-bottom:1px solid #dedede; line-height:40px;">填写调查问题</div>
  	<!-- 以下为显示表单 -->
  	<div style="width:880px; float:left; margin-top:30px; border:1px solid #dfdfdf">
			<table  width="880" border="0" cellpadding="0" cellspacing="0">
			  <tbody>
			  <tr>
				<td width="880" height="40" align="center" bgcolor="#EEEEEE" class="style_hei_16_bold" id="itemTitle"></td>
			  	<input type="hidden" id="surveyId"/>
			  	<input type="hidden" id="surveyType"/>
			  	<input type="hidden" id="targetId"/>
			  </tr>
			  <tr>
				<td height="40" align="center" >
				<span class="style_hong_12" ></span>&nbsp;&nbsp;
				<span class="style_hong_12"></span>
				</td>
			  </tr>
			</tbody>
			</table>
			<table  width="640" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:20px;">
			 	<tbody  id="saveSurvey">
			  <tr id="createQuestionTr">
				<td height="70" align="left" class="style_hui_12" style="padding-left:10px;">
				<form name="form2" method="post" action="change_vtest.asp">
					<input type="hidden" name="num" value="0">
					<input type="button" onclick="check2()" name="Submit2" value="提交问卷" style=" cursor:pointer; border-style:solid; border-width:1px; cursor:hand; border-color:#F56A5F; background-color:#F26100;font-size:14px; font-weight:bold; color:#FFFFFF; letter-spacing:0px; width:150px; height:35px; padding-top:0px">
				</form>
				</td>
			  </tr>
		  	</tbody>
			  </table>
		</div>
   </div>
</body>
</html>
