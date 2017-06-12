package com.data.service.department;

import java.util.List;

import com.data.model.Department;

/**
 * @ClassName: IDepartmentService
 * @Description: TODO(院系管理实线层)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年3月12日 下午11:03:13
 */
public interface IDepartmentService {

	/**
	 * @Title: queryAllDepartment
	 * @Description: TODO(查询有的院系)
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List<Department> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月18日 下午1:27:02
	 * @throws
	 */
	public List<Department> queryAllDepartment() throws Exception;

	/**
	 * @Title: queryUserDepartment
	 * @Description: TODO(查询用户拥有)
	 * @param @param departmentIdList
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List<Department> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月18日 下午1:27:24
	 * @throws
	 */
	public List<Department> queryUserDepartment(List<Integer> departmentIdList)
			throws Exception;

	/**
	 * @Title: getStaticDataDepartment
	 * @Description: TODO(从缓存中获取院系信息)
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List<Department> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月18日 下午1:51:47
	 * @throws
	 */
	public List<Department> getStaticDataDepartment() throws Exception;

	/**
	 * @Title: queryAllConditionDepartment
	 * @Description: TODO(条件查询院系)
	 * @param @param departmentIdList
	 * @param @param page
	 * @param @param rows
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return Page 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月20日 下午7:23:30
	 * @throws
	 */
	public List<Department> queryAllConditionDepartment(
			List<Integer> departmentIdList) throws Exception;
}
