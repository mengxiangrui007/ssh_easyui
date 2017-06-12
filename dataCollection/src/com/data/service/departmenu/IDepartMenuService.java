package com.data.service.departmenu;

import java.util.List;

import com.data.model.DepartmentMenu;

/**
 * @ClassName: IDepartMenuService
 * @Description: TODO(菜单与用户关系)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年3月22日 下午5:05:56
 */
public interface IDepartMenuService {

	/**
	 * @Title: queryDepartmentMenu
	 * @Description: TODO(查询部门与菜单的关系)
	 * @param @param departmentId
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List<DepartmentMenu> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月22日 下午5:10:49
	 * @throws
	 */
	public List<DepartmentMenu> queryDepartMenu(Integer departmentId)
			throws Exception;

	/**
	 * @Title: insertDepartmentMenu
	 * @Description: TODO(添加院系菜单)
	 * @param @param departmentId
	 * @param @param asList 设定文件
	 * @return void 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年6月2日 上午2:23:11
	 * @throws
	 */
	public void insertDepartmentMenu(Integer departmentId, List<Integer> list);

}
