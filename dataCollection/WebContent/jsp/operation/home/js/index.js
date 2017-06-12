/*
	主页加载方法
	@eric
*/
//系统时间显示
setInterval("document.getElementById('nowTime').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",1000);
var setting = {
	data: {
		simpleData: {
			/*
			 * 确定 zTree 初始化时的节点数据、异步加载时的节点数据、或 addNodes 方法中输入的 newNodes 数据是否采用简单数据模式 (Array)
			       不需要用户再把数据库中取出的 List 强行转换为复杂的 JSON 嵌套格式
			 */
			enable: true
		}
	},
	view: {
		selectedMulti: false//设置是否允许同时选中多个节点
	},
	callback: {
		onClick:function(e, id, node){//用于捕获节点被点击的事件回调函数
			var zTree = $.fn.zTree.getZTreeObj("menuTree");//zTree  专门提供的根据 treeId 获取 zTree 对象的方法。
			if(node.isParent) {//node JSON被点击的节点 JSON 数据对象
				zTree.expandNode();
			} else {
				addTabs(node.name, node.file);
			}
		}
	}
};

$(function() {
	var zTree = $.fn.zTree.getZTreeObj("menuTree");
	$.ajax({
		   type: "POST",
		   url: "./menu.queryUserMenu.action",
		   success: function(result){
			   var zNodes = JSON.parse(result);
			   $.fn.zTree.init($("#menuTree"), setting, zNodes);
				//zTree 初始化方法，创建 zTree 必须使用此方法
				var zTree = $.fn.zTree.getZTreeObj("menuTree");
				////zTree  专门提供的根据 treeId 获取 zTree 对象的方法。
		   }
		});
	//中间部分tab
	$('#tabs').tabs({  
		border:false,//设置为true时，显示选项卡容器边框。
		fit:true,//设置为true时，选项卡的大小将铺满它所在的容器。
		onSelect: function(title, index){//onSelect title,index 用户在选择一个选项卡面板的时候触发 
			var treeNode = zTree.getNodeByParam("name", title, null);//根据节点数据的属性搜索，获取条件完全匹配的节点数据 JSON 对象集合
			zTree.selectNode(treeNode);//v3.x 支持同时选中多个节点。
		}
	}); 
	
	$('.index_panel').panel({  
	  width:300,  
	  height:200,  
	  closable:true,//定义是否显示关闭按钮。
	  minimizable:true,//定义是否显示最小化按钮。
	  title: 'My Panel'
	});
	
});

//添加一个选项卡面板 
function addTabs(title, url, icon){
	if(!$('#tabs').tabs('exists', title)){
		$('#tabs').tabs('add',{  
			title:title,  
			content:'<iframe src="'+url+'" name="'+title+'" frameBorder="0" border="0" scrolling="no" style="width: 100%; height: 100%;"/>',//选项卡面板的内容。  
			closable:true
		});
	} else {
		$('#tabs').tabs('select', title);
	}
}

/**
 * 注销用户
 */
function removeUser(){
	var rootPath=getRootPath();
	$.ajax({
		   type: "POST",
		   url: "./login.loginout.action",
		   success: function(result){
			   var dataObject = JSON.parse(result);
			   if(dataObject.Flag){
				   window.location.href=rootPath+"/jsp/login.jsp"
			   }else{
				   $.messager.show({
	                   title: '错误提示',
	                   msg: dataObject.Message
	              });
			   }
		   }
		});
}