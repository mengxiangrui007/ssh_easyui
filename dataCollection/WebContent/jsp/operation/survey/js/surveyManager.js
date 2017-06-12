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
		url:"./survey.querySurvey.action",  
		singleSelect:false, //如果为true，则只允许选择一行。
		nowrap:true,    //如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
		fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
		rownumbers:true,//如果为true，则显示一个行号列。
		showPageList:true,//定义是否显示页面列表 true
		pageSize:15,
		columns:[[
		    {field:'ck',checkbox:true},
		    {field:'id',hidden:true},
			{field:'item.name',title:'调查名称',halign:"center",width:75,align:"center",resizable:false,
				formatter: function(value,row,index){
					if(row.item){
						return row.item.name;
					}
				}	
			},
			{field:'type',title:'调查目标',halign:"center",width:75,align:"center",resizable:false,
				formatter: function(value,row,index){
					if(row.type){
						if(row.type == 1){
							return "调查学生";
						}else if(row.type ==2)
						{
							return "调查教职工";
						}
						else if(row.type == 3){
							return "调查部门";
						}
					}
				}	
			},
			{field:'users.fullname',title:'发布人姓名',halign:"center",width:75,align:"center",resizable:false,
				formatter: function(value,row,index){
					if(row.users){
						return row.users.fullname;
					}
				}	
			},
			{field:'startTime',title:'调查开始时间',halign:"center",width:95,align:"center",resizable:false},
			{field:'endTime',title:'调查结束时间',halign:"center",width:95,align:"center",resizable:false},
			{field:'isSurveyComplete',title:'调查是否完成',halign:"center",width:100,align:"center",resizable:false,
				formatter: function(value,row,index){
						if(row.isSurveyComplete == 0){
							return "<a href='javascript:void(0)' onclick=showChart("+row.id+") title='未完成'><font color='red'><b>未完成</b></font></a>";
						}else if(row.isSurveyComplete == 1){
							return "<a href='javascript:void(0)' onclick=showChart("+row.id+") title='已完成'><font color='green'><b>已完成</b></font></a>";
						}
					},
					styler: function(value,row,index){
						if (row.isSurveyComplete == 0){
							return 'background-color:#ffee00;color:red;';
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
		showSurvey('icon-save','新建调查');
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
	$("#querySurvey").on("click",function(){
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
			'survey.item.name': $("#surveyName").val().trim()
		});
	});
	//----------------------以下为增加删除操作------------------------
	//取消
	$("#cancelSurvey").on("click", function(){
		$("#showSurvey").dialog("close");
	});
	//保存
	$("#saveSurvey").on("click", function(){
		var Flag = $("#fm").form('validate');
		if(Flag){
			$parent.messager.confirm('新增调查','您确认要添加这个调查吗？',function(r){    
			    if (r){
			    	var itemChoicevalue =$("#itemChoice").combobox('getValue');
			    	var targetValue = $("input[name='target']:checked").val();
			    	var targetChoice = "";
			    	var targetDepartment = "";
			    	if(targetValue == 1){
			    		targetChoice = $("#studentValue").val();
			    	}else if(targetValue == 2){
			    		targetChoice = $("#teacherValue").val();
			    	}else if(targetValue == 3){
			    		targetDepartment = 	$("#departmentChoiceName").combobox('getValue');
			    	}
			    	//参加的班级与专业
			 	   var attendValue = [];
					var t = $('#classSurvey').combotree('tree');	// 获取树对象
					var nodes = t.tree('getChecked');	//获取选择的节点
				   $(nodes).each(function(){
					   if(this.type=="3"){
						   var node = this.id;
						   var nodeArr = node.split(":");
							   attendValue.push(new AttendObject(nodeArr[0],nodeArr[1],nodeArr[2],nodeArr[3]));
					   }
					});
				   var teacherDept = $("#teacherChioce").combobox('getValues');
				   var teacherDepartment = [];
				   $(teacherDept).each(function(){
					   teacherDepartment.push(parseInt(this));
				   });
				   
				   var startSurveyTime = $('#startSurveyTime').datetimebox('getValue');	
				   var endSurveyTime = $("#endSurveyTime").datetimebox('getValue');	
				   $.ajax({
					   type: "post",
					   url: "./survey.insertSurvey.action",
				       data:{
				        	"surveyTarget.item.id":parseInt(itemChoicevalue),
				        	"surveyTarget.type":parseInt(targetValue),
				        	"surveyTarget.targetUserId":parseInt(targetChoice.length!=0?targetChoice:"0"),
				        	"surveyTarget.departmentId":parseInt(targetDepartment.length!=0?targetDepartment:"0"),
				        	"surveyTarget.startTime":startSurveyTime,
				        	"surveyTarget.endTime":endSurveyTime,
				        	"surveyTarget.departmentTeacher":teacherDepartment.join(),
				        	"jsonData":JSON.stringify(attendValue) 
				        },
					   success: function(msg){
				            var result = JSON.parse(msg);
				            if (result.Flag){
				            	$("#showSurvey").dialog("close");
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

	
	//调查项目Combo
	 $('#surveyChoice').combo({
		 editable:false,
		 panelHeight:105
   });
   $('#sp').appendTo($('#surveyChoice').combo('panel'));
   $('#sp input').click(function(){
       var v = $(this).val();
	   var targetChoiceArr = $("td[name='targetChoice']");
	   var targetChoice1 = targetChoiceArr[0];
	   var targetChoice2 = targetChoiceArr[1];
       if(v=="1"){
    	   $(targetChoice1).text("选择学生：");
    	   $(targetChoice2).text("");
    	   $(targetChoice2).append("<input type='text' id='studentName' data-options='required:true'  onclick='openDialog();' readonly='true' style='width:150px'/>"
                	+"<input type='hidden' id='studentValue'/>");
       }else if(v=="2"){
    	   $(targetChoice1).text("选择教师");
    	   $(targetChoice2).text("");
    	   $(targetChoice2).append("<input type='text' id='teacherName' data-options='required:true'  onclick='openTeacherDialog();' readonly='true' style='width:150px'/>"
                	+"<input type='hidden' id='teacherValue'/>");
       }else if(v == "3"){
    	   $(targetChoice1).text("选择院系");
    	   $(targetChoice2).text("");
    	   $(targetChoice2).append("<input type='text' id='departmentChoiceName'  onclick='openDepartmentDialog();'   style='width:150px'/>"
                	+"<input type='hidden' id='departmentChoiceValue'/>");
       }
       var s = $(this).next('span').text();
       $('#surveyChoice').combo('setValue', v).combo('setText', s).combo('hidePanel');
   });
   $("#itemChoice").click(function() {
	   $.ajax({
		   type: "post",
		   url: "./item.queryHaveQuestionItem.action",
		   success: function(msg){
			   createItemCombobox(JSON.parse(msg));
		   }
		});
   });
   
function aaaa(){
	   $.ajax({
		   type: "post",
		   url: "./item.queryHaveQuestionItem.action",
		   success: function(msg){
			   createItemCombobox(JSON.parse(msg));
		   }
		});
}

   $("#departmentSurvey").click(function() {
	   $.ajax({
		   type: "post",
		   url: "./department.queryALLDepartment.action",
		   success: function(msg){
			   createDepartmentCombobox(JSON.parse(msg));
		   }
		});
   });
   
   $("#professionalSurvey").click(function() {
	   createProfession();
   });
})
	

	/**
	 * 创建年级
	 */
	function createGread(){
	var t = $('#professionalSurvey').combotree('tree');	// 获取树对象
	var nodes = t.tree('getChecked');	//获取选择的节点
	   var comboDepartmentValues = [];
	   var comboProfessionalValues = [];
	   $(nodes).each(function() {
		   	if(this.type==0){
		   		comboDepartmentValues.push(this.id);
		   	}else if(this.type==1){
		    	 var pnode = t.tree('getParent',this.target);
		    	 	if(!comboDepartmentValues.in_array(pnode.id)){
		    	 		comboDepartmentValues.push(pnode.id);
				   	}
		   		comboProfessionalValues.push(this.id);
		   	}
 	 });
	   if(comboProfessionalValues.length>0){
		   $.ajax({
			   type: "post",
			   url: "./student.queryGread.action",
			   data:{
				   'departmentArr':JSON.stringify(comboDepartmentValues),
				   'professionalIdStr' :comboProfessionalValues.join()
			   },
			   success: function(msg){
				   var data=JSON.parse(msg);
				   createGreadCombobox(data);
			   }
			});
	   }
		
  }
	/**
	 * 创建班级
	 */
//判定数组是否还有某个数
Array.prototype.in_array = function(e)  
{  
	for(i=0;i<this.length && this[i]!=e;i++);  
	return !(i==this.length);  
} 

	function createClass(){
		   var comboProfessionalValues = [];
			var t = $('#greadSurvey').combotree('tree');	// 获取树对象
			var comboGreadValues = [];
			var comboDepartmentValues = [];
			var nodes = t.tree('getChecked');	//获取选择的节点
		   $(nodes).each(function(){
			   	if(this.type==0){
			   		comboDepartmentValues.push(this.id);
			   	}else if(this.type==1){
			    	 var pnode = t.tree('getParent',this.target);
			    	 	if(!comboDepartmentValues.in_array(pnode.id)){
			    	 		comboDepartmentValues.push(pnode.id);
					   	}
			   		comboProfessionalValues.push(this.id);
			   	}else if(this.type==2){
			   	 var pnode = t.tree('getParent',this.target);
			   	if(!comboProfessionalValues.in_array(pnode.id)){
			   		comboProfessionalValues.push(pnode.id);
			   	 var pnode1 = t.tree('getParent',this.target);
		    	 	if(!comboDepartmentValues.in_array(pnode1.id)){
		    	 		comboDepartmentValues.push(pnode1.id);
				   	}
			   	}
			   		comboGreadValues.push(this.id);
			   	}
		   });
		   if(comboGreadValues.length>0){
			   $.ajax({
				   type: "post",
				   url: "./student.queryClass.action",
				   data:{
					   'departmentArr':JSON.stringify(comboDepartmentValues),
					   "professionalIdStr" :comboProfessionalValues.join(),
					   "greadIdStr":comboGreadValues.join()
				   },
				   success: function(msg){
					   var data=JSON.parse(msg);
					   createClassCombobox(data);
				   }
				});
		   }else{
			   $("#classSurvey").combotree(
						  'loadData',
						    new Array());
		   }
	}
	//创建专业
	function createProfession(){
	 var departmentArr = new Array();
	 var comboValue = $("#departmentSurvey").combo('getValues');	
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

	/**
	 * @param row 格式化
	 * @returns {String}
	 */
	function formatItem(row){   

	    var s = '<span style="font-weight:bold; ">' + row.text + '</span><br/>' +
	    	'<span style="color:#888">' + row.desc + '</span>';
	    return s;
	}
	//创建调查项
	function createItemCombobox(comboboxData){
		$('#itemChoice').combobox({ 
			 valueField:'id',    
			 textField:'text',
			 data:comboboxData,
			 panelHeight:150,
			 panelWidth:300,
			 formatter: formatItem,
			 multiple:false,
			 editable:false
		});
	}
	
	//创建院系选项
	function createDepartmentCombobox(comboboxData){
		$('#departmentSurvey').combobox({ 
			 valueField:'id',    
			 textField:'text',
			 panelHeight:150,
			 data:comboboxData,
			 multiple:true,
			 editable:false,
			 onSelect: function(param){
					createProfession();
				}
		});
	}
	
	//创建专业
	function createProfessionalCombobox(comboboxData){
		$('#professionalSurvey').combotree({ 
			 panelWidth:300,
			 panelHeight:150,
			 data:comboboxData,
			 animate:true,
			 lines:true,
			 multiple:true,
			 onCheck: function(param){
				 createGread();
				}
		});
	}
	//创建年级
	function createGreadCombobox(treeData){
			$('#greadSurvey').combotree({ 
				 panelWidth:300,
				 panelHeight:150,
				 data:treeData,
				 animate:true,
				 lines:true,
				 multiple:true,
				 onCheck: function(param){
					 createClass();
					}
			});
			if(treeData.length == 0){
				 $("#classSurvey").combotree(
						  'loadData',
						    new Array());
			}
		//$('#greadSurvey').combotree('loadData', treeData);
	}

	
	/**
	 * 报表展示
	 */
	function showChart(id){
		$parent("iframe[name='调查管理']").attr("src", "jsp/operation/chart/chartManage.jsp?id="+id).attr("name","图表展示");
	}
	
	
	//创建班级
	function createClassCombobox(treeData){
		$('#classSurvey').combotree({ 
			 panelWidth:300,
			 panelHeight:150,
			 data:treeData,
			 animate:true,
			 lines:true,
			 multiple:true
		}
		);
		//$('#greadSurvey').combotree('loadData', treeData);
	}
	
	//弹出页面
	function showSurvey(iconClsImage,iconClsName){
		$("#showSurvey").window({
			iconCls:iconClsImage,
			width:600,
			height:500,
			title:iconClsName
		});
	}
	
	//定义一个问题项对象
	function QuestionOptionsesObject(orderNo,name,score){
		this.orderNo = orderNo;
		this.name = name;
		this.score = score;
	}
	
	/**定义一个参加对象
	 * @param professional
	 * @param gread
	 * @param clazz
	 */
	function AttendObject(departmentId,professionalId,gread,class_){
		this.departmentId = departmentId;
		this.professionalId  = professionalId;
		this.gread = gread;
		this.class_ = class_;
	}
	/******************************绑定院系**************************************/
	
	/**
	 * 院系
	 */
	function openDepartmentDialog() {
		   $.ajax({
			   type: "post",
			   url: "./department.queryALLDepartment.action",
			   success: function(msg){
				   createChoiceDepartmentCombobox(JSON.parse(msg));
			   }
			});
	}
	
	/**
	 * 院系
	 */
	function openTheacherDialog() {
		   $.ajax({
			   type: "post",
			   url: "./department.queryALLDepartment.action",
			   success: function(msg){
				   createChoiceTeacherCombobox(JSON.parse(msg));
			   }
			});
	}
	
	//创建院系选项
	function createChoiceDepartmentCombobox(comboboxData){
		$('#departmentChoiceName').combobox({ 
			 valueField:'id',    
			 textField:'text',
			 panelHeight:150,
			 data:comboboxData,
			 multiple:false,
			 editable:false
		});
	}
	
	//创建教师选项
	function createChoiceTeacherCombobox(comboboxData){
		$('#teacherChioce').combobox({ 
			 valueField:'id',    
			 textField:'text',
			 panelHeight:150,
			 data:comboboxData,
			 multiple:true,
			 editable:false
		});
	}
	
	
	function createCombobox(comboboxData){
		$('#departmentChoiceName').combobox({ 
			 valueField:'id',    
			 textField:'text',
			 panelHeight:150,
			 data:comboboxData,
			 multiple:false,
			 editable:false
		});
	}
	/******************************绑定学生**************************************/
	//打开学生弹框
	function openDialog() {
		$("#showStudnet").window({
			title:"绑定学生",
			width:800,
			height:300,
		});
		//显示学生
		 dialogGridStudent();
	}
	/******************************绑定教师**************************************/
	function openTeacherDialog(){
		$("#showTeacher").window({
			title:"绑定教师",
			width:800,
			height:300,
		});
		//显示教师
		dialogGridTeacher();
	
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
