/*
	公共方法文件
	@eric
*/
var $parent = self.parent.$;//子窗口如何获取父窗口

$(function(){
	//隐藏显示查询条件区域
	$('#openOrClose').on("click",function(){
		$('#conditon').toggle(80);//如果元素是可见的，切换为隐藏的；如果元素是隐藏的，切换为可见的。
		setTimeout(domresize,100);//条件隐藏，改变表格高度
		//setTimeout() 方法用于在指定的毫秒数后调用函数或计算表达式。
	});	
})

/**获取URL地址参数
 * @param name
 * @returns
 */
function parse_url(url){ //定义函数
	var search = url.split("?")[1];
    var pattern = /(?:([^&]+)=([^&]+))/g;//定义正则表达式
    var parames = {};//定义一个js对象
    search.replace(pattern, function(a, b, c){
    	parames[b] = c;
    	});
    /*这是最关键的.当replace匹配到classid=9时.那么就用执行function(a,b,c);
     * 其中a的值为:classid=9,b的值为classid,c的值为9;(这是反向引用.因为在定义 正则表达式的时候有两个子匹配.)
     * 然后将对象的属性为classid的值赋为9;然后完成.再继续匹配到id=2;此时执行function(a,b,c);其中a的值为:id=2,
     * b的值为id,c的值为2;然后将数组的key为id的值赋为2.*/
    return parames;//返回这个js对象.
}


function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}
function TreeHelper(list){
    this.list = list;
}

//计算JSON的个数
function JSONLength(obj) {
	var size = 0, key;
	for (key in obj) {
	if (obj.hasOwnProperty(key)) size++;
	}
	return size;
};


//把id Pid 的树转换为Child的形式
(function(TreeHelper){
    function getAllChildren(list,item){
        var children = getNextLevelChildren(list,item);
        for(var i=0,ii=children.length;i<ii;i++){
            getAllChildren(list,children[i]);
        }
    }
    //遍历list剩下的数据，找到item的下一层的子节点
    function getNextLevelChildren(list,item){
        var children = [];
        for(var i=list.length-1;i>=0;i--){
            var mid = list[i];
            if(mid.pid === item.id){
                delete mid.pid;
                children.push(mid);
                list.splice(i,1);
            }
        }
        if(children.length > 0){
            item.children = children;
        }
        return children;
    }
    TreeHelper.prototype.dataTransferer = function(){
        var list = this.list;
        //根节点必须在数组前面
        list.sort(function(a,b){
            if(a.pid > b.pid){
                return 1;
            }else{
                return -1;
            }
        });
        var item,
            result = [];
        //遍历根节点，递归处理其所有子节点的数据
        //每处理完一个根节点，就将其及其所有子节点从list中删除，加快递归速度
        while(list.length){
            item = list[0];
            list.splice(0,1);
            delete item.pid;
            getAllChildren(list,item);
            result.push(item);
        }
        return result;
    };
})(TreeHelper);


