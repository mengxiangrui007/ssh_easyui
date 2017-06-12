package com.data.service.rolemenu;

import java.util.List;

import com.data.model.RoleMenu;

/**
 * @ClassName: IRoleMenuService
 * @Description: TODO(角色和菜单的关系)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年3月23日 下午4:52:48
 */
public interface IRoleMenuService {

	/**
	 * @Title: queryRoleMenu
	 * @Description: TODO(查询角色菜单)
	 * @param @param roleMenu
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List<RoleMenu> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年6月2日 上午3:16:28
	 * @throws
	 */
	public List<RoleMenu> queryRoleMenu(RoleMenu roleMenu) throws Exception;

	/**
	 * @Title: insertRoleMenu
	 * @Description: TODO(添加角色菜单)
	 * @param @param departmentId
	 * @param @param roleId
	 * @param @param asList 设定文件
	 * @return void 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年6月2日 上午3:16:12
	 * @throws
	 */
	public void insertRoleMenu(Integer departmentId, Integer roleId,
			List<Integer> asList) throws Exception;

}
