package com.data.view.teacher;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.data.model.Department;
import com.data.model.LogType;
import com.data.model.Teacher;
import com.data.model.Users;
import com.data.service.teacher.ITeacherService;
import com.data.tools.ComplexPropertyPreFilter;
import com.data.tools.Page;
import com.data.view.base.BaseAction;

/**
 * @ClassName: TeacherAction
 * @Description: TODO(教师Action)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年3月17日 下午1:22:58
 */
@Controller
@Scope("prototype")
public class TeacherAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	/**
	 * @Fields iDepartmentService : TODO(注入)
	 */
	@Autowired(required = true)
	private ITeacherService iTeacherService;
	/**
	 * @Fields teacher : TODO(创建一个Bean)
	 */
	private Teacher teacher = getTeacherModel();

	/**
	 * @Title: queryTeacher
	 * @Description: TODO(查询教师信息)
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月17日 下午1:35:29
	 * @throws
	 */
	public String queryTeacher() throws Exception {
		try {
			pager = iTeacherService.queryTeacher(teacher,
					super.getDepartmentIdList(), super.getPage(),
					super.getRows());
			complexPropertyPreFilter = new ComplexPropertyPreFilter();
			complexPropertyPreFilter
					.setIncludes(new HashMap<Class<?>, String[]>() {
						private static final long serialVersionUID = -8411128674046835592L;
						{
							put(Page.class, new String[] { "total", "rows",
									"pageSize" });
							put(Teacher.class, new String[] { "id", "teachNo",
									"users", "department" });
							put(Users.class,
									new String[] { "fullname", "email" });
							put(Department.class, new String[] { "name" });
						}
					});
			super.getResponse().setHeader("Content-type",
					"text/html;charset=UTF-8");
			super.getResponse()
					.getWriter()
					.print(JSON.toJSONString(pager, complexPropertyPreFilter,
							SerializerFeature.WriteDateUseDateFormat,
							SerializerFeature.WriteNullListAsEmpty));
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"查询教师信息失败" + e.getMessage(), super.getResourceName(),
					super.getRequest());
			super.sendJsonMessage(false, "查询教师信息失败");
			e.getStackTrace();

		}
		return null;
	}

	/**
	 * @Title: getTeacherModel
	 * @Description: TODO(单例默认创建Teacher)
	 * @param @return 设定文件
	 * @return Teacher 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月17日 下午1:48:49
	 * @throws
	 */
	private Teacher getTeacherModel() {
		if (null == teacher) {
			teacher = new Teacher();
			teacher.setDepartment(new Department());
			teacher.setUsers(new Users());
		}
		return teacher;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getDepartmentArr() {
		return departmentArr;
	}

	public void setDepartmentArr(String departmentArr) {
		this.departmentArr = departmentArr;
	}
}
