$(function(){
	var  srcItem = $parent("iframe[name='图表展示']").attr("src");
	var parames = parse_url(srcItem);
	var id = parames['id'];
	$.ajax({
		  type: "POST",
		  url: "./survey.queryDepartmentChart.action",
		  data : {  
          'survey.id' : parseInt(id)
      },success: function(msg){
    	  
      }
	 });
})
	