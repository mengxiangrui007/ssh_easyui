$(function(){
	$.ajax({
		   type: "post",
		   url: "./department.queryUserDepartment.action",
		   success: function(msg){
			   var dataObject = JSON.parse(msg);
			 if(dataObject.flag == true){
				$("#addStudent").find("td[style='display: none;']").show();
				createCombobox(dataObject.data);
			   }else{
				   $("#addStudent").find("td[style='display: none;']").hide();
			   }
		   }
		});
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
		$("#parent_win").dialog("close");
	});
	//取消
	$("#cancelStudent").on("click", function(){
		$("#parent_win").dialog("close");
	});
	//保存
	$("#saveStudent").on("click", function(){
			$parent.messager.confirm('新增学生','您确认要添加此学生吗？',function(r){    
			    if (r){    
			    	 $('#fm').form('submit',{
					        url: "./student.insertStudent.action",
					        onSubmit: function(){
					            return $(this).form('validate');
					        },
					        success: function(result){
					            var result = JSON.parse(result);
					            if (result.Flag){
					                $('#parent_win').dialog('close');  
					                $("iframe[name='学生管理']").attr("src", $("iframe[name='学生管理']").attr("src"));
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
	});
});

