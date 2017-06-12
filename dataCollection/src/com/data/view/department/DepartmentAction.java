package com.data.view.department;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.data.model.Department;
import com.data.model.LogType;
import com.data.service.department.IDepartmentService;
import com.data.tools.ComplexPropertyPreFilter;
import com.data.tools.Tree;
import com.data.view.base.BaseAction;

/**
 * @ClassName: DepartmentAction
 * @Description: TODO(院系管理页面交互层)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年3月12日 下午11:04:22
 */
@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	/**
	 * @Fields iDepartmentService : TODO(注入)
	 */
	@Autowired(required = true)
	private IDepartmentService iDepartmentService;
	/**
	 * @Fields departmentList : TODO(院系集合)
	 */
	private List<Department> departmentList = null;

	/**
	 * @Fields departmentIdList : TODO(院系Id集合)
	 */
	private List<Integer> departmentIdList = null;

	/**
	 * @Title: queryAllConditionDepartment
	 * @Description: TODO(获取所有有条件院系)
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月17日 下午4:31:52
	 * @throws
	 */
	public String queryAllConditionDepartment() {
		try {
			departmentList = iDepartmentService
					.queryAllConditionDepartment(userInfo.getDepartmentList());
			super.getResponse().setHeader("Content-type",
					"text/html;charset=UTF-8");
			complexPropertyPreFilter = new ComplexPropertyPreFilter();
			complexPropertyPreFilter
					.setIncludes(new HashMap<Class<?>, String[]>() {
						private static final long serialVersionUID = -8411128674046835592L;
						{
							put(Tree.class, new String[] { "id", "text", "pid",
									"checked", "paramType", "paramObject" });
							put(String.class, new String[] { "loaderName" });
						}
					});
			super.getResponse()
					.getWriter()
					.print(JSON.toJSONString(this.getTree(departmentList),
							complexPropertyPreFilter,
							SerializerFeature.WriteNullStringAsEmpty,
							SerializerFeature.WriteNullListAsEmpty));
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"获取所有有条件院系" + e.getMessage(), super.getResourceName(),
					super.getRequest());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title: queryAllConditionDepartment
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月23日 下午4:07:08
	 * @throws
	 */
	public String queryALLDepartment() {
		try {
			departmentList = iDepartmentService.queryUserDepartment(userInfo
					.getDepartmentList());
			super.getResponse().setHeader("Content-type",
					"text/html;charset=UTF-8");
			complexPropertyPreFilter = new ComplexPropertyPreFilter();
			complexPropertyPreFilter
					.setIncludes(new HashMap<Class<?>, String[]>() {
						private static final long serialVersionUID = -8411128674046835592L;
						{
							put(Tree.class,
									new String[] { "id", "text", "pid" });
						}
					});
			super.getResponse()
					.getWriter()
					.print(JSON.toJSONString(this.getTree(departmentList),
							complexPropertyPreFilter,
							SerializerFeature.WriteNullStringAsEmpty,
							SerializerFeature.WriteNullListAsEmpty));
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"获取所有有条件院系" + e.getMessage(), super.getResourceName(),
					super.getRequest());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title: queryUserDepartment
	 * @Description: TODO(查询用户拥有的院系)
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月18日 下午1:09:39
	 * @throws
	 */
	public String queryUserDepartment() {
		try {
			departmentIdList = userInfo.getDepartmentList();
			if (departmentIdList != null && departmentIdList.size() > 1) {
				departmentList = iDepartmentService
						.queryUserDepartment(departmentIdList);
				mapParams.put("flag", true);
				mapParams.put("data", this.getTree(departmentList));
			} else {
				mapParams.put("flag", false);
				mapParams.put("data", null);
			}
			complexPropertyPreFilter = new ComplexPropertyPreFilter();
			complexPropertyPreFilter
					.setIncludes(new HashMap<Class<?>, String[]>() {
						private static final long serialVersionUID = -8411128674046835592L;
						{
							put(HashMap.class, new String[] { "flag", "data" });
							put(Tree.class, new String[] { "id", "text", "pid",
									"checked" });
						}
					});

			super.getResponse().setHeader("Content-type",
					"text/html;charset=UTF-8");
			super.getResponse()
					.getWriter()
					.print(JSON.toJSONString(mapParams,
							complexPropertyPreFilter,
							SerializerFeature.WriteNullStringAsEmpty,
							SerializerFeature.WriteNullListAsEmpty));
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"查询用户拥有的院系" + e.getMessage(), super.getResourceName(),
					super.getRequest());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title: queryUserDepartment
	 * @Description: TODO(查询用户拥有的院系)
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月18日 下午1:09:39
	 * @throws
	 */
	public String queryDepartment() {
		try {
			departmentIdList = userInfo.getDepartmentList();
			if (departmentIdList != null && departmentIdList.size() > 0) {
				departmentList = iDepartmentService
						.queryUserDepartment(departmentIdList);
				mapParams.put("flag", true);
				mapParams.put("data", this.getTree(departmentList));
			} else {
				mapParams.put("flag", false);
				mapParams.put("data", null);
			}
			complexPropertyPreFilter = new ComplexPropertyPreFilter();
			complexPropertyPreFilter
					.setIncludes(new HashMap<Class<?>, String[]>() {
						private static final long serialVersionUID = -8411128674046835592L;
						{
							put(HashMap.class, new String[] { "flag", "data" });
							put(Tree.class, new String[] { "id", "text", "pid",
									"checked" });
						}
					});

			super.getResponse().setHeader("Content-type",
					"text/html;charset=UTF-8");
			super.getResponse()
					.getWriter()
					.print(JSON.toJSONString(mapParams,
							complexPropertyPreFilter,
							SerializerFeature.WriteNullStringAsEmpty,
							SerializerFeature.WriteNullListAsEmpty));
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"查询用户拥有的院系" + e.getMessage(), super.getResourceName(),
					super.getRequest());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title: getTree
	 * @Description: TODO(院系树)
	 * @param @return 设定文件
	 * @return list<Tree> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月18日 下午3:50:00
	 * @throws
	 */
	public List<Tree> getTree(List<Department> departmentList) {
		treeList = new ArrayList<Tree>();
		Tree tree = null;
		for (int i = 0, len = departmentList.size(); i < len; i++) {
			Department departmentTmp = departmentList.get(i);
			tree = new Tree();
			tree.setId(departmentTmp.getId() + "");
			tree.setPid(departmentTmp.getPid() + "");
			tree.setChecked(true);
			tree.setParamObject(departmentTmp.getLoaderName());
			// tree.setIconCls("icon-school");
			tree.setParamType(departmentTmp.getType());
			tree.setText(departmentTmp.getName());
			treeList.add(tree);
		}
		return treeList;
	}
}
