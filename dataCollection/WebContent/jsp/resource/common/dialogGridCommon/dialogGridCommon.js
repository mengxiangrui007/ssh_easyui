function dialogGridCommon(options) {
	var input = null;
	var hsae_dialog_grid = null;
	if (isEmpty(options.inputId)) {
		options.inputId = new Date().getTime();
		input = $("body").append("<div id='dialogGrid"+options.inputId+"'></div>");
	} else {
		//将input设置为只读，并且指定获取到焦点时的事件
		options.inputId = options.inputId.replace(".","_");
		input = $("#" + options.inputId).attr('readonly', 'readOnly').focus(
				function() {
					_open();
				});
		//绘制对话框
		input.after("<div id='dialogGrid"+options.inputId+"'></div>");
	}	
	if (!isEmpty(options.inputValueId) && !isEmpty(options.inputId)){
		input.before("<input type='hidden' id='"+options.inputValueId.replace(".","_")+"' name='"+options.inputValueId+"' value=''>");
	}
	
	var whereLength = 0;
	if(!isEmpty(options.whereModel) && options.whereModel.length>0){
		$("#dialogGrid"+options.inputId).append("<div id='whereDiv"+options.inputId+"'></div>");
		whereLength = options.whereModel.length;
		$("#whereDiv"+options.inputId).css({"padding":"5px","height":"auto"}).append(getWhereTable(options.whereModel,options.inputId));
	}
	var colLength = 0;
	if(!isEmpty(options.colModel) && options.colModel.length>0){
		colLength = options.colModel.length;
	}
	//绘制gird的table
	$("#dialogGrid"+options.inputId).append("<table id='grid"+options.inputId+"' " +(whereLength > 0 ? ("data-options=\"toolbar:'#whereDiv"+options.inputId+"'\"") : "" )+"></table>");
	var instance = {
		options : initGridOptions()
	};
	//声明一个om-grid
	easyloader.load("datagrid", function() {
		$("#grid"+options.inputId).datagrid(options);
	});
	if (!isEmpty(options.whereModel) && options.whereModel.length > 0) {
		easyloader.load("linkbutton", function() {
			$("#btnSerch"+options.inputId).linkbutton({  
				iconCls:"icon-search"  
			}); 
		});		 
		$("#btnSerch"+options.inputId).live("click", function() {
			$("#grid"+options.inputId).datagrid('load',getExtraDataObj(options));
		});
	}
	var _rowIndex=-1,_rowData={};
	function initGridOptions() {
		options = $.extend({
			title:null,
			inputId:"",
			inputValueId:"",
			onRowClickCallback:null,//
			cancelClickCallBack:null,
			method : 'POST',
			width : 450,
			height : 200,
			showIndex : true,
			autoFilter : true,
			pageSize:5,
			pageList:[5,10,15,20,25],
			textField : "",
			dialogGridExtraData:{},
			textFieldSeparator:" ",//text分隔符号
			valueField : "",
			columns : _getColums(),
			fit:true,
	        pagination:true,
			whereModel : [],//查询条件
			autoClose:false, //是否自动关闭	
			singleSelect : true,
			_rowIndex:-1,
			_rowData:{},
			onClickRow : function(rowIndex,rowData) {
				if(options.onRowClickCallback && options.onRowClickCallback!=undefined && options.autoClose){
					options.onRowClickCallback(rowIndex, rowData);
				}else if(options.autoClose){
					setValueText(rowData,rowIndex);
					hsae_dialog_grid.close();
				} else {
					_rowIndex = rowIndex;
					_rowData = rowData;
				} 
			}
		}, options || {});
		return options;
	}
	function setValueText(rowData,rowIndex){
		setValue(rowData,rowIndex);
		setText(rowData,rowIndex);
	}
	
	function _getColums(){
		var colums = new Array();
		if(!isEmpty(options.colModel) && options.colModel.length>0){
			colums.push(options.colModel);
		}else{
			colums.push(new Array());
		}
		return colums;
	}
	
	function _getButtonsArray(){
		if(options.autoClose){
			return [];
		}else{
			return [{
			     text : "取消", 
			     click : function () {
			    	 if(options.cancelClickCallBack && options.cancelClickCallBack!=undefined){
			    		 options.cancelClickCallBack(); 
			    	 } else {
			    		 hsae_dialog_grid.close();
			    	 }			    	 
			     }
			},{
			     text : "确定", 
			     click : function () {
			    	 if(options.onRowClickCallback && options.onRowClickCallback!=undefined){
			    		 options.onRowClickCallback(_rowIndex,_rowData); 
			    	 } else {
			    		setValueText(_rowData,_rowIndex);
			    		hsae_dialog_grid.close();
			    	 }
			     }
			 }];
		}
	}
	
	function setValue(rowData,rowIndex) {
		var value = _setValue(rowData,rowIndex);
		input.attr('_trueValue',value);
		$("#"+options.inputValueId.replace(".","_")).val(value);
    }
	
	function _setValue(rowData,rowIndex) {
		return rowData[options.valueField];
    }

    function getValue() {
    	return input.attr('_trueValue');
    }
    
    function setText(rowData,rowIndex) {
		var text = _setText(rowData,rowIndex);
		input.val(isEmpty(text) || text == undefined ? "" :text);	
    }
    
    function _setText(rowData,rowIndex) {
		var text = "";
		if(options.textField.indexOf(",")!=-1){
			var textArray = options.textField.split(",");
			$.each(textArray,function(i,n){
				if(i>0){
					text +=options.textFieldSeparator;
				}
				text += rowData[textArray[i]];
			});
		}else{
			text = rowData[options.textField];
		}
		return text;	
    }
    function _open(){
    	hsae_dialog_grid.popup();
		if ($('.pControl input').width() > 40) {
			$('.pControl input').width(40);
		}
		$("#grid"+options.inputId).datagrid('load',getExtraDataObj(options));
    }
    function getText() {
    	return input.val();
    }
	instance = {
		options : initDialogOptions(),
		getValue:function(){
			return getValue();
		},
		setValueText:function(value,text){
			input.attr('_trueValue',value);
			$("#"+options.inputValueId.replace(".","_")).val(value);
			input.val(isEmpty(text) || text == undefined ? "" :text);
		},
		close:function(){
			hsae_dialog_grid.close();
		},
		open:function(){
			_open();
		},
		setDialogGridExtraData:function(obj){
			options.dialogGridExtraData = obj;
		}
	};
	//声明一个om-dialog
	$("#dialogGrid"+options.inputId).css("display","block");
	hsae_dialog_grid=new hsMDialog({
		"styles":{
			"width":getDialogWidth(options.width,colLength),
			"height":getDialogHeight(options.height, whereLength,options.autoClose),
			"background":true,
			"drag":true,
			"anchor":"c",
			"zindex":options.zindex,
			"margin":0
		},
		"title":options.dialogTitle,
		"context":$("#dialogGrid"+options.inputId)[0],
		"buttons":options.buttons
	});
	function initDialogOptions() {
		options = $.extend({
			dialogTitle:"请选择",
			autoOpen : false,
			modal : true,
			draggable : true,
			resizable:false,
			zindex:10001,
			buttons : _getButtonsArray()
		}, options || {});
		return options;
	}	
	//instance = inst;
	return instance;
}

function  isEmpty (strInput) {
    return strInput == null || (strInput + "") == "" || strInput==undefined;
}

function getWhereTable(whereCol,inputId) {
	var html = "<table style='width:100%'>";
	$.each(whereCol,function(i, n) {
		html += "<tr>";
		html += ("<td style='width:15%;text-align:right'>"+ n.header + "</td>");
		if (i == 0) {
			html += ("<td style='width:60%'><input style='width:99%' type='text' id='"+ n.id + "' name='" + n.name + "' value=''/></td>");
			html += ("<td rowspan='"+whereCol.length+"' style='padding-left:5px'>");
			html +=	("	<a id='btnSerch"+inputId+"'  href='###'>搜索</a>");
			html +=	("</td>");
		} else {
			html += ("<td style='width:70%'><input style='width:99%' type='text' id='"+ n.id + "' name='" + n.name + "' value=''/></td>");
		}
		html += "</tr>";
	});
	return html;
}

function getExtraDataObj(options){
	var obj = {};
	$.each(options.whereModel,function(i, n){
		obj[n.name] = $("#"+n.id).val();
	});
	$.extend(obj,options.dialogGridExtraData);
	return obj;
}

function getDialogWidth(width, length) {
	return (width + 2)<450 ? 450 :(width + 2);
}

function getDialogHeight(height, length,autoClose) {
	if(autoClose){
		return length * 20 + height + 5;
	}else{
		return length * 20 + height + 25;
	}
}

Array.prototype.indexOf = function(val) {
	for ( var i = 0; i < this.length; i++) {
		if (this[i] == val) {
			return i;
		}
	}
	return -1;
};
Array.prototype.removevalue = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
		this.splice(index, 1);
	}
};