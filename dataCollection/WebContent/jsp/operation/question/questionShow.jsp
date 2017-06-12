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
<title>调查项目管理</title>
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
	<script type="text/javascript" src="<%=basePath%>jsp/operation/question/js/questionShow.js"></script>
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
		<div class="style_cheng_14_bold" style="border-bottom:1px solid #dedede; line-height:40px;">创建调查问题</div>
  	<!-- 以下为显示表单 -->
  	<div style="width:880px; float:left; margin-top:30px; border:1px solid #dfdfdf">
			<table  width="880" border="0" cellpadding="0" cellspacing="0">
			  <tbody>
			  <tr>
				<td width="880" height="40" align="center" bgcolor="#EEEEEE" class="style_hei_16_bold" id="itemTitle"></td>
				<input type="hidden" id="itemId"/>
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
			 	<tbody  id="saveItem">
			  <tr id="createQuestionTr">
				<td height="70" align="left" class="style_hui_12" style="padding-left:10px;">
				<form name="form2" method="post" action="change_vtest.asp">
					<input type="hidden" name="num" value="0">
					<input type="button" onclick="check2()" name="Submit2" value="确定发布该调查" style=" cursor:pointer; border-style:solid; border-width:1px; cursor:hand; border-color:#F56A5F; background-color:#F26100;font-size:14px; font-weight:bold; color:#FFFFFF; letter-spacing:0px; width:150px; height:35px; padding-top:0px">
					&nbsp;&nbsp;<a href="#fabu" class="lan_12">继续添加问题</a>
				</form>
				</td>
			  </tr>
		  	</tbody>
			  </table>
		</div>
		<!-- 以下为添加表单 -->
	<div data-options="region:'south'" style="height:100px;"> 
		<div style="width:880px; float:left; margin-top:10px; border:1px solid #dfdfdf">
			<div style="background-color:#EAEAEA; height:30px; line-height:30px; padding-left:20px;" class="style_cheng_14_bold">添加问题</div>
			<form name="form" action="change_vtest.asp" method="post" id="a">
			 <table width="640" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:20px;" id="fabu">
				  <tbody>
				  <tr>
					<td width="84" height="40" class="style_hui_14">问题标题<span class="style_hong_12">*</span></td>
					<td width="516">
					<input name="title" type="text" id="title" style="width:400px; height:25px; line-height:25px; border:1px solid #777777; background-color:#FFEFDF"></td>
				  </tr>
				   <tr id="questionScoreTr">
					<td width="84" height="40" class="style_hui_14">问题分数<span class="style_hong_12">*</span></td>
					<td width="516">
						<input type="text" id="score" class="easyui-numberbox" value="0" style="width:400px; height:25px; line-height:25px; border:1px solid #777777; background-color:#FFEFDF" data-options="min:0,precision:0,max:100"></input>  
					</tr>
				  <tr>
					<td height="40" class="style_hui_14">问题类型<span class="style_hong_12">*</span></td>
					<td class="style_hui_14">
						<input type="radio" name="choice" value="1" onclick="showQuestionItem();">
						单选
						<input type="radio" name="choice" value="2" onclick="showQuestionItem();">
						多选	
						<input type="radio" name="choice" value="3" onclick="removeQuestionItem();">
						问题			
					</td>
				  </tr>
				  <tr id="questionIntem">
					<td height="40" valign="top" class="style_hui_14" style="padding-top:12px;">问题选项<span class="style_hong_12">*</span></td>
					<td valign="top">
						<div style="margin-top:8px;" id="tritem0_0"  class="style_hui_14">
							<input name="item0_0" type="text" style="width:400px; height:25px; line-height:25px; border: 1px solid #777777; background-color:#FFEFDF">
							&nbsp;选项分数&nbsp;&nbsp;<span class="style_hong_12">*</span>&nbsp;<input type="text" name="itemScore0_0"  class="easyui-numberbox" value="0" style="width:30px; height:25px; line-height:25px; border:1px solid #777777; background-color:#FFEFDF" data-options="min:0,precision:0,max:100"></input>  
						</div>
						
						<div style="margin-top:8px;" id="tritem0_1" class="style_hui_14">
							<input name="item0_1" type="text" style="width:400px; height:25px; line-height:25px; border: 1px solid #777777; background-color:#FFEFDF">
							&nbsp;选项分数&nbsp;&nbsp;<span class="style_hong_12">*</span>&nbsp;<input type="text" name="itemScore0_1"  class="easyui-numberbox" value="0" style="width:30px; height:25px; line-height:25px; border:1px solid #777777; background-color:#FFEFDF" data-options="min:0,precision:0,max:100"></input>  
						</div>
						
						<div style="margin-top:8px;" id="tritem0_2" class="style_hui_14">
							<input name="item0_2" type="text" style="width:400px; height:25px; line-height:25px; border: 1px solid #777777; background-color:#FFEFDF">
							&nbsp;选项分数&nbsp;&nbsp;<span class="style_hong_12">*</span>&nbsp;<input type="text" name="itemScore0_2"  class="easyui-numberbox" value="0" style="width:30px; height:25px; line-height:25px; border:1px solid #777777; background-color:#FFEFDF" data-options="min:0,precision:0,max:100"></input>  
							<img src="images/cha.gif" align="absmiddle" style="cursor:pointer;" onclick="removeitem(tritem0_2)">
						</div>
						
						<div style="margin-top:8px; width:80px; cursor:pointer;" id="tritem0" class="style_lan_12" onclick="additem(0);">
							<input type="hidden" name="nn" id="nn" value="2">
							<div style="float:left"><img src="images/jia.gif" align="absmiddle"></div>
							<div style="float:left; margin-left:5px; margin-top:2px;">添加选项</div>
						</div>				
					</td>
				  </tr>
				  
				  <tr>
					<td height="70">&nbsp;<font></font></td>
					<td valign="top" style="padding-top:15px;"><input type="button" onclick="check()" name="Submit2" value="确定添加问题" style=" cursor:pointer; border-style:solid; border-width:1px; cursor:hand; border-color:#F56A5F; background-color:#F26100;font-size:12px; color:#FFFFFF; letter-spacing:1px; width:100px; height:26px; padding-top:0px"></td>
				  </tr>
			  </tbody></table>
			</form>
			</div>
		</div>  
   </div>
</body>
</html>
