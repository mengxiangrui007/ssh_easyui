<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	<!-- 自定义js加载 -->
	<script type="text/javascript" src="<%=basePath%>jsp/operation/student/js/studentShow.js"></script>
<!--  
此页面做为user.html弹出框中的子页面，使用href方式从外部引入，需将所有代码写在body标签中，
可省略body标签之外的所有内容。
保存按钮区域div放在放外层标签，不能以其他带样式标签包裹

以表格方式布局，设置table具体宽度，每列第一行单元格设置该列宽度
具体宽度根据内容而定，弹出框宽度根据table宽度而定
-->
<!-- 内容 -->
        <div class="easyui-layout" data-options="fit:true">
            <!-- <div data-options="region:'east',split:true" style="width:100px"></div> -->
            <div id="addStudent" data-options="region:'center'" style="padding:10px;">
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
                <td>入学日期：</td>
                <td><input class= "easyui-datebox"   name="student.startTime" data-options="editable:false"></td>
            </tr>
            <tr>
            	<td>角色：</td>
            	<td>
           		 	<select id="roleChoice" style="width:124px"  name="student.users.role.id" data-options="required:true,panelHeight:100"></select>
				    <div id="sp">
				        <div style="color:#99BBE8;background:#fafafa;padding:5px;">选择一个角色</div>
				        <div style="padding:10px">
				            <input type="radio"  value="3"><span>普通用户</span><br/>
				            <input type="radio" value="2"><span>普通管理员</span><br/>
				        </div>
				    </div>
            	</td>
            	<td style="display: none;">所在院系：</td>
            	<td style="display: none;">
            		<input id="departmentChoice" name="student.department.id" data-options="required:true" style="width:124px"/>
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
