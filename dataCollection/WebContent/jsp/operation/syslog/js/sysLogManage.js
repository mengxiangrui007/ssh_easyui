$(function(){
	var rootPath=getRootPath();
	$("#tt").datagrid({
		height:$("#body").height()-$('#search_area').height()-5,
		width:$('#body').width(),
		idField:'id',//指明哪一个字段是标识字段。
		url:"./syslog.querySysLog.action",  
		singleSelect:false, //如果为true，则只允许选择一行。
		nowrap:true,    //如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
		fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
		rownumbers:true,//如果为true，则显示一个行号列。
		showPageList:true,//定义是否显示页面列表 true
		pageSize:15,
		columns:[[
		    {field:'id',hidden:true},
			{field:'type',title:'日志类型',halign:"center",width:20,align:"center",resizable:false,
				formatter: function(value,row,index){
				if(row.type == 1){
					return "操作日志";
				  }else if(row.type == 2){
					return "安全日志";
				  }else if(row.type == 3){
					  return "异常日志";
				  }
			}
		    },
			{field:'operation',title:'操作路径',halign:"center",width:80,align:"center",resizable:false},
			{field:'userId',title:'用户编号',halign:"center",width:20,align:"center",resizable:false},
			{field:'ipAddress',title:'IP地址',halign:"center",width:25,align:"center",resizable:false},
			{field:'operationTime',title:'操作时间',halign:"center",width:30,align:"center",resizable:false},
			{field:'context',title:'操作内容',halign:"center",width:80,align:"center",resizable:false,
				formatter: function(value,row,index){
					if(row.context.length > 20){
						return row.context;
					}
				}},
			]],
        pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
	});
	
	//条件查询
	$('#sysType').combobox({
		onSelect: function(param){
			$('#tt').datagrid('load',{//向后台额外传值
				'sysLog.type': param.value
			});
		}
	});
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
