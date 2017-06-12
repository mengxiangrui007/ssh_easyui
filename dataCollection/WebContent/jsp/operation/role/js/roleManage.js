$(function(){
	var rootPath=getRootPath();
	 var editingId;
	 redraw();
		$.ajax({
			   type: "post",
			   url: "./department.queryALLDepartment.action",
			   success: function(msg){
				 if(msg!=null){
					 var dataObject = JSON.parse(msg);
					 var treeDepart = new TreeHelper(dataObject).dataTransferer();
					 createDepartTree(treeDepart);
				 }
				 else{
					 return;
				 }
			   }
			});
		
		$.ajax({
			   type: "post",
			   url: "./role.queryDepartmentRole.action",
			   success: function(msg){
				 if(msg!=null){
					 var dataObject = JSON.parse(msg);
					 var treeRole = new TreeHelper(dataObject).dataTransferer();
					 createRole(treeRole);
				 }
				 else{
					 return;
				 }
			   }
			});
})
 //创建院系树
	 function createDepartTree(treeDepart) {
		 $('#departmentTree').tree({ 
			    data: treeDepart,  
			    animate:true,
		 });
	}
//创建角色树
	function createRole(treeRole){
		$('#roleTree').tree({ 
		    data: treeRole,  
		    animate:true,
		    onClick: function(node){
		    	$.ajax({
					   method:"get",
					   data:{
						   "roleMenu.role.id":node.id,
						   "roleMenu.departmentId":$('#departmentTree').tree('getSelected').id
					   },
					   url: "./menu.queryRoleMenu.action",
					   success: function(msg){
						 if(msg!=null){
							 var dataObject = JSON.parse(msg);
							 var treeMenu = new TreeHelper(dataObject).dataTransferer();
							 createMenu(treeMenu);
						 }
						 else{
							 return;
						 }
					   }
					});
			}

	 });
	}
	//创建菜单树
	function createMenu(treeMenu){
		$('#menuTree').tree({ 
		    data: treeMenu,  
		    animate:true,
		    checkbox:true
	 });
	}
function addRoleMenu(){

	 var nodes =  $('#menuTree').tree('getChecked');
	 var departmentId = $('#departmentTree').tree('getSelected');
	 var roleId =  $('#roleTree').tree('getSelected');
	 var menuArr = [];
	 $(nodes).each(function(i){
			 menuArr.push(this.id);
	 });
	 if(menuArr.length>0){
		 $.ajax({
			   type: "post",
			   url: "./menu.insertRoleMenu.action",
			   data:{
				   'departmentId':parseInt(departmentId.id),
				   'roleId':parseInt(roleId.id),
				   "menuStr" :menuArr.join(),
			   },
			   success: function(msg){
				   var data=JSON.parse(msg);
				   $parent.messager.alert("提示",data.Message, "info");
			   }
			});
	 }

}

//监听窗口大小变化
	window.onresize = function(){
		setTimeout(redraw,300);
	};
	//改变表格宽高
	//自动调整
		function redraw(){
			var win_width = $("#content").width();
			$('#content').layout('panel', 'west').panel('resize',{width:win_width/3,height:$("#content").height()-5});
			$('#content').layout('panel', 'center').panel('resize',{width:win_width/3,height:$("#content").height()-5});
			$('#content').layout('panel', 'east').panel('resize',{width:win_width/3,height:$("#content").height()-5});
			$('#content').layout('resize');
		}
