$(function(){
	var rootPath=getRootPath();
	 var editingId;
	 redraw();
		$.ajax({
			   type: "post",
			   url: "./department.queryAllConditionDepartment.action",
			   success: function(msg){
				 if(msg!=null){
					 var dataObject = eval("("+ msg +")");
					 var treeData = new TreeHelper(dataObject).dataTransferer();
					 createGrid(treeData);
				 }
				 else{
					 return;
				 }
			   }
			});
	 //创建表格
	 function createGrid(dataObject) {
			$("#tt").treegrid({
				height:$("#body").height()-5,
				width:$('#content').width() * 2/3,
				idField:'id',//指明哪一个字段是标识字段。
				singleSelect:true, //如果为true，则只允许选择一行。
				nowrap:true,    //如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
				fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
				rownumbers:true,//如果为true，则显示一个行号列。
				data:dataObject,
				treeField: 'text',
				columns:[[
					    {field:'id',hidden:true},
						{field:'text',title:'院系名称',width:220,editor:'text',resizable:false,halign:"center"},
						{field:'paramType',title:'院系类型',width:40,resizable:false, align:"center",halign:"center",
							formatter: function(value,row,index){
								if (row.paramType == 1){
									return '党政管理机构';
								}else if(row.paramType == 2){
									return '教学院系';
								}
								else if(row.paramType == 3){
									return '教学辅助机构';
								}
								else if(row.paramType == 4){
									return '研究机构';
								}
								else if(row.paramType == 5){
									return '附属单位';
								}
							}
						},
						{field:'paramObject.loaderName',title:'院系领导',width:40,resizable:false,align:"center",halign:"center",
							formatter: function(value,row,index){
								if(row.paramObject){
									return row.paramObject;
								}
							}
						}
					]],
			/*	onLoadSuccess: function(row){
					$(this).treegrid('enableDnd', row?row.id:null);
				},*/onDblClickRow:function(row){
			         if (editingId != undefined){
			            $('#tt').treegrid('select', editingId);
			             return;
			         }
			         if (row){
			             editingId = row.id
			             $('#tt').treegrid('beginEdit', editingId);
			         }
				},
				onClickRow:function(row){
					 if (editingId != undefined){
			        	 $('#tt').treegrid('cancelEdit', editingId);
			                editingId = undefined;
			            	$.messager.confirm("部门管理","您确认要修改此院系的信息吗？",function(r){    
							    if (r){
							    	alert();
							    }    
							}); 
			             return;
			         }
						$.ajax({
							   type: "post",
							   method:"get",
							   url: "./menu.queryDepartmentMenu.action?departmentId="+row.id,
							   success: function(msg){
								 if(msg!=null){
									 var dataObject = eval("("+ msg +")");
									 var treeData = new TreeHelper(dataObject).dataTransferer();
									  createTree(treeData)
								 }
								 else{
									 return;
								 }
							   }
							});
				}
			});
	}
	 //创建树
	 function createTree(treeData) {
		 $('#menuTree').tree({ 
			    data: treeData,  
			    checkbox:true,
			    animate:true,
		 });
	}
	 
	//判定数组是否还有某个数
	 Array.prototype.in_array = function(e)  
	 {  
	 	for(i=0;i<this.length && this[i]!=e;i++);  
	 	return !(i==this.length);  
	 } 
	//新增弹出框
	$("#save").on("click", function(){
		$parent("#parent_win").window({
			width:274,
			height:225,
			href:'jsp/operation/user/addUser.jsp',
			title:'新增用户'
		});
	});
	//修改
	$("#update").on("click", function(){
		$parent.messager.alert("提示","update", "info");
	});
	//删除
	$("#delete").on("click", function(){
		$parent.messager.alert("提示","delete", "info");
	});
	//条件查询
	$("#queryTeacher").on("click",function(){
		var t = $('#department').combotree('tree');	// 获取树对象
		var n = t.tree('getChecked');	//获取选择的节点
		var departmentArr = new Array();
		for(var i=0,len=n.length; i<len; i++){
			if(n[i].id!=''){
				departmentArr.push(n[i].id);
			}
		}
		console.debug(departmentArr);
		$('#tt').datagrid('load',{//向后台额外传值
			'departmentArr':JSON.stringify(departmentArr),
			'teacher.teachNo': $("#teachNo").val().trim(),
			'teacher.users.fullname': $("#fullname").val().trim()
		});
	});
	function viewDetail(date, id){
		$parent.messager.alert("提示","查询详细", "info");
	}
})
//监听窗口大小变化
	window.onresize = function(){
		setTimeout(domresize,300);
	};
	//改变表格宽高
	function domresize(){
		$('#tt').datagrid('resize',{  
			height:$("#body").height()-5,
			width:$('#content').width() * 2/3
		});
		 redraw();
	}
	
	//自动调整
	function redraw(){
		var win_width = $("#content").width();
		$('#content').layout('panel', 'east').panel('resize',{width:win_width* 1/3,height:$("#content").height()-5});
		$('#content').layout('panel', 'center').panel('resize',{width:win_width* 2/3,height:$("#content").height()-5});
		$('#content').layout('resize');
	}
	 //获取菜单
	 function addDepartmentMenu(){
		 var nodes =  $('#menuTree').tree('getChecked');
		 var menuArr = [];
		 $(nodes).each(function(i){
				 menuArr.push(this.id);
		 });
		 if(menuArr.length>0){
			 var row = $('#tt').treegrid('getSelected');
			 $.ajax({
				   type: "post",
				   url: "./menu.insertDepartmentMenu.action",
				   data:{
					   'departmentId':parseInt(row.id),
					   "menuStr" :menuArr.join(),
				   },
				   success: function(msg){
					   var data=JSON.parse(msg);
					   $parent.messager.alert("提示",data.Message, "info");
				   }
				});
		 }
	 }
