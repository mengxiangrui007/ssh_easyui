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
<title>学生管理</title>
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
	<script type="text/javascript" src="<%=basePath%>/jsp/operation/student/js/studentManager.js"></script>
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
           <td>&nbsp;专业：</td>
          <td ><input   id="professionalName"/></td>
          <td>&nbsp;学号：</td>
          <td ><input   id="studentNo"/></td>
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
    <span id="openOrClose"></span> 
  </div>
  <!-- 数据表格区域 -->
  <table id="tt" style="table-layout:fixed;"></table>
  <!-- 表格顶部工具按钮 -->
  <div id="tt_btn">
      <a href="javascript:void(0)"  id="save" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增<img alt="" src="" onmouseover="" onmouseout=""></a>
      <a href="javascript:void(0)"  id="update" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> 
      <a href="javascript:void(0)"  id="delete" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
   </div>
   <!-- 弹出框 -->
            <div id="showStudent"  style="padding:10px;">
        <div class="easyui-layout" data-options="fit:true">
            <!-- <div data-options="region:'east',split:true" style="width:100px"></div> -->
             <div data-options="region:'center'" style="padding:10px;">
        <form id="fm" method="post" novalidate>
            <table cellpadding=5 cellspacing=0 width=100% align="center" border="0">
          	 <tr>
                <td>姓名：</td>
                <td><input name="student.users.fullname" id="fullname"  class="easyui-validatebox" data-options="required:true,validType:['chinese','length[0,10]']" ></td>
           		 <td>性别：</td>
                <td>男：<input type="radio" name="student.users.sexy" value="1"  checked="checked" />&nbsp;&nbsp;女：<input type="radio" name="student.users.sexy" value="0" /></td>
            </tr>
            <tr>
                <td>学号：</td>
                <td><input name="student.studentNo" class="easyui-validatebox" data-options="required:true,validType:['length[0,15]']" ></td>
            	 <td>密码：</td>
                <td><input name="student.users.password" class="easyui-validatebox" data-options="required:true" ></td>
            </tr>
             <tr>
                <td>年级：</td>
                <td><input    name="student.gread" data-options="required:true"></td>
                  <td>班级：</td>
                <td><input    name="student.class_" data-options="required:true"></td>
            </tr>
            <tr>
            	<td>角色：</td>
            	<td>
           		 	<select id="roleChoice" style="width:124px"  name="student.users.role.id" data-options="required:true,panelHeight:100"></select>
				    <div id="sp">
				        <div style="color:#99BBE8;background:#fafafa;padding:5px;">选择一个角色</div>
				        <div style="padding:10px">
				            <input type="radio" name="student.users.role.id" value="3"><span>学生</span><br/>
				            <input type="radio" name="student.users.role.id" value="2"><span>普通管理员</span><br/>
				        </div>
				    </div>
             	 <td style="display: none;">所在院系：</td>
            	<td style="display: none;">
            		<input type="hidden" id="departmentChoice" name="student.department.id" data-options="required:true" style="width:170px"/>
            	</td>
            	
            	<tr>
            		</td>
            	<td >专业：</td>
            	<td >
            		<input  id="professionalChoice" data-options="required:true" style="width:170px"/>
            		<input  type="hidden" id="professionalValue" name="student.professional.id" data-options="required:true" style="width:170px"/>
            	</td>
            </tr>
              <tr>
                <td>电话：</td>
                <td><input class="easyui-textbox"   name="student.users.mobilephone" ></td>
            	 <td>邮箱：</td>
                <td><input class="easyui-textbox"   name="student.users.email" ></td>
            </tr>
            <tr>
              <td>描述：</td>
		            <td colspan="3"> <textarea  name="student.users.description" style="width: 451;height: 72" cols=72 rows=5></textarea>  </td>
            </tr>
        	</table>
        </form>
        </div>
	            <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
	                <a class="easyui-linkbutton" id="saveStudent" data-options="iconCls:'icon-ok'" href="javascript:void(0)" >保存</a>
	                <a class="easyui-linkbutton" id="cancelStudent" data-options="iconCls:'icon-cancel'" href="javascript:void(0)">取消</a>
	            </div>
            </div>
        </div>
</body>
</html>
