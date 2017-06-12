package com.data.share.initdata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.data.model.Department;
import com.data.model.Menu;
import com.data.model.Professional;
import com.data.model.Role;
import com.data.service.department.IDepartmentService;
import com.data.service.menu.IMenuService;
import com.data.service.professional.IProfessionalService;
import com.data.service.role.IRoleService;

/**
 * @ClassName: StaticDataUtil
 * @Description: TODO(初始基础数据)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年3月17日 下午3:15:25
 */
public class StaticDataUtil {

	/**
	 * @Fields alarmTypeList : 院系类型
	 */
	private static List<Department> departmentList;

	/**
	 * @Fields menuList : TODO(菜单)
	 */
	private static List<Menu> menuList;

	/**
	 * @Fields roleList : TODO(角色)
	 */
	private static List<Role> roleList;

	/**
	 * @Fields professionalList : TODO(专业)
	 */
	private static List<Professional> professionalList;

	/**
	 * @Fields iDepartmentService : TODO(注入院系service)
	 */
	@Autowired(required = true)
	private IDepartmentService iDepartmentService;
	/**
	 * @Fields iMenuService : TODO(注入菜单Service)
	 */
	@Autowired(required = true)
	private IMenuService iMenuService;
	/**
	 * @Fields iRoleService : TODO(注入角色service)
	 */
	@Autowired(required = true)
	private IRoleService iRoleService;

	/**
	 * @Fields iProfessionalService : TODO(注入角色service)
	 */
	@Autowired(required = true)
	private IProfessionalService iProfessionalService;

	/**
	 * @throws Exception
	 * @Title: loadBasetype
	 * @Description: TODO(初始化基本数据)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月17日 下午3:15:11
	 * @throws
	 */
	public void loadBasetype() throws Exception {
		departmentList = iDepartmentService.queryAllDepartment();
		menuList = iMenuService.queryAllMenu();
		roleList = iRoleService.queryAllRole();
		professionalList = iProfessionalService.queryAllProfessional();
	}

	public static void setDepartmentList(List<Department> departmentList) {
		StaticDataUtil.departmentList = departmentList;
	}

	public static List<Department> getDepartmentList() {
		return departmentList;
	}

	public static List<Menu> getMenuList() {
		return menuList;
	}

	public static void setMenuList(List<Menu> menuList) {
		StaticDataUtil.menuList = menuList;
	}

	public static List<Role> getRoleList() {
		return roleList;
	}

	public static void setRoleList(List<Role> roleList) {
		StaticDataUtil.roleList = roleList;
	}

	public static List<Professional> getProfessionalList() {
		return professionalList;
	}

	public static void setProfessionalList(List<Professional> professionalList) {
		StaticDataUtil.professionalList = professionalList;
	}
}
