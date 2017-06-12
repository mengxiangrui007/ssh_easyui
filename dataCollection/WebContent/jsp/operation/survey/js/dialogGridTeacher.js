

$(function() {
	$("#queryTeacher").on("click",function(){
		$('#tt2').datagrid('load',{	
			'teacher.teachNo': $("#teachNo").val().trim(),
			'teacher.users.fullname': $("#fullname").val().trim()
			});
	});
})
function dialogGridTeacher(){	
$("#tt2").datagrid({
	height:$("#showTeacher").height()-$('#search_area2').height()-5,
	idField:'id',//指明哪一个字段是标识字段。
	url:"./teacher.queryTeacher.action",  
	singleSelect:true, //如果为true，则只允许选择一行。
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
    pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
    onDblClickRow:function(rowIndex, rowData){
    	$("#teacherName").val(rowData['users']['fullname']);
    	$("#teacherValue").val(rowData['id']);
    	$('#tt2').datagrid('reload');
    	$('#showTeacher').window('close');
	},
});}

