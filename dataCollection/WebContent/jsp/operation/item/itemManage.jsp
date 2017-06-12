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
	<script type="text/javascript" src="<%=basePath%>/jsp/operation/item/js/itemManager.js"></script>
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
          <td>&nbsp;调查项目名称：</td>
          <td><input   id="itemName"/></td>
          <td>
             &nbsp;&nbsp; <a  href="javascript:void(0)" id="queryItem" class="easyui-linkbutton my-search-button" iconCls="icon-search" plain="true">查询</a> 
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
      <a href="javascript:void(0)"  id="delete" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
   </div>
   <!-- 弹出框 -->
             <div id="showItem"  style="padding:10px;">
        <div class="easyui-layout" data-options="fit:true">
            <!-- <div data-options="region:'east',split:true" style="width:100px"></div> -->
             <div data-options="region:'center'" style="padding:10px;">
        <form id="fm" method="post" novalidate>
            <table cellpadding=5 cellspacing=0 width=100%  align="center" border="0">
          	 <tr>
                <td>调查项标题：</td>
                <td><input name="item.name"  class="easyui-validatebox" data-options="required:true" ></td>
            </tr>
            <tr>
            	  <td>调查项分类：</td>
                <td>
                	<select id="itemChoice" style="width:140px"  name="item.itemType" data-options="required:true,panelHeight:125"></select>
				    <div id="sp">
				        <div style="color:#99BBE8;background:#fafafa;padding:5px;">调查项类别：</div>
				        <div style="padding:10px">
				            <input type="radio" name="item.itemType" value="1"><span>满意机关</span><br/>
				            <input type="radio" name="item.itemType" value="2"><span>部门调查</span><br/>
			              <input type="radio" name="item.itemType" value="3"><span>教师调查</span><br/>
			               <input type="radio" name="item.itemType" value="4"><span>学生调查</span><br/>
				        </div>
				    </div>
                </td>
            </tr>
             <tr>
                  <td>调查项描述：</td>
		            <td colspan="3"> <textarea  name="item.description" style="width: 451;height: 72" cols=72 rows=5></textarea>  </td>
            </tr>
        	</table>
        </form>
        </div>
	            <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
	                <a class="easyui-linkbutton"  id="saveItem" data-options="iconCls:'icon-ok'" href="javascript:void(0)" >保存</a>
	                <a class="easyui-linkbutton" id="cancelItem" data-options="iconCls:'icon-cancel'" href="javascript:void(0)">取消</a>
	            </div>
            </div>
        </div>
</body>
</html>
