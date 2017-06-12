$(function(){
	var  srcItem = $parent("iframe[name='创建调查项目问题']").attr("src");
	var parames = parse_url(srcItem);
	$("#itemTitle").append(parames['name']);
	$("#itemId").val(parames['id']);
})
	//以下为创建调查项目
var aSort = new Array(new Array(0,1));
		
		function additem(id)
		{
			var ipoll = id;
			var iitem = aSort[aSort.length-1].length+1;
			aSort[ipoll].push(iitem);
			document.getElementById("nn").value = iitem;
			var s;
			s= "<div style='margin-top:8px;' id='tritem0_"+iitem+"' class='style_hui_14'>";
			s = s + "<input type='text' name=item" + ipoll + "_" + iitem + " style='width:400px; height:25px;   line-height:25px; border: 1px solid #777777; background-color:#FFEFDF' >&nbsp;";
			s= s + "&nbsp;选项分数&nbsp;&nbsp;<span class='style_hong_12'>*</span>&nbsp;<input type='text' name='itemScore"+ipoll + "_" + iitem+"'  class='easyui-numberbox numberbox-f validatebox-text' value='0' style='width:30px; height:25px; line-height:25px; border:1px solid #777777; background-color:#FFEFDF' data-options='min:0,precision:0,max:100'></input>"
			s = s + "&nbsp;<img src='images/cha.gif' align='absmiddle' style='cursor:pointer;'  onclick='removeitem(tritem" + ipoll + "_" + iitem + ")' >"
			//s = s + "<input type=button value='删除此项' onclick='removeitem(tritem" + ipoll + "_" + iitem + ")'>";
			s = s + "</div>"
			
			var newNode = document.createElement("div");
			newNode.innerHTML = s;
		
			//board.appendChild(newNode);
			var addbutton = document.getElementById("tritem0");
			addbutton.parentNode.insertBefore(newNode,addbutton);
		
		}
			function removeitem(id)
			{
				id.parentNode.removeChild(id);
			}
		
			function check()
			{
				if(document.form.elements["title"].value=="")
				{ 
					$parent.messager.alert("提示","请填写问题标题", "info");
					document.form.elements["title"].focus();
					return false;
				}
				
				if(document.form.elements["choice"][0].checked==false && document.form.elements["choice"][1].checked==false && document.form.elements["choice"][2].checked==false)
				{
					$parent.messager.alert("提示","请选择问题类型", "info");
					return false; 
				}
				//选择的类型
				var choiceValue = $("input[name='choice']:checked").val();
				if(choiceValue != 3){
					if(document.form.elements["item0_0"].value=="" || document.form.elements["item0_1"].value=="")
					{
						$parent.messager.alert("提示","请填写至少2个问题选项！", "info");
						document.form.elements["item0_0"].focus();
						return false; 
					}else{
						var itemArr =$("input[name^='item0']");
						$(itemArr).each(function(i){
							if($(this).val().length==0){
								$parent.messager.alert("提示","第"+(i+1)+"个“问题选项”不得为空！", "info");
								document.form.elements[$(this).attr("name")].focus();
								return false; 
							}
						 });
						createItemQuestion();
					}
				}else{
					createItemQuestion();
				}
				//document.form.submit()
			}
			
	
			
			function check2()
			{
				if(document.form2.elements["num"].value=="0")
				{ 
					$parent.messager.alert("提示","至少添加一个问题才可以创建调查项目！", "info");
					return false;
				}
				else{
					var questionSore = 0;
					var questionSoreArr = $("span[name='questionScore']");
					$(questionSoreArr).each(function(i) {
						questionSore = questionSore + parseInt($(this).last().text());
					});
					if(questionSore < 100){
						$parent.messager.alert("提示","您创建的调查问题总分数小于100！", "info");
					}
					else if(questionSore > 100){
						$parent.messager.alert("提示","您创建的调查问题总分数大于100，请重新创建！", "info");
					}else{
						//保存表单
						saveItem();
					}
				}
			}
			//定义一个问题对象
			function QuestionItemsObject(orderNo,name,type,score,questionOptionses){
				this.orderNo = orderNo;
				this.name = name;
				this.type = type;
				this.score = score;
				this.questionOptionses = questionOptionses;
			}
			
			//定义一个问题项对象
			function QuestionOptionsesObject(orderNo,name,score){
				this.orderNo = orderNo;
				this.name = name;
				this.score = score;
			}
			
			//保存调查问题项
			function saveItem(){
				var jsonData = {};//定义一个JSON的对象
				jsonData["id"] = parseInt($("#itemId").val());
				var questionItems = new Array();//问题JSON对象数组
				var questionTrArr =  $("#saveItem").find("tr[class='questionTr']");
				$(questionTrArr).each(function(obj, i, args) {
					var questionOrderNo = parseInt($(this).find("span[name='questionOrderNo']").text());
					var questionName = $(this).find("span[name='questionName']").text();
					var questionScore = parseInt($(this).find("span[name='questionScore']").last().text());
					var questionType = parseInt($(this).find("input[name='questionType']").val());
					var nextTr = $(this).next("tr");
					var questionOptionses = new Array();
					if(questionType!=3){
						var optionArr = $(nextTr).find("input");
						$(optionArr).each(function(i) {
							var optionValue = $(this).val();
							var optionScore = parseInt($(this).next("font").last().text());
							questionOptionses.push(new QuestionOptionsesObject(i+1,optionValue,optionScore));
						});
					}else{
						questionOptionses.push(new QuestionOptionsesObject(0,"0",0));
					}
					questionItems.push(new QuestionItemsObject(questionOrderNo,questionName,questionType,questionScore,questionOptionses))
				});
				
				//questionItems.push(new QuestionItemsObject(1, "最喜爱的老师?",1,20,questionOptionses))
				//questionOptionses.push(new QuestionOptionsesObject(1,"满意",20));
				//questionOptionses.push(new QuestionOptionsesObject(2,"不满意",0));
				jsonData["questions"]  = questionItems;
				$parent.messager.confirm('新增调查问题','您确认要为"'+$("#itemTitle").text()+'"添加这些问题吗？',function(r){    
					if(r){
						//保存
						$.ajax({
							  type: "POST",
							  url: "./item.insertQuestionOption.action",
							  data : {  
			                      'jsonData' : JSON.stringify(jsonData) 
			                  },success: function(msg){
			                	  var data=JSON.parse(msg);
			                	  if(data.Flag){
			                		  $parent.messager.alert("提示",data.Message, "info");
			                		  $parent("iframe[name='创建调查项目问题']").attr("src", "jsp/operation/item/itemManage.jsp").attr("name","调查项目管理");
			                	  }else{
			                		  $parent.messager.alert("提示",data.Message, "warning");
			                	  }
			                  }
						 });
					}
				});
			}
			
			//创建调查问题项
			function createItemQuestion(){
				//标题
				var titleValue = $("#title").val();
				//分数
				var scoreValue = parseInt($('#score').numberbox('getValue'));
				//选择的类型
				var choiceValue = $("input[name='choice']:checked").val();
				//获取个数
				var numValue =  parseInt($("input[name='num']").val())+1;
				//error souGou, So write DOM numValue
				$("input[name='num']").val(numValue);
				//问题项
				var itemArr =$("input[name^='item0']");
				//问题项分数
				var itemScoreArr =$("input[name^='itemScore0']");
				//获取选项分数
				var totalItemScore = 0;
				$(itemScoreArr).each(function(i) {
					totalItemScore = totalItemScore + parseInt($(this).val());
				});
				//如果大于 选项总分数 > 问题分数
				if(totalItemScore > scoreValue){
					$parent.messager.alert("提示","选项总分数已大于问题分数！", "info");
					return false;
				}else if(totalItemScore < scoreValue){
					$parent.messager.alert("提示","选项总分数小于问题分数！", "info");
					return false;
				}
				//追加代码
				var strAppend = undefined;
				//创建Item
				var itemAppend = "";
				if(choiceValue == 1){
					  $(itemArr).each(function(i){
						  var itemScore_Index = $(this).attr("name").split("_")[1];
						  var itemTmp = "<input type='radio' name='"+numValue+"' value='"+$(this).val()+"'>"
						  +$(this).val()+"&nbsp;&nbsp;&nbsp;<font color='red'><b>"+$("input[name='itemScore0_"+itemScore_Index+"']").val()+"分</b></font><br> ";
						  itemAppend = itemAppend+itemTmp;
					  });
					strAppend = 	
						  "<tr class='questionTr'><td height='30' bgcolor='#EAEAEA' class='style_hui_14' style='padding-left:10px;'>"
				+"问题<span name='questionOrderNo'>"+numValue+"</span>：<span name='questionName'>"+titleValue+"</span>&nbsp;&nbsp;&nbsp;<span  name='questionScore'><font color='red'><b>"+scoreValue+"分</b></font></span>&nbsp;[单选]<input type='hidden' name='questionType' value='1'/></td>"
			  +"</tr>"
			 +" <tr>"
				+"<td height='40' valign='top' class='style_hui_14' style='padding:10px; line-height:28px;'>"
				+ itemAppend
				+"</td>"
			  "</tr>";
				}else if(choiceValue == 2){
					  $(itemArr).each(function(i){
						  var itemScore_Index = $(this).attr("name").split("_")[1];
						  var itemTmp = "<input type='checkbox' name='"+numValue+i+"' value='"+$(this).val()+"'>"
						  +$(this).val() +"&nbsp;&nbsp;&nbsp;<font color='red'><b>"+$("input[name='itemScore0_"+itemScore_Index+"']").val()+"分</b></font><br> ";
						  itemAppend = itemAppend+itemTmp;
					  });
					strAppend = 	
						  "<tr class='questionTr'><td height='30' bgcolor='#EAEAEA' class='style_hui_14' style='padding-left:10px;'>"
				+"问题<span name='questionOrderNo'>"+numValue+"</span>：<span name='questionName'>"+titleValue+"</span>&nbsp;&nbsp;&nbsp;<span  name='questionScore'><font color='red'><b>"+scoreValue+"分</b></font></span>&nbsp;[多选]<input type='hidden' name='questionType' value='2'/></td>"
			  +"</tr>"
			 +" <tr>"
				+"<td height='40' valign='top' class='style_hui_14' style='padding:10px; line-height:28px;'>"
				+ itemAppend
				+"</td>"
			  "</tr>";
				
				}
				else if(choiceValue == 3){
					strAppend =
						"<tr class='questionTr'><td height='30' bgcolor='#EAEAEA' class='style_hui_14' style='padding-left:10px;'>"
						+"问题<span name='questionOrderNo'>"+numValue+"</span>：<span name='questionName'>"+titleValue+"</span>&nbsp;&nbsp;[问题]<input type='hidden' name='questionType' value='3'/></td></td>"
						  +"</tr>"
						 +" <tr>"
						 +"<td height='40' valign='top' class='style_hui_14' style='padding:10px; line-height:28px;'>"
						 +"<textarea  name='"+numValue+"'  cols=72 rows=5></textarea>"
					+"<br>"
					+"</td>"
                         +"</tr>";
				}
				$("#createQuestionTr").before(strAppend);
			}
			//显示“添加问题”
			function showQuestionItem() {
				$("#questionIntem").attr("style",""); 
				$("#questionScoreTr").attr("style",""); 
				$("input[name^='item0']").val("");
			}
		
			//移除“添加问题”
			function removeQuestionItem() {
				$("#questionIntem").attr("style","display:none;"); 
				$("#questionScoreTr").attr("style","display:none;"); 
				$("input[name^='item0']").val("");
			}
			//empty Form
			function clearForm() {
				$("input[name^='item0']").val("");
				$("#title").val("");
				$('#score').numberbox('setValue', 0);
			}
			
			