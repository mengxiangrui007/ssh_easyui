$(function(){
	var rootPath=getRootPath();
	$("#tt").datagrid({
		height:$("#body").height()-$('#search_area').height()-5,
		width:$('#body').width(),
		idField:'id',//指明哪一个字段是标识字段。
		url:"./survey.queryUserIsHaveSurvey.action",  
		singleSelect:false, //如果为true，则只允许选择一行。
		nowrap:true,    //如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
		fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
		rownumbers:true,//如果为true，则显示一个行号列。
		showPageList:false,//定义是否显示页面列表 true
		pageSize:100,
		columns:[[
		    {field:'id',hidden:true},
			{field:'name',title:'调查目标',halign:"center",width:75,align:"center",resizable:false},
			{field:'startTime',title:'开始调查时间',halign:"center",width:75,align:"center",resizable:false},
			{field:'endTime',title:'结束调查时间',halign:"center",width:75,align:"center",resizable:false},
			{field:'itemId',title:'调查',halign:"center",width:75,align:"center",resizable:false,
				formatter: function(value,row,index){
							return "<a href='javascript:void(0)' onclick=showSurvry("+row.id+","+row.targetId+","+row.type+") title='参与调查'><font color='green'><b>参与调查</b></font></a>";
				}	
			}
			]],
       /* onDblClickRow:function(rowIndex, rowData){
			
			 * 在用户点击一行的时候触发，参数包括：
i				index：点击的行的索引值，该索引值从0开始。
r					row：对应于点击行的记录。
			 
			viewDetail(rowData.userId);
		},*//*,onClickCell:function(index,field,value){
			if(field == 'isHaveQuestion'){
			   var row = $('#tt').datagrid('getSelected');
			   console.debug($("iframe[name='调查项目管理']").attr("src"));
				 $("iframe[name='调查项目管理']").attr("src", "jsp/operation/question/questionShow.jsp");
			}
		}*/
	});
})
	function showSurvry(surveyId,targetId,type){
	$.ajax({
		   type: "post",
		   url: "./survey.queryUserSurvey.action",
		   data:{
			   "surveyId":parseInt(surveyId)
		   },
		   success: function(msg){
				$parent("iframe[name='参与调查']").attr("src", "jsp/operation/surveyShow/surveyShow.jsp?jsonData="+msg+"&targetId="+targetId+"&type="+type).attr("name","填写调查");
		   }
		});
   }
//监听窗口大小变化
	window.onresize = function(){
		setTimeout(domresize,300);
	};
	//改变表格宽高
	function domresize(){
		//表格
		$('#tt').datagrid('resize',{  
			height:$("#body").height()-$('#search_area').height()-5,
			width:$('#body').width()
		});
		
	}
