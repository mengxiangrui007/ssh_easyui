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
		url:"./item.queryItem.action",  
		singleSelect:false, //如果为true，则只允许选择一行。
		nowrap:true,    //如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
		fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
		rownumbers:true,//如果为true，则显示一个行号列。
		showPageList:true,//定义是否显示页面列表 true
		pageSize:15,
		columns:[[
		    {field:'ck',checkbox:true},
		    {field:'id',hidden:true},
			{field:'name',title:'调查项目名称',halign:"center",width:146,align:"center",resizable:false},
			{field:'itemType',title:'调查项目类别',halign:"center",width:75,align:"center",resizable:false,
				formatter: function(value,row,index){
					if(row.itemType){
						 if(row.itemType== 1 ){
							 return "满意机关";
						 }
						 else if(row.itemType == 2 ){
							 return "部门调查";
						 }else if(row.itemType == 3 ){
							 return "教师调查";
						 }else if(row.itemType == 4 ){
							 return "学生调查";
						 }else{
							 return "-";
						 }
						}
					}	
			},
			{field:'createTime',title:'创建调查项目时间',halign:"center",width:95,align:"center",resizable:false},
			{field:'users.fullname',title:'创建调查项目用户姓名',halign:"center",width:100,align:"center",resizable:false,
				formatter: function(value,row,index){
					if(row.users){
						return row.users.fullname;
						}
					}
			},
			{field:'department.name',title:'创建调查项目部门',halign:"center",width:165,align:"center",resizable:false,
				formatter: function(value,row,index){
					if(row.department){
						return row.department.name;
						}
					}
			},
			{field:'isHaveQuestion',title:'创建调查项目问题',halign:"center",width:98,align:"center",resizable:false,
				formatter: function(value,row,index){
					if(row.isHaveQuestion == 0){
						return  "<a href='javascript:void(0)' onclick=createQuestion() title='未创建'><font color='red'><b>未创建</b></font></a>";
						}else {
						return "<font color='green'><b>已创建</b></font>";
						}
					},
					styler: function(value,row,index){
						if (row.isHaveQuestion == 0){
							return 'background-color:#ffee00;color:red;';
						}
					}
			},
			{field:'description',title:'调查项目描述',halign:"center",width:165,align:"center",resizable:false,
				formatter: function(value,row,index){
					if(row.description){
						return  "<a  title='"+row.description+"'>"+row.description+"</a> ";
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
		},*//*,onClickCell:function(index,field,value){
			if(field == 'isHaveQuestion'){
			   var row = $('#tt').datagrid('getSelected');
			   console.debug($("iframe[name='调查项目管理']").attr("src"));
				 $("iframe[name='调查项目管理']").attr("src", "jsp/operation/question/questionShow.jsp");
			}
		}*/
	});
	//新增弹出框
	$("#save").on("click", function(){
		showItem('icon-save','新建调查项');
	});
	
	//调查项目Combo
	 $('#itemChoice').combo({
        editable:false
    });
    $('#sp').appendTo($('#itemChoice').combo('panel'));
    $('#sp input').click(function(){
        var v = $(this).val();
        var s = $(this).next('span').text();
        $('#itemChoice').combo('setValue', v).combo('setText', s).combo('hidePanel');
    });
	//修改
	$("#update").on("click", function(){
            var row = $('#tt').datagrid('getSelected');
            if (row){
            	showStudent('icon-edit','修改学生');
            	$.ajax({
         		   type: "post",
         		   url: "./student.showStudent.action",
         		  data : {  
                      'student.id' : row.id  
                  },  
         		   success: function(msg){
         			   var dataObject = JSON.parse(msg);
         			   console.debug(dataObject);
         			  var name=' ';//获取标签的name值 
         			 var strs=new Array();//声明一个数组，用于字符串分隔 
         			 $('#fm input,textarea').each(function(){ 
         			         name=$(this).attr('name'); 
         			         if(name) { 
	         			         strs=name.split(".");
	         			         var NameStr = "dataObject";//JSON名称
	         			         $(strs).each(function(i){
	         			        	 if(i != 0){//除去第一个
	         			        		 NameStr=NameStr+"[\""+this+"\"]";
	         			        	 }
	         			         });
	         			         console.debug(eval(NameStr));
	         			         if($(this).attr('type')=='radio'){
	         			        	$('input[type=\'radio\'][name=\''+name+'\'][value=\''+eval(NameStr)+'\']').attr('checked','checked'); 
	         			         } else{
	         			        	$(this).val(eval(NameStr)); 
	         			         }
         			         } 
         			   }); 
         			   
         			 /* $('#fm').form('load',{
         				 "student.users.fullname":'sdfsd'
         			  });*/
         		   }
         		});
	            //url = 'update_user.php?id='+row.id;
            }else{
            	$parent.messager.alert("提示","请选择您要修改的学生", "question");
            }
	});
	//删除
	$("#delete").on("click", function(){
		$parent.messager.alert("提示","delete", "info");
	});
	//条件查询
	$("#queryItem").on("click",function(){
		var t = $('#department').combotree('tree');	// 获取树对象
		var n = t.tree('getChecked');	//获取选择的节点
		var departmentArr = new Array();
		for(var i=0,len=n.length; i<len; i++){
			if(n[i].id!=''){
				departmentArr.push(n[i].id);
			}
		}
		$('#tt').datagrid('load',{//向后台额外传值
			'departmentArr':JSON.stringify(departmentArr),
			'item.name': $("#itemName").val().trim()
		});
	});
	//----------------------以下为增加删除操作------------------------
 	//dialog操作
	$("#addUser_cancel").on("click", function(){
		$("#showItem").dialog("close");
	});
	//取消
	$("#cancelItem").on("click", function(){
		$("#showItem").dialog("close");
	});
	//保存
	$("#saveItem").on("click", function(){
		var Flag = $("#fm").form('validate');
		if(Flag){
			$parent.messager.confirm('新增调查项目','您确认要添加"'+$("input[name='item.name']").val()+'"这个项目吗？',function(r){    
			    if (r){    
			    	 $('#fm').form('submit',{
					        url: "./item.insertItem.action",
					        onSubmit: function(){
					            return $(this).form('validate');
					        },
					        success: function(result){
					            var result = JSON.parse(result);
					            if (result.Flag){
					                $('#showItem').dialog('close');  
					                $('#tt').datagrid('reload');
					                //$("iframe[name='学生管理']").attr("src", $("iframe[name='学生管理']").attr("src"));
					            } else {
					            	   $.messager.show({
						                    title: '错误提示',
						                    msg: result.Message
						               });
					            }
					        }
					    });
			    }
			});
		}
	});

})
	function createQuestion(){
	   var row = $('#tt').datagrid('getSelected');
	 	$parent("iframe[name='调查项目管理']").attr("src", "jsp/operation/question/questionShow.jsp?id="+row.id+"&name="+row.name).attr("name","创建调查项目问题");
	}
	function viewDetail(date, id){
		$parent.messager.alert("提示","查询详细", "info");
	}
	//创建院系选项
	function createCombobox(comboboxData){
		$('#departmentChoice').combobox({ 
			 valueField:'id',    
			 textField:'text',
			 panelHeight:JSONLength(comboboxData) * 14 + 50,
			 data:comboboxData,
			 multiple:false,
			 editable:false
		});
	}
	
	//弹出页面
	function showItem(iconClsImage,iconClsName){
		$("#showItem").window({
			iconCls:iconClsImage,
			width:600,
			height:255,
			title:iconClsName
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
