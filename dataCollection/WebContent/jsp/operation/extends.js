/*
	扩展easyui中的控件方法或修改其默认属性
	@eric
	$.fn是指jquery的命名空间，加上fn上的方法及属性，会对jquery实例每一个有效。 
		如扩展$.fn.abc() 
		那么你可以这样子：$("#div").abc(); 
		通常使用extend方法扩展，详细请看API. 
		$.fx是指jquery的特效。 
		如果使用显示、滑动、淡入淡出、动画等。 
		$.fx.off可以关闭动画，其实是直接显示结果。 
		
		jquery的extend和fn.extend 
		
		jQuery为开发插件提拱了两个方法，分别是： 
		jQuery.fn.extend(object); 
		jQuery.extend(object); 
		jQuery.extend(object); 为扩展jQuery类本身.为类添加新的方法。 
		jQuery.fn.extend(object);给jQuery对象添加方法。 
		
		fn 是什么东西呢。查看jQuery代码，就不难发现。 
		jQuery.fn = jQuery.prototype = { 
		　　　init: function( selector, context ) {//....　 
		　　　//...... 
		}; 
		原来 jQuery.fn = jQuery.prototype.对prototype肯定不会陌生啦。
 */
 
//翻页工具栏文字//用 $.fn.pagination.defaults 重写了 defaults。
if ($.fn.pagination){
	$.fn.pagination.defaults.showPageList=false;//定义是否显示页面导航列表。
	$.fn.pagination.defaults.PageText=false;
	$.fn.pagination.defaults.beforePageText="第&nbsp",//在输入组件之前显示一个label标签。
	$.fn.pagination.defaults.afterPageText = "页&nbsp; &nbsp; 共 {pages} 页";
	//在输入组件之后显示一个label标签。
	$.fn.pagination.defaults.displayMsg ="当前{from}-{to} 条  共{total}条记录";//显示页面信息。
}
if ($.fn.datagrid){
	$.fn.datagrid.defaults.loadMsg = '正在加载...';//显示载入状态。
}

//window窗体默认属性
$.fn.window.defaults.resizable=false;//定义是否能够改变窗口大小。
$.fn.window.defaults.collapsible=false;//定义是否显示可折叠按钮。
$.fn.window.defaults.minimizable=false;//定义是否显示最小化按钮。
$.fn.window.defaults.maximizable=false;//定义是否显示最大化按钮。
$.fn.window.defaults.shadow=false;//如果设置为true，在窗体显示的时候显示阴影。
$.fn.window.defaults.modal=true;//定义是否将窗体显示为模式化窗口。
$.fn.window.defaults.loadingMessage = '正在加载...';

//信息框按钮文字
if ($.messager){
	$.messager.defaults.ok = '确定';
	$.messager.defaults.cancel = '取消';
}
//树表格菜单
if ($.fn.treegrid){
	$.fn.treegrid.defaults.loadingMessage = '正在加载...';
}

if ($.fn.validatebox){
	$.fn.validatebox.defaults.missingMessage = '该输入项为必输项';
	$.fn.validatebox.defaults.rules.email.message = '请输入有效的电子邮件地址';
	$.fn.validatebox.defaults.rules.url.message = '请输入有效的URL地址';
	$.fn.validatebox.defaults.rules.length.message = '输入内容长度必须介于{0}和{1}之间';
	$.fn.validatebox.defaults.rules.remote.message = '请修正该字段';
}
if ($.fn.numberbox){
	$.fn.numberbox.defaults.missingMessage = '该输入项为必输项';
}
if ($.fn.combobox){
	$.fn.combobox.defaults.missingMessage = '该输入项为必输项';
}
if ($.fn.combotree){
	$.fn.combotree.defaults.missingMessage = '该输入项为必输项';
}
if ($.fn.combogrid){
	$.fn.combogrid.defaults.missingMessage = '该输入项为必输项';
}
if ($.fn.combo){
	$.fn.combo.defaults.missingMessage = '该输入项为必输项';
}
if ($.fn.calendar){
	$.fn.calendar.defaults.weeks = ['日','一','二','三','四','五','六'];
	$.fn.calendar.defaults.months = ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'];
}
if ($.fn.datebox){
	$.fn.datebox.defaults.currentText = '今天';
	$.fn.datebox.defaults.closeText = '关闭';
	$.fn.datebox.defaults.okText = '确定';
	$.fn.datebox.defaults.missingMessage = '该输入项为必输项';
	$.fn.datebox.defaults.formatter = function(date){
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
		return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
	};
	$.fn.datebox.defaults.parser = function(s){
		if (!s) return new Date();
		var ss = s.split('-');
		var y = parseInt(ss[0],10);
		var m = parseInt(ss[1],10);
		var d = parseInt(ss[2],10);
		if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
			return new Date(y,m-1,d);
		} else {
			return new Date();
		}
	};
}
if ($.fn.datetimebox && $.fn.datebox){
	$.extend($.fn.datetimebox.defaults,{
		currentText: $.fn.datebox.defaults.currentText,
		closeText: $.fn.datebox.defaults.closeText,
		okText: $.fn.datebox.defaults.okText,
		missingMessage: $.fn.datebox.defaults.missingMessage
	});
}

//提示信息
var promptMessage = undefined;
//以下为验证框架
$.extend($.fn.validatebox.defaults.rules, {
    idcard: {// 验证身份证
        validator: function (value) {
            return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
        },
        message: '身份证号码格式不正确'
    },
    minLength: {
        validator: function (value, param) {
            return value.length >= param[0];
        },
        message: '请输入至少（2）个字符.'
    },
    length: { validator: function (value, param) {
        var len = $.trim(value).length;
        return len >= param[0] && len <= param[1];
    },
        message: "输入内容长度必须介于{0}和{1}之间."
    },
    phone: {// 验证电话号码
        validator: function (value) {
            return /^((\d2,3)|(\d{3}\-))?(0\d2,3|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        },
        message: '格式不正确,请使用下面格式:020-88888888'
    },
    mobile: {// 验证手机号码
        validator: function (value) {
            return /^(13|15|18)\d{9}$/i.test(value);
        },
        message: '手机号码格式不正确'
    },
    intOrFloat: {// 验证整数或小数
        validator: function (value) {
            return /^\d+(\.\d+)?$/i.test(value);
        },
        message: '请输入数字，并确保格式正确'
    },
    studentNo:{//验证学号
    	 validator: function (value) {
    		 if (!(/^\d+(\.\d+)?$/i.test(value))){
    			 promptMessage = '请输入正确的学号格式！';
    			 return false;
    		 }
    		     $.ajax({
                     async : false,  
                     type : 'post',  
                     url : './student.ajaxValidateStudent.action',  
                     data : {  
                         'student.id' : value  
                     },  
                     success : function(data) {  
                         if (data.Flag == true) {
                        	promptMessage = data.Message;
                             return false;  
                         } else{
                        	 return true;  
                         }
                      
                     }  
                 });  
    		 
         },
         message: promptMessage
    },
    currency: {// 验证货币
        validator: function (value) {
            return /^\d+(\.\d+)?$/i.test(value);
        },
        message: '货币格式不正确'
    },
    qq: {// 验证QQ,从10000开始
        validator: function (value) {
            return /^[1-9]\d{4,9}$/i.test(value);
        },
        message: 'QQ号码格式不正确'
    },
    integer: {// 验证整数 可正负数
        validator: function (value) {
            //return /^[+]?[1-9]+\d*$/i.test(value);

            return /^([+]?[0-9])|([-]?[0-9])+\d*$/i.test(value);
        },
        message: '请输入整数'
    },
    age: {// 验证年龄
        validator: function (value) {
            return /^(?:[1-9][0-9]?|1[01][0-9]|120)$/i.test(value);
        },
        message: '年龄必须是0到120之间的整数'
    },

    chinese: {// 验证中文
        validator: function (value) {
            return /^[\Α-\￥]+$/i.test(value);
        },
        message: '请输入中文'
    },
    english: {// 验证英语
        validator: function (value) {
            return /^[A-Za-z]+$/i.test(value);
        },
        message: '请输入英文'
    },
    unnormal: {// 验证是否包含空格和非法字符
        validator: function (value) {
            return /.+/i.test(value);
        },
        message: '输入值不能为空和包含其他非法字符'
    },
    username: {// 验证用户名
        validator: function (value) {
            return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value);
        },
        message: '用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）'
    },
    faxno: {// 验证传真
        validator: function (value) {
            //            return /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/i.test(value);
            return /^((\d2,3)|(\d{3}\-))?(0\d2,3|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        },
        message: '传真号码不正确'
    },
    zip: {// 验证邮政编码
        validator: function (value) {
            return /^[1-9]\d{5}$/i.test(value);
        },
        message: '邮政编码格式不正确'
    },
    ip: {// 验证IP地址
        validator: function (value) {
            return /d+.d+.d+.d+/i.test(value);
        },
        message: 'IP地址格式不正确'
    },
    name: {// 验证姓名，可以是中文或英文
        validator: function (value) {
            return /^[\Α-\￥]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);
        },
        message: '请输入姓名'
    },
    date: {// 验证姓名，可以是中文或英文
        validator: function (value) {
            //格式yyyy-MM-dd或yyyy-M-d
            return /^(?:(?!0000)[0-9]{4}([-]?)(?:(?:0?[1-9]|1[0-2])\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\1(?:29|30)|(?:0?[13578]|1[02])\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-]?)0?2\2(?:29))$/i.test(value);
        },
        message: '清输入合适的日期格式'
    },
    msn: {
        validator: function (value) {
            return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
        },
        message: '请输入有效的msn账号(例：abc@hotnail(msn/live).com)'
    },
    same: {
        validator: function (value, param) {
            if ($("#" + param[0]).val() != "" && value != "") {
                return $("#" + param[0]).val() == value;
            } else {
                return true;
            }
        },
        message: '两次输入的密码不一致！'
    }
});

