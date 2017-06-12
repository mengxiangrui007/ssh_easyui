package com.data.view.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.data.model.DepartmentMenu;
import com.data.model.LogType;
import com.data.model.Menu;
import com.data.model.RoleMenu;
import com.data.service.departmenu.IDepartMenuService;
import com.data.service.menu.IMenuService;
import com.data.service.rolemenu.IRoleMenuService;
import com.data.share.initdata.StaticDataUtil;
import com.data.tools.ComplexPropertyPreFilter;
import com.data.tools.Tree;
import com.data.view.base.BaseAction;

/**
 * @ClassName: MenuAction
 * @Description: TODO(菜单页面交互层)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年3月12日 下午11:46:18
 */
@Controller
@Scope("prototype")
public class MenuAction extends BaseAction {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @Fields iRoleService : TODO(注入)
	 */
	@Autowired(required = true)
	private IMenuService iMenuService;
	@Autowired(required = true)
	private IDepartMenuService iDepartMenuService;
	@Autowired(required = true)
	private IRoleMenuService iRoleMenuService;
	/**
	 * @Fields listMenu : TODO(菜单)
	 */
	private List<Menu> listMenu = null;

	/**
	 * @Fields listDepartMenu : TODO(公司权限菜单)
	 */
	private List<DepartmentMenu> listDepartMenu = null;
	/**
	 * @Fields departmentId : TODO(公司ID)
	 */
	private Integer departmentId;

	/**
	 * @Fields listRoleMenu : TODO(角色菜单集合)
	 */
	private List<RoleMenu> listRoleMenu = null;

	/**
	 * @Fields departmentIdRoleId : TODO(角色和部门ID)
	 */
	private RoleMenu roleMenu = createRoleMenuModel();
	private String menuStr = "";
	private Integer roleId;

	/**
	 * @Title: queryAllMenu
	 * @Description: TODO(查询院系菜单)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月22日 下午1:51:42
	 * @throws
	 */
	public String queryUserMenu() {
		List<Integer> menuIDs = super.userInfo.getMenuID();
		try {
			if (menuIDs != null) {
				listMenu = iMenuService.queryUserMenu(menuIDs);
			}
			super.getResponse().setHeader("Content-type",
					"text/html;charset=UTF-8");
			complexPropertyPreFilter = new ComplexPropertyPreFilter();
			complexPropertyPreFilter
					.setIncludes(new HashMap<Class<?>, String[]>() {
						private static final long serialVersionUID = -8411128674046835592L;
						{
							put(Tree.class, new String[] { "id", "file", "pId",
									"name", "open" });
						}
					});
			super.getResponse()
					.getWriter()
					.print(JSON.toJSONString(this.getUserMenuTree(listMenu),
							complexPropertyPreFilter,
							SerializerFeature.WriteNullStringAsEmpty,
							SerializerFeature.WriteNullListAsEmpty));
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"查询用户菜单失败" + e.getMessage(), super.getResourceName(),
					super.getRequest());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title: queryDepartmentMenu
	 * @Description: TODO(查询院系菜单）
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年6月2日 上午1:56:25
	 * @throws
	 */
	public String queryDepartmentMenu() {
		try {
			listDepartMenu = iDepartMenuService.queryDepartMenu(departmentId);
			super.getResponse().setHeader("Content-type",
					"text/html;charset=UTF-8");
			complexPropertyPreFilter = new ComplexPropertyPreFilter();
			complexPropertyPreFilter
					.setIncludes(new HashMap<Class<?>, String[]>() {
						private static final long serialVersionUID = -8411128674046835592L;
						{
							put(Tree.class, new String[] { "id", "text", "pid",
									"checked" });
						}
					});
			super.getResponse()
					.getWriter()
					.print(JSON.toJSONString(
							this.getDepartmentMenuTree(listDepartMenu),
							complexPropertyPreFilter,
							SerializerFeature.WriteNullStringAsEmpty,
							SerializerFeature.WriteNullListAsEmpty));
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"查询院系菜单失败" + e.getMessage(), super.getResourceName(),
					super.getRequest());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @throws IOException
	 * @Title: insertDepartmentMenu
	 * @Description: TODO(添加部门菜单)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年6月2日 上午1:57:49
	 * @throws
	 */
	public String insertDepartmentMenu() throws IOException {
		try {
			Integer[] menuArr = (Integer[]) ConvertUtils.convert(
					menuStr.split(","), Integer.class);
			iDepartMenuService.insertDepartmentMenu(departmentId,
					Arrays.asList(menuArr));
			super.sendJsonMessage(true, "添加院系菜单成功");
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"添加院系菜单失败" + e.getMessage(), super.getResourceName(),
					super.getRequest());
			e.printStackTrace();
			super.sendJsonMessage(true, "添加院系菜单授权失败");
		}
		return null;
	}

	/**
	 * @Title: insertRoleMenu
	 * @Description: TODO(添加角色菜单)
	 * @param @return
	 * @param @throws IOException 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年6月2日 上午3:14:52
	 * @throws
	 */
	public String insertRoleMenu() throws IOException {
		try {
			Integer[] menuArr = (Integer[]) ConvertUtils.convert(
					menuStr.split(","), Integer.class);
			iRoleMenuService.insertRoleMenu(departmentId, roleId,
					Arrays.asList(menuArr));
			super.sendJsonMessage(true, "添加角色菜单成功");
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"添加角色菜单失败" + e.getMessage(), super.getResourceName(),
					super.getRequest());
			e.printStackTrace();
			super.sendJsonMessage(true, "添加角色菜单失败");
		}
		return null;
	}

	/**
	 * @Title: queryRoleMenu
	 * @Description: TODO(查询院系角色的菜单)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月23日 下午5:02:16
	 * @throws
	 */
	public String queryRoleMenu() {
		try {
			listRoleMenu = iRoleMenuService.queryRoleMenu(roleMenu);
			listDepartMenu = iDepartMenuService.queryDepartMenu(roleMenu
					.getDepartmentId());
			super.getResponse().setHeader("Content-type",
					"text/html;charset=UTF-8");
			complexPropertyPreFilter = new ComplexPropertyPreFilter();
			complexPropertyPreFilter
					.setIncludes(new HashMap<Class<?>, String[]>() {
						private static final long serialVersionUID = -8411128674046835592L;
						{
							put(Tree.class, new String[] { "id", "text", "pid",
									"checked" });
						}
					});
			super.getResponse()
					.getWriter()
					.print(JSON.toJSONString(
							this.getRoleMenuTree(listRoleMenu, listDepartMenu),
							complexPropertyPreFilter,
							SerializerFeature.WriteNullStringAsEmpty,
							SerializerFeature.WriteNullListAsEmpty));
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"查询院系菜单失败" + e.getMessage(), super.getResourceName(),
					super.getRequest());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title: getDepartmentMenuTree
	 * @Description: TODO(获取企业菜单树)
	 * @param @param departmentList
	 * @param @return 设定文件
	 * @return List<Tree> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月22日 下午3:32:05
	 * @throws
	 */
	public List<Tree> getDepartmentMenuTree(List<DepartmentMenu> menudepartList) {
		treeList = new ArrayList<Tree>();
		Tree tree = null;
		DepartmentMenu departmentMenuTmp = null;
		Menu menuTmp = null;
		listMenu = StaticDataUtil.getMenuList();
		for (int i = 0, len = listMenu.size(); i < len; i++) {
			menuTmp = listMenu.get(i);
			departmentMenuTmp = new DepartmentMenu();
			departmentMenuTmp.setMenu(menuTmp);
			flag = menudepartList.contains(departmentMenuTmp);
			tree = new Tree();
			tree.setId(menuTmp.getId() + "");
			tree.setPid(menuTmp.getPid() + "");
			tree.setText(menuTmp.getTitle());
			tree.setChecked(flag ? true : false);
			treeList.add(tree);
		}
		return treeList;
	}

	/**
	 * @param listDepartMenu2
	 * @Title: getRoleMenuTree
	 * @Description: TODO(获取角色菜单树)
	 * @param @param roleMenuList
	 * @param @return 设定文件
	 * @return List<Tree> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月23日 下午5:12:11
	 * @throws
	 */
	public List<Tree> getRoleMenuTree(List<RoleMenu> roleMenuList,
			List<DepartmentMenu> listDepart) {
		treeList = new ArrayList<Tree>();
		Tree tree = null;
		DepartmentMenu departmentMenuTmp = null;
		RoleMenu roleMenuTmp = null;
		for (int i = 0, len = listDepart.size(); i < len; i++) {
			departmentMenuTmp = listDepart.get(i);
			roleMenuTmp = new RoleMenu();
			roleMenuTmp.setMenu(departmentMenuTmp.getMenu());
			flag = roleMenuList.contains(roleMenuTmp);
			tree = new Tree();
			tree.setId(departmentMenuTmp.getMenu().getId() + "");
			tree.setPid(departmentMenuTmp.getMenu().getPid() + "");
			tree.setText(departmentMenuTmp.getMenu().getTitle());
			tree.setChecked(flag ? true : false);
			treeList.add(tree);
		}
		return treeList;
	}

	/**
	 * @Title: getUserMenuTree
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return List<Tree> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年6月2日 上午12:08:03
	 * @throws
	 */
	public List<Tree> getUserMenuTree(List<Menu> menus) {
		treeList = new ArrayList<Tree>();
		Tree tree = null;
		for (Menu menu : menus) {
			tree = new Tree();
			tree.setId(menu.getId() + "");
			tree.setpId(menu.getPid() + "");
			if (menu.getHref().equals("javascript:void(0);")) {
				tree.setOpen(true);
			}
			tree.setFile(menu.getHref());
			tree.setName(menu.getTitle());
			treeList.add(tree);
		}
		return treeList;
	}

	private RoleMenu createRoleMenuModel() {
		if (null == roleMenu) {
			roleMenu = new RoleMenu();
		}
		return roleMenu;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public RoleMenu getRoleMenu() {
		return roleMenu;
	}

	public void setRoleMenu(RoleMenu roleMenu) {
		this.roleMenu = roleMenu;
	}

	public String getMenuStr() {
		return menuStr;
	}

	public void setMenuStr(String menuStr) {
		this.menuStr = menuStr;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}
