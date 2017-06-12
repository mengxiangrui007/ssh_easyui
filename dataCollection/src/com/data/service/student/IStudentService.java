package com.data.service.student;

import java.util.List;

import com.data.model.Student;
import com.data.tools.Page;

/**
 * @ClassName: IStudentService
 * @Description: TODO(学生接口)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年2月19日 下午3:53:40
 */
public interface IStudentService {

	/**
	 * @Title: queryStudent
	 * @Description: TODO(查询学生信息)
	 * @param @param student
	 * @param @param departmentIdList
	 * @param @param page
	 * @param @param rows
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return Page 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月19日 下午9:43:40
	 * @throws
	 */
	public Page queryStudent(Student student, List<Integer> departmentIdList,
			int page, int rows) throws Exception;

	/**
	 * @Title: insertStudent
	 * @Description: TODO(添加一个学生)
	 * @param @param student
	 * @param @throws Exception 设定文件
	 * @return void 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月28日 下午3:35:01
	 * @throws
	 */
	public void insertStudent(Student student) throws Exception;

	/**
	 * @Title: showStudent
	 * @Description: TODO(查询一个学生)
	 * @param @param student
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return Student 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年4月16日 下午12:20:21
	 * @throws
	 */
	public Student showStudent(Student student) throws Exception;

	/**
	 * @Title: ajaxValidateStudent
	 * @Description: TODO(验证学生是否已存在)
	 * @param @param student
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return boolean 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月18日 下午1:58:25
	 * @throws
	 */
	public boolean ajaxValidateStudent(Student student) throws Exception;

	/**
	 * @Title: queryGread
	 * @Description: TODO(查询年级)
	 * @param @param asList
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List<Student> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月28日 下午3:03:36
	 * @throws
	 */
	public List<Student> queryGread(List<Integer> asList) throws Exception;

	/**
	 * @Title: queryClass
	 * @Description: TODO(查询搬家)
	 * @param @param asList
	 * @param @param asList2
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List<Student> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月28日 下午4:32:57
	 * @throws
	 */
	public List<Student> queryClass(List<String> professionaGreadList)
			throws Exception;
}
