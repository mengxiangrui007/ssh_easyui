package com.data.service.teacher;

import java.util.List;

import com.data.model.Teacher;
import com.data.tools.Page;

/**
 * @ClassName: ITeacherService
 * @Description: TODO(教师接口)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年3月17日 下午1:24:11
 */
public interface ITeacherService {

	/**
	 * @Title: queryTeacher
	 * @Description: TODO(查询教师)
	 * @param @param userInfo
	 * @param @param page
	 * @param @param rows
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return Page 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月17日 下午1:38:24
	 * @throws
	 */
	public Page queryTeacher(Teacher teacher, List<Integer> departmentIdList,
			int page, int rows) throws Exception;

}
