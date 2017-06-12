package com.data.view.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.data.model.Users;
import com.data.service.logger.ILoggerService;
import com.data.share.propertie.BaseConfigHelper;
import com.data.tools.ComplexPropertyPreFilter;
import com.data.tools.Page;
import com.data.tools.Tree;
import com.data.tools.TreeNodeUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @ClassName: BaseAction
 * @Description: TODO(公共Action)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年2月19日 下午2:37:47
 */
@Controller
@Scope("prototype")
public class BaseAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	/**
	 * @Fields pager : TODO(页面交互pager)
	 */
	protected Page pager = null;

	/*
	 * @Fields page : TODO(当前页)
	 */
	private int page;

	/**
	 * @Fields rows : TODO(每页显示的记录数 )
	 */
	private int rows;
	/**
	 * @Fields pageSize : TODO(页面显示条目数)
	 */
	private int pageSize;
	/**
	 * @Fields resourceName : TODO(访问名称)
	 */
	private String resourceName;
	/**
	 * @Fields departmentIdList : TODO(院系Id集合)
	 */
	protected List<Integer> departmentIdList = null;

	/**
	 * @Fields departmentArr : TODO(所选院系)
	 */
	protected String departmentArr = null;
	/**
	 * @Fields mapParam : TODO(属性参数)
	 */
	protected Map<String, Object> mapParams = new HashMap<String, Object>();

	@Autowired(required = true)
	protected ILoggerService iLogerService;

	/**
	 * @Fields userInfo : TODO(获取session中的User)
	 */
	protected Users userInfo = BaseConfigHelper.getUser(getRequest());
	/**
	 * @Fields flag : TODO(标识位)
	 */
	protected boolean flag = false;

	/**
	 * @Fields complexPropertyPreFilter : TODO(Fastjson属性过滤)
	 */
	protected ComplexPropertyPreFilter complexPropertyPreFilter = null;
	/**
	 * @Fields treeList : TODO(树的集合)
	 */
	protected List<Tree> treeList = null;

	/**
	 * @Title: getRequest
	 * @Description: TODO(获取当前页面的request对象)
	 * @param @return 设定文件
	 * @return HttpServletRequest 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月21日 下午1:41:25
	 * @throws
	 */
	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * @Title: getResponse
	 * @Description: TODO(这获取当前页面的respons对象)
	 * @param @return 设定文件
	 * @return HttpServletResponse 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月21日 下午1:42:07
	 * @throws
	 */
	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * @Title: getApplication
	 * @Description: TODO(获取 application 对象)
	 * @param @return 设定文件
	 * @return ServletContext 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月21日 下午1:42:44
	 * @throws
	 */
	public static ServletContext getApplication() {
		return ServletActionContext.getServletContext();
	}

	/**
	 * 操作提示，用于Ajax数据调用
	 * 
	 * @param flag
	 * @param message
	 * @return Json
	 */
	public String operationHintsToJson(Boolean flag, String message) {
		String json = "{\"Flag\":" + flag + ",\"Message\":\"" + message + "\"}";
		return json;
	}

	/**
	 * @Title: sendJsonMessage
	 * @Description: TODO(发送JSON数据)
	 * @param @param flag
	 * @param @param message
	 * @param @throws IOException 设定文件
	 * @return void 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月21日 下午1:51:14
	 * @throws
	 */
	public void sendJsonMessage(boolean flag, String message)
			throws IOException {
		getResponse().setHeader("Content-type", "text/html;charset=UTF-8");
		getResponse().getWriter().print(
				this.operationHintsToJson(flag, message));
	}

	/**
	 * @Title: isAjaxRequest
	 * @Description: TODO(判断当前请求是否为ajax请求)
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月21日 下午1:43:56
	 * @throws
	 */
	public static boolean isAjaxRequest() {
		boolean result = false;
		String requestType = getRequest().getHeader("X-Requested-With");
		if (requestType != null && requestType.equals("XMLHttpRequest")) {
			result = true;
		}
		return result;
	}

	/**
	 * @Title: getRemoteAddr
	 * @Description: 获取客户端ip地址
	 * @author 孟祥瑞
	 * @date 2016年2月21日 下午1:43:56
	 * @return String ip地址
	 * @throws
	 */
	public static String getRemoteAddr() {
		return BaseAction.getRequest().getRemoteAddr();
	}

	/**
	 * @Title: getRemoteSystem
	 * @Description: 获取客户端的系统版本
	 * @author 孟祥瑞
	 * @date 2016年2月21日 下午1:43:56
	 * @return String 系统的版本
	 * @throws
	 */
	public static String getRemoteSystem() {
		return BaseAction.getRequest().getHeader("User-Agent");
	}

	/**
	 * @Title: getServerHomeUrl
	 * @Description: 获取服务器主机名，例如http://localhost:8080
	 * @author A18ccms A18ccms_gmail_com
	 * @author 孟祥瑞
	 * @date 2016年2月21日 下午1:43:56
	 * @return String 获取服务器主机名
	 * @throws
	 */
	public static String getServerHomeUrl() {
		HttpServletRequest request = BaseAction.getRequest();
		return request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort();
	}

	/**
	 * @Title: getWebAbsolutePath
	 * @Description: 获取web绝对路径
	 * @author 孟祥瑞
	 * @date 2016年2月21日 下午1:43:56
	 * @param 无
	 * @return String 获取web绝对路径
	 * @throws
	 */
	public static String getWebRootAbsolutePath() {
		return BaseAction.getApplication().getRealPath("/");
	}

	/**
	 * @Title: getWebAbsolutePath
	 * @Description: 根据相对路径返回绝对路径
	 * @param String
	 *            path 相对路径
	 * @return String 绝对路径
	 * @author: 温泉
	 * @date 2012-03-01 22:55:58 +0800
	 * @throws
	 */
	public static String getWebAbsolutePath(String path) {
		return BaseAction.getApplication().getRealPath(path);
	}

	/**
	 * @Title: getTreeList
	 * @Description: TODO(得到easyUI树)
	 * @param @param listTree
	 * @param @return 设定文件
	 * @return List<Tree> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月19日 下午12:19:28
	 * @throws
	 */
	public List<Tree> getTreeList(List<Tree> listTree) {
		return TreeNodeUtil.getfatherNode(listTree);
	}

	public int getPage() {
		return page == 0 ? 1 : page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize == 0 ? BaseConfigHelper.getBaseConfig(
				ServletActionContext.getServletContext()).getPageSize()
				: pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public List<Integer> getDepartmentIdList() {
		if (departmentArr != null && !"[]".equals(departmentArr)) {
			departmentIdList = JSON.parseArray(departmentArr, Integer.class);
		} else {
			departmentIdList = userInfo.getDepartmentList();
		}
		return departmentIdList;
	}

	public void setDepartmentIdList(List<Integer> departmentIdList) {
		this.departmentIdList = departmentIdList;
	}

	public String getDepartmentArr() {
		return departmentArr;
	}

	public void setDepartmentArr(String departmentArr) {
		this.departmentArr = departmentArr;
	}
}
