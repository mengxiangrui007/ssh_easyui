

$(function() {
	$("#queryStudent").on("click",function(){
		$('#tt1').datagrid('load',{//向后台额外传值
			'student.studentNo': $("#studentNo").val().trim(),
			'student.users.fullname': $("#fullname").val().trim(),
			"student.professional.professionalName":$("#professionalName").val().trim()
		});
	});
})
function dialogGridStudent(){	
	
$("#tt1").datagrid({
	height:$("#showStudnet").height()-$('#search_area1').height()-5,
	idField:'id',//指明哪一个字段是标识字段。
	url:"./student.queryStudent.action",  
	singleSelect:true, //如果为true，则只允许选择一行。
	nowrap:true,    //如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
	fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
	rownumbers:true,//如果为true，则显示一个行号列。
	showPageList:true,//定义是否显示页面列表 true
	pageSize:15,
	columns:[[
	    {field:'id',hidden:true},
		{field:'studentNo',title:'学号',halign:"center",width:130,align:"center",resizable:false},
		{field:'users.fullname',title:'姓名',halign:"center",width:100,align:"center",resizable:false,
			formatter: function(value,row,index){
				if(row.users){
				return row.users.fullname;
				}
			}
		},
		{field:'department.name',title:'所属院系',halign:"center",width:110,align:"center",resizable:false,
			formatter: function(value,row,index){
				if(row.department){
				return row.department.name;
				}
			}
		},
		{field:'professional.professionalName',title:'专业',halign:"center",width:200,align:"center",resizable:false,
			formatter: function(value,row,index){
				if(row.professional){
				return row.professional.professionalName;
			}
		}
		},
		{field:'gread',title:'年级',halign:"center",width:35,align:"center",resizable:false},
		{field:'class_',title:'班级',halign:"center",width:45,align:"center",resizable:false,
			formatter: function(value,row,index){
				if(row.class_){
					if(row.class_ == 1){
						return "一班";
					}
					if(row.class_ == 2){
						return "二班";
					}
					if(row.class_ == 3){
						return "三班";
					}
					if(row.class_ == 4){
						return "四班";
					}
					if(row.class_ == 5){
						return "五班";
					}
					if(row.class_ == 6){
						return "六班";
					}
					if(row.class_ == 7){
						return "七班";
					}
					if(row.class_ == 8){
						return "八班";
					}
					if(row.class_ == 9){
						return "九班";
					}
					if(row.class_ == 10){
						return "十班";
					}
				}
			}
		}
		]],
    pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
    onDblClickRow:function(rowIndex, rowData){
    	$("#studentName").val(rowData['users']['fullname']);
    	$("#studentValue").val(rowData['id']);
    	$('#dg').datagrid('reload');
    	$('#showStudnet').window('close');
	},
});}
