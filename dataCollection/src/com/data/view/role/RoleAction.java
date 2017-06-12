package com.data.view.role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.data.model.LogType;
import com.data.model.Role;
import com.data.service.role.IRoleService;
import com.data.share.initdata.StaticDataUtil;
import com.data.tools.ComplexPropertyPreFilter;
import com.data.tools.Tree;
import com.data.view.base.BaseAction;

/**
 * @ClassName: RoleAction
 * @Description: TODO(角色页面交互层)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年3月12日 下午11:44:24
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @Fields iRoleService : TODO(注入)
	 */
	@Autowired(required = true)
	private IRoleService iRoleService;

	/**
	 * @Fields listRole : TODO(角色菜单)
	 */
	private List<Role> roleList = null;

	/**
	 * @Title: queryDepartmentRole
	 * @Description: TODO(获取院系角色)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月23日 下午4:23:44
	 * @throws
	 */
	public String queryDepartmentRole() {
		try {
			roleList = StaticDataUtil.getRoleList();
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
					.print(JSON.toJSONString(this.getDepartmentRole(roleList),
							complexPropertyPreFilter,
							SerializerFeature.WriteNullStringAsEmpty,
							SerializerFeature.WriteNullListAsEmpty));
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"查询院系角色失败" + e.getMessage(), super.getResourceName(),
					super.getRequest());
			e.printStackTrace();
		}
		return null;
	}

	public List<Tree> getDepartmentRole(List<Role> listTmp) {
		treeList = new ArrayList<Tree>();
		Tree tree = null;
		Role roleTmp = null;
		for (int i = 0, len = listTmp.size(); i < len; i++) {
			roleTmp = listTmp.get(i);
			tree = new Tree();
			tree.setId(roleTmp.getId() + "");
			tree.setPid(roleTmp.getPid() + "");
			tree.setText(roleTmp.getName());
			treeList.add(tree);
		}
		return treeList;
	}

}
