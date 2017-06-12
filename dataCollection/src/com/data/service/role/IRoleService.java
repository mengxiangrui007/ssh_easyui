package com.data.service.role;

import java.util.List;

import com.data.model.Role;

/**
 * @ClassName: IRoleService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年3月17日 下午1:33:04
 */
public interface IRoleService {

	/**
	 * @Title: queryAllRole
	 * @Description: TODO(获取所有角色)
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List<Role> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月23日 下午4:20:38
	 * @throws
	 */
	public List<Role> queryAllRole() throws Exception;

}
