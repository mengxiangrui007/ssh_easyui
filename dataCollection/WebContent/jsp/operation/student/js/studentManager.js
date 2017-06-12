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
		url:"./student.queryStudent.action",  
		singleSelect:false, //如果为true，则只允许选择一行。
		nowrap:true,    //如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
		fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
		rownumbers:true,//如果为true，则显示一个行号列。
		showPageList:true,//定义是否显示页面列表 true
		pageSize:15,
		columns:[[
		    {field:'ck',checkbox:true},
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
			{field:'professional.professionLevel',title:'学科层次',halign:"center",width:45,align:"center",resizable:false,
				formatter: function(value,row,index){
					if(row.professional){
						if(row.professional.professionLevel==1){
							return "本科";
						}
						if(row.professional.professionLevel==2){
							return "专科";
						}
				}
			}
			},
			{field:'gread',title:'年级',halign:"center",width:35,align:"center",resizable:false},
			{field:'professional.professionalSystem',title:'学制',halign:"center",width:30,align:"center",resizable:false,
				formatter: function(value,row,index){
					if(row.professional){
					return row.professional.professionalSystem;
				}
			}
			},
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
			},
			{field:'users.email',title:'邮箱',halign:"center",width:100,align:"center",resizable:false,
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
		showStudent('icon-save','新增学生');
		$.ajax({
			   type: "post",
	   url: "./department.queryDepartment.action",
	   success: function(msg){
		   var dataObject = JSON.parse(msg);
		 if(dataObject.flag == true){
			$("#showStudent").find("td[style='display: none;']").show();
			createCombobox(dataObject.data);
		   }else{
			   $("#showStudent").find("td[style='display: none;']").hide();
				   }
			   }
			});
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
	$("#queryTeacher").on("click",function(){
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
			'student.studentNo': $("#studentNo").val().trim(),
			'student.users.fullname': $("#fullname").val().trim(),
			"student.professional.professionalName":$("#professionalName").val().trim()
		});
	});
	//----------------------以下为增加删除操作------------------------
	//院系Combo
	 $('#roleChoice').combo({
         editable:false
     });
     $('#sp').appendTo($('#roleChoice').combo('panel'));
     $('#sp input').click(function(){
         var v = $(this).val();
         var s = $(this).next('span').text();
         $('#roleChoice').combo('setValue', v).combo('setText', s).combo('hidePanel');
     });
 	//dialog操作
	$("#addUser_cancel").on("click", function(){
		$("#showStudent").dialog("close");
	});
	//取消
	$("#cancelStudent").on("click", function(){
		$("#showStudent").dialog("close");
	});
	//保存
	$("#saveStudent").on("click", function(){
		var Flag = $("#fm").form('validate');
		if(Flag){
			$parent.messager.confirm('新增学生','您确认要添加"'+$("input[name='student.users.fullname']").val()+'"这个学生吗？',function(r){    
			    if (r){    
			    	 $('#fm').form('submit',{
					        url: "./student.insertStudent.action",
					        onSubmit: function(){
					            return $(this).form('validate');
					        },
					        success: function(result){
					            var result = JSON.parse(result);
					            if (result.Flag){
					                $('#showStudent').dialog('close');  
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
	function viewDetail(date, id){
		$parent.messager.alert("提示","查询详细", "info");
	}
	//创建院系选项
	function createCombobox(comboboxData){
		$('#departmentChoice').combobox({ 
			 valueField:'id',    
			 textField:'text',
			 panelHeight:150,
			 data:comboboxData,
			 multiple:false,
			 editable:false,
			 onSelect: function(param){
					createProfession();
				}
		});
	}
	
	//弹出页面
	function showStudent(iconClsImage,iconClsName){
		 $('#fm').form('clear');
		  $("#showStudent").find("td[style='display: none;']").hide();
		$("#showStudent").window({
			iconCls:iconClsImage,
			width:600,
			height:400,
			title:iconClsName
		});
	}
	//创建专业
	function createProfession(){
	 var departmentArr = new Array();
	 var comboValue = $("#departmentChoice").combo('getValues');	
	 $(comboValue).each(function() {
		  departmentArr.push(parseInt(this));
	});
	   $.ajax({
		   type: "post",
		   url: "./student.queryProfessional.action",
		   data:{
				'departmentArr':JSON.stringify(departmentArr)
		   },
		   success: function(msg){
			   createProfessionalCombobox(JSON.parse(msg));
		   }
		});
	}
	//创建专业
	function createProfessionalCombobox(comboboxData){
		$('#professionalChoice').combotree({ 
			 panelWidth:300,
			 panelHeight:150,
			 data:comboboxData,
			 animate:true,
			 lines:true,
			 multiple:false,
			 onSelect: function(param){
				 if(param["type"]==1){
					 $("#professionalValue").val(parseInt(param['id']));
				 }
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
