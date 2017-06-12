$(function(){
	var rootPath=getRootPath();
	$("#tt").datagrid({
		height:$("#body").height()-$('#search_area').height()-5,
		width:$('#body').width(),
		idField:'id',//指明哪一个字段是标识字段。
		url:"./user.queryUser.action",  
		singleSelect:false, //如果为true，则只允许选择一行。
		nowrap:true,    //如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
		fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
		rownumbers:true,//如果为true，则显示一个行号列。
		showPageList:true,//定义是否显示页面列表 true
		pageSize:15,
		columns:[[
		    {field:'ck',checkbox:true},
		    {field:'id',hidden:true},
			{field:'account',title:'账号',halign:"center",width:146,align:"center",resizable:false},
			{field:'fullname',title:'姓名',halign:"center",width:170,align:"center",resizable:false},
			{field:'sexy',title:'性别',halign:"center",width:34,align:"center",resizable:false,
				formatter: function(value,row,index){
					if (row.sexy == 1){
						return '男';
					}else if(row.sexy == 0){
						return '女';
					}else{
						return '';
					}
				}
			},
			{field:'email',title:'邮箱',halign:"center",width:165,align:"center",resizable:false},
			{field:'mobilephone',title:'手机号',halign:"center",width:160,align:"center",resizable:false},
			{field:'accountCreated',title:'创建时间',halign:"center",width:138,align:"center",resizable:false},
			{field:'type',title:'角色',halign:"center",width:98,align:"center",resizable:false,
				formatter: function(value,row,index){
						if(row.role){
						return row.role.name;
					}
				}
				},
			{field:'accountLocked',title:'是否锁定',halign:"center",width:72,align:"center",resizable:false,
				formatter: function(value,row,index){
					var imageName;
					var titleName;
					if (row.accountLocked == 1){
						imageName = "lock.png"
						titleName = "锁定";
					}else if(row.accountLocked == 0){
						imageName = "unlock.png"
						titleName = "未锁定";
					}
					//return "<a href='javascript:void(0)' class='easyui-linkbutton'><span></span></a>";
					return "<a  title='"+titleName+"' href='javascript:void(0)' ><img name='lock'  src='"+rootPath+"/jsp/resource/easyui/themes/icons/"+imageName+"'  width='16px' heigth='16px'></a>"
				}
			}
			]],
		toolbar:'#tt_btn', //顶部工具栏的DataGrid面板
        pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
       /* onDblClickRow:function(rowIndex, rowData){
			
			 * 在用户点击一行的时候触发，参数包括：
i				index：点击的行的索引值，该索引值从0开始。
r					row：对应于点击行的记录。
			 
			viewDetail(rowData.userId);
		},*/onClickCell:function(index,field,value){
			if(field == 'accountLocked'){
				$parent.messager.confirm('用户状态','您确认要修改锁定状态吗？',function(r){    
				    if (r){    
				 	   var row = $('#tt').datagrid('getSelected');
					   if (row){  
						   $.ajax({
							   type: "POST",
							   url: "./user.updateUserAccountLocked.action",
							   data: {
								   "user.id":row.id,
								   "user.accountLocked":value
							   },
							   success: function(msg){
								 var  data ;
								 data =JSON.parse(msg);
								  $('#tt').datagrid('updateRow',{
										index:index,
										row: {
											accountLocked: data.Message
										}
									});
							   }
							});
					   } 
				    }    
				});  
			}
		}
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
	$("#queryUser").on("click",function(){
		$('#tt').datagrid('load',{//向后台额外传值
			'user.account': $("#account").val().trim(),
			'user.fullname': $("#fullname").val().trim()
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
