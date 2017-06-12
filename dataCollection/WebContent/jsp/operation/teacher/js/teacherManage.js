$(function(){
	$.ajax({
		   type: "post",
		   url: "./department.queryUserDepartment.action",
		   success: function(msg){
			   var dataObject = JSON.parse(msg);
			 if(dataObject.flag == true){
				$("#search_area").find("td[style='display: none;']").show();
				   var treeData = new TreeHelper(dataObject.data).dataTransferer();
				   $("#department").combotree(
						  'loadData',
						    treeData);
			   }else{
				   $("#search_area").find("td[style='display: none;']").hide();
			   }
		   }
		});
	var rootPath=getRootPath();
	$("#tt").datagrid({
		height:$("#body").height()-$('#search_area').height()-5,
		width:$('#body').width(),
		idField:'id',//指明哪一个字段是标识字段。
		url:"./teacher.queryTeacher.action",  
		singleSelect:false, //如果为true，则只允许选择一行。
		nowrap:true,    //如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
		fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
		rownumbers:true,//如果为true，则显示一个行号列。
		showPageList:true,//定义是否显示页面列表 true
		pageSize:15,
		columns:[[
		    {field:'ck',checkbox:true},
		    {field:'id',hidden:true},
			{field:'teachNo',title:'工号',halign:"center",width:146,align:"center",resizable:false},
			{field:'users.fullname',title:'姓名',halign:"center",width:170,align:"center",resizable:false,
				formatter: function(value,row,index){
					if(row.users){
					return row.users.fullname;
				}
			}
			},
			{field:'department.name',title:'所属院系',halign:"center",width:165,align:"center",resizable:false,
				formatter: function(value,row,index){
					if(row.department){
					return row.department.name;
					}
				}
			},
			{field:'users.email',title:'邮箱',halign:"center",width:170,align:"center",resizable:false,
				formatter: function(value,row,index){
					if(row.users){
					return row.users.email;
				}
			}
			}
			]],
		toolbar:'#tt_btn', //顶部工具栏的DataGrid面板
        pagination:true//如果为true，则在DataGrid控件底部显示分页工具栏。
       /* onDblClickRow:function(rowIndex, rowData){
			
			 * 在用户点击一行的时候触发，参数包括：
i				index：点击的行的索引值，该索引值从0开始。
r					row：对应于点击行的记录。
			 
			viewDetail(rowData.userId);
		},*/
	});
	
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
			height:$("#body").height()-$('#search_area').height()-5,
			width:$('#body').width()
		});
	}
