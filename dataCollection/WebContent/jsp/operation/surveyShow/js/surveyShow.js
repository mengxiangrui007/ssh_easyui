$(function(){
	var  srcItem = $parent("iframe[name='填写调查']").attr("src");
	var parames = parse_url(srcItem);
	var jsonObject = JSON.parse(parames['jsonData']);
	var targetId = parames['targetId'];
	$("#itemTitle").text(jsonObject['item']['name']);
	$("#itemId").val(jsonObject['item']['id']);
	$("#surveyId").val(jsonObject['id']);
	$("#surveyType").val(jsonObject['type']);
	$("#targetId").val(targetId);
	var questionsArr = jsonObject['item']['questions'];
	$(questionsArr).each(function(i){
		//追加代码
		var strAppend = undefined;
		//创建Item
		var itemAppend = "";
		var orderNo = this.orderNo;
		if(this.type == 1){
			var questionOptionsesArr = this.questionOptionses;
			  $(questionOptionsesArr).each(function(i){
				 var option =  questionOptionsesArr[i];
				  var itemTmp = "<input type='radio' name="+orderNo+" value='"+option.options.id+"'>"
				  +option.options.name+"&nbsp;&nbsp;&nbsp;<font color='red'><b>"+option.options.score+"分</b></font><br> ";
				  itemAppend = itemAppend+itemTmp;
			  });
			strAppend = 	
				  "<tr class='questionTr'><td height='30' bgcolor='#EAEAEA' class='style_hui_14' style='padding-left:10px;'>"
		+"问题<span name='questionOrderNo'>"+(i+1)+"</span>：<span name='questionName'>"+this.name+"</span>&nbsp;&nbsp;&nbsp;<span  name='questionScore'><font color='red'><b>"+this.score+"分</b></font></span>&nbsp;[单选]<input type='hidden' name='questionType' value='1'/><input type='hidden' name='questionId' value="+this.id+"></td>"
	  +"</tr>"
	 +" <tr>"
		+"<td height='40' valign='top' class='style_hui_14' style='padding:10px; line-height:28px;'>"
		+ itemAppend
		+"</td>"
	  "</tr>";
		}else if(this.type == 2){
			var questionOptionsesArr = this.questionOptionses;
			  $(questionOptionsesArr).each(function(i){
				  var option =  questionOptionsesArr[i];
				  var itemTmp = "<input type='checkbox' name='"+option.options.name+i+"' value='"+option.options.id+"'>"
				  +option.options.name +"&nbsp;&nbsp;&nbsp;<font color='red'><b>"+option.options.score+"分</b></font><br> ";
				  itemAppend = itemAppend+itemTmp;
			  });
			strAppend = 	
				  "<tr class='questionTr'><td height='30' bgcolor='#EAEAEA' class='style_hui_14' style='padding-left:10px;'>"
		+"问题<span name='questionOrderNo'>"+(i+1)+"</span>：<span name='questionName'>"+this.name+"</span>&nbsp;&nbsp;&nbsp;<span  name='questionScore'><font color='red'><b>"+this.score+"分</b></font></span>&nbsp;[多选]<input type='hidden' name='questionType' value='2'/></td><input type='hidden' name='questionId' value="+this.id+">"
	  +"</tr>"
	 +" <tr>"
		+"<td height='40' valign='top' class='style_hui_14' style='padding:10px; line-height:28px;'>"
		+ itemAppend
		+"</td>"
	  "</tr>";
		
		}
		else if(this.type == 3){
			strAppend =
				"<tr class='questionTr'><td height='30' bgcolor='#EAEAEA' class='style_hui_14' style='padding-left:10px;'>"
				+"问题<span name='questionOrderNo'>"+(i+1)+"</span>：<span name='questionName'>"+this.name+"</span>&nbsp;&nbsp;[问题]<input type='hidden' name='questionType' value='3'/><input type='hidden' name='questionId' value="+this.id+"></td></td>"
				  +"</tr>"
				 +" <tr>"
				 +"<td height='40' valign='top' class='style_hui_14' style='padding:10px; line-height:28px;'>"
				 +"<textarea  name='questionResult'  cols=72 rows=5></textarea>"
			+"<br>"
			+"</td>"
                 +"</tr>";
		}
		$("#createQuestionTr").before(strAppend);
	});
})

	function QuestionOptionsesObject(questionId,score,result,surveyId){
		this.questionId = questionId;
		this.score= score;
		this.result = result;
		this.surveyId =surveyId;
	}
			function check2()
		{
			var questionTrArr =  $("#saveSurvey").find("tr[class='questionTr']");
			var surveyId = $("#surveyId").val();
			var surveyType = $("#surveyType").val();
			var targetId = $("#targetId").val();
			var objectOptionsesArr = [];
			var Flag = true;
			$(questionTrArr).each(function(obj, i, args) {
				var questionName = $(this).find("span[name='questionName']").text();
				var questionType = parseInt($(this).find("input[name='questionType']").val());
				var questionId = $(this).find("input[name='questionId']").val();
				var nextTr = $(this).next("tr");
				if(questionType==1){
					var optionChecked = "";
					optionChecked = $(nextTr).find("input:checked");
					if(optionChecked.length == 0){
						$parent.messager.alert("提示","第"+(obj+1)+"个单选框请选择", "info");
						Flag = false;
						return false; 
					}
					objectOptionsesArr.push(new QuestionOptionsesObject(questionId,parseInt($(optionChecked).next("font").last().text()),"",parseInt(surveyId)))
				}else if(questionType==2){
					var optionArr = "";
					optionArr = $(nextTr).find("input:checked");
					if(optionArr.length==0){
						$parent.messager.alert("提示","第"+(obj+1)+"个复选框请选择", "info");
						Flag = false;
						return false; 
					}
					var tatotalScore = 0;
					$(optionArr).each(function(i) {
						var optionScore = parseInt($(this).next("font").last().text());
						tatotalScore = tatotalScore + optionScore;
					});
					objectOptionsesArr.push(new QuestionOptionsesObject(questionId,tatotalScore,"",parseInt(surveyId)));
				}else if(questionType==3){
					var questionResult ="";
					questionResult =$(this).next("tr").find("textarea[name='questionResult']").val();
					if(questionResult.length==0){
						$parent.messager.alert("提示","第"+(obj+1)+"个问题请填写", "info");
						Flag = false;
						return false; 
					}
					objectOptionsesArr.push(new QuestionOptionsesObject(questionId,0,questionResult,parseInt(surveyId)));
				}
			});
			if(Flag){
				$.ajax({
					  type: "POST",
					  url: "./score.insertScore.action",
					  data : {  
	                    'jsonData' : JSON.stringify(objectOptionsesArr),
	                    'targetId':parseInt(targetId),
	                    'surveyType':parseInt(surveyType)
	                },success: function(msg){
	              	  var data=JSON.parse(msg);
	              	  if(data.Flag){
	              		  $parent.messager.alert("提示",data.Message, "info");
	            		  $parent("iframe[name='填写调查']").attr("src", "jsp/operation/surveyShow/surveyShowManage.jsp").attr("name","参与调查");
	              	  }else{
	              		  $parent.messager.alert("提示",data.Message, "warning");
	              	  }
	                }
				 });
				
			}
		}

			
			