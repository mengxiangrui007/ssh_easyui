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
<title>调查管理</title>
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
	<script type="text/javascript" src="<%=basePath%>/jsp/operation/survey/js/dialogGridStudent.js"></script>
	<script type="text/javascript" src="<%=basePath%>/jsp/operation/survey/js/dialogGridTeacher.js"></script>
	<script type="text/javascript" src="<%=basePath%>/jsp/operation/survey/js/surveyManager.js"></script>
</head>

<body class="easyui-layout" >
<div id="body" region="center"  > 
  <!-- 查询条件区域 -->
  <div id="search_area">
    <div id="conditon">
    <form method="get">
      <table border="0">
        <tr>
          <td style="display: none;">&nbsp;院系：</td>
          <td style="display: none;"><input id="department" class="easyui-combotree"  multiple="true" cascadeCheck="false"  lines="true" style="width:250px;"/></td>
          <td>&nbsp;调查名称：</td>
          <td><input   id="surveyName"/></td>
          <td>
             &nbsp;&nbsp; <a  href="javascript:void(0)" id="querySurvey" class="easyui-linkbutton my-search-button" iconCls="icon-search" plain="true">查询</a> 
              <a  href="javascript:void(0)" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
          </td>
        </tr>
      </table>
     </form>
    </div>
    <span id="openOrClose"></span> 
  </div>
  <!-- 数据表格区域 -->
  <table id="tt" style="table-layout:fixed;"></table>
  <!-- 表格顶部工具按钮 -->
  <div id="tt_btn">
      <a href="javascript:void(0)"  id="save" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增调查<img alt="" src="" onmouseover="" onmouseout=""></a>
   </div>
   <!-- 弹出框 -->
      <div id="showSurvey"  style="padding:10px;">
        <div class="easyui-layout" data-options="fit:true">
            <!-- <div data-options="region:'east',split:true" style="width:100px"></div> -->
             <div data-options="region:'center'" style="padding:10px;">
        <form id="fm" method="post" novalidate>
            <table cellpadding=5 cellspacing=0 width=100%  align="center">
            <tr>
            	 <td width="45px">调查名称：</td>
                <td width="250px" colspan="4">
                	<input type="text" id="itemChoice" onclick="aaaa();" data-options="required:true" style="width:250px"/>
                </td>
            </tr>
             <tr>
                  	<td width="60px">调查目标：</td>
		           <td width="100px">
                	<select id="surveyChoice" style="width:200px"   data-options="required:true,panelHeight:125"></select>
				    <div id="sp">
				        <div style="color:#99BBE8;background:#fafafa;padding:5px;">调查目标：</div>
				        <div style="padding:10px">
				            <input type="radio" name="target" value="1"><span>调查学生</span><br/>
				            <input type="radio" name="target" value="2"><span>调查教职工</span><br/>
			              	<input type="radio" name="target" value="3"><span>调查部门</span><br/>
				        </div>
				    </div>
                 </td>
                 <td width="60px" name="targetChoice">选择学生：</td>
                 <td width="100px" name="targetChoice"> 
                 	<input type="text" id="studentName" data-options="required:true"  onclick="openDialog();" readonly="true" style="width:150px"/>
                	<input type="hidden" id="studentValue"/>
                  </td>
            </tr>
             <tr>
                  <td>参与院系：</td>
		           <td width="100px">
                	<input type="text" id="departmentSurvey"  data-options="required:true"    cascadeCheck="false"  lines="true" style="width:200px;"/>
                 </td>
                 <td width="45px">参与专业：</td>
                 <td width="100px"> 
            		<input type="text"  id="professionalSurvey"    data-options="required:true" cascadeCheck="true"  style="width:150px"/>
                  </td>
            </tr>
             <tr>
                 <td>参与年级：</td>
		           <td width="100px">
                	<input  id="greadSurvey"    style="width:200px;"/>
                 </td>
                 <td width="45px">参与班级：</td>
                 <td width="100px"> 
            		<input type="text"   id="classSurvey"    style="width:150px"/>
                  </td>
            </tr>
            <tr>
                <td>开始时间：</td>
                <td><input class= "easyui-datetimebox"   id="startSurveyTime" data-options="editable:false,required:true"></td>
              	<td>结束时间：</td>
                <td><input class= "easyui-datetimebox"   id="endSurveyTime" data-options="editable:false,required:true"></td>
             </tr>
                <tr>
                <td>参与教师：</td>
                <td><input type='text' id='teacherChioce'  onclick='openTheacherDialog();'   style='width:150px'/>
                	<input type='hidden' id='teacherChioceValue'/></td>
             </tr>
        	</table>
        </form>
        </div>
	            <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
	                <a class="easyui-linkbutton"  id="saveSurvey" data-options="iconCls:'icon-ok'" href="javascript:void(0)" >保存</a>
	                <a class="easyui-linkbutton" id="cancelSurvey" data-options="iconCls:'icon-cancel'" href="javascript:void(0)">取消</a>
	            </div>
            </div>
        </div>
           <!-- 弹出框 -->
      <div id="showStudnet"  >
        <div class="easyui-layout" data-options="fit:true">
            <!-- <div data-options="region:'east',split:true" style="width:100px"></div> -->
             <div data-options="region:'center'">
             <div id="search_area1">
    <div id="conditon">
    <form method="get">
      <table border="0">
        <tr>
          <td style="display: none;">&nbsp;院系：</td>
          <td style="display: none;"><input id="department" class="easyui-combotree"  multiple="true" cascadeCheck="false"  lines="true" style="width:250px;"/></td>
           <td>&nbsp;专业：</td>
          <td ><input   id="professionalName"/></td>
          <td>&nbsp;学号：</td>
          <td ><input   id="studentNo"/></td>
          <td>&nbsp;姓名：</td>
          <td><input   id="fullname"/></td>
          <td>
             &nbsp;&nbsp; <a  href="javascript:void(0)" id="queryStudent" class="easyui-linkbutton my-search-button" iconCls="icon-search" plain="true">查询</a> 
              <a  href="javascript:void(0)" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
          </td>
        </tr>
      </table>
     </form>
    </div>
    </div>
             	  <!-- 数据表格区域 -->
  				<table id="tt1" style="table-layout:fixed;"></table>
             </div>
             </div>
       </div>
       <div id="showTeacher">
             <div class="easyui-layout" data-options="fit:true">
            <!-- <div data-options="region:'east',split:true" style="width:100px"></div> -->
             <div data-options="region:'center'">
             <div id="search_area2">
				       <div id="conditon">
						  <form method="get">
						    <table border="0">
						      <tr>
						        <td>&nbsp;工号：</td>
						        <td ><input   id="teachNo"/></td>
						        <td>&nbsp;姓名：</td>
						        <td><input   id="fullname"/></td>
						        <td>
						           &nbsp;&nbsp; <a  href="javascript:void(0)" id="queryTeacher" class="easyui-linkbutton my-search-button" iconCls="icon-search" plain="true">查询</a> 
						            <a  href="javascript:void(0)" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
						        </td>
						      </tr>
						    </table>
						   </form>
						  </div>
  			  </div>
             	  <!-- 数据表格区域 -->
  				<table id="tt2" style="table-layout:fixed;"></table>
             </div>
             </div>
       </div>
</body>
</html>
