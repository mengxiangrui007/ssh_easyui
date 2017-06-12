package com.data.service.menu;

import java.util.List;

import com.data.model.Menu;

/**
 * @ClassName: IMenuService
 * @Description: TODO(菜单业务处理)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年3月12日 下午11:47:24
 */
public interface IMenuService {

	/**
	 * @Title: queryDepartmentMenu
	 * @Description: TODO(获取院系的菜单)
	 * @param @param departmentId
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List<Menu> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月22日 下午3:21:01
	 * @throws
	 */
	public List<Menu> queryDepartmentMenu(Integer departmentId)
			throws Exception;

	/**
	 * @Title: queryAllMenu
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List<Menu> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月22日 下午4:59:36
	 * @throws
	 */
	public List<Menu> queryAllMenu() throws Exception;

	/**
	 * @Title: queryUserMenu
	 * @Description: TODO(查看用户所拥有的菜单)
	 * @param @param menuIDs
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List<Menu> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年6月1日 下午11:40:21
	 * @throws
	 */
	public List<Menu> queryUserMenu(List<Integer> menuIDs) throws Exception;

}
