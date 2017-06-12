package com.data.service.professional;

import java.util.List;

import com.data.model.Professional;

/**
 * @ClassName: IProfessionalService
 * @Description: TODO(院系专业)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年5月27日 下午3:39:56
 */
public interface IProfessionalService {

	/**
	 * @Title: queryProfessional
	 * @Description: TODO(查询院系专业)
	 * @param @param departmentIdList
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List<Professional> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月27日 下午3:50:32
	 * @throws
	 */
	public List<Professional> queryProfessional(List<Integer> departmentIdList)
			throws Exception;

	/**
	 * @Title: queryAllProfessional
	 * @Description: TODO(获取所有的专业)
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List<Professional> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月27日 下午4:02:57
	 * @throws
	 */
	public List<Professional> queryAllProfessional() throws Exception;

	/**
	 * @Title: getStaticProfessional
	 * @Description: TODO(从缓存中获取专业集合)
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List<Professional> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月27日 下午4:07:54
	 * @throws
	 */
	public List<Professional> getStaticProfessional() throws Exception;

	public List<Professional> queryChoiceProfessional(List<Integer> asList)
			throws Exception;

}
