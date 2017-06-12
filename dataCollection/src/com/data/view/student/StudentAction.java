package com.data.view.student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.data.model.Department;
import com.data.model.LogType;
import com.data.model.Professional;
import com.data.model.Role;
import com.data.model.Student;
import com.data.model.Users;
import com.data.service.department.IDepartmentService;
import com.data.service.professional.IProfessionalService;
import com.data.service.student.IStudentService;
import com.data.tools.ComplexPropertyPreFilter;
import com.data.tools.Page;
import com.data.tools.Tree;
import com.data.view.base.BaseAction;

/**
 * @ClassName: StudentAction
 * @Description: TODO(学生Action)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年2月28日 上午11:21:24
 */
@Controller
@Scope("prototype")
public class StudentAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	/**
	 * @Fields iStudentService : TODO(注入)
	 */
	@Autowired(required = true)
	private IStudentService iStudentService;

	private Student student = getStudentModel();
	@Autowired(required = true)
	private IDepartmentService iDepartmentService;
	/**
	 * @Fields professionalStr : TODO(选择的专业串)
	 */
	private String professionalIdStr = "";

	/**
	 * @Fields greadIdStr : TODO(年级串)
	 */
	private String greadIdStr = "";
	/**
	 * @Fields iProfessionalService : TODO(注入)
	 */
	@Autowired(required = true)
	private IProfessionalService iProfessionalService;
	/**
	 * @Fields professionalList : TODO(专业集合)
	 */
	private List<Professional> professionalList = null;

	/**
	 * @Fields studentList : TODO(学生集合)
	 */
	private List<Student> studentList = new ArrayList<Student>();

	/**
	 * @Title: queryStudent
	 * @Description: TODO(查询学生信息)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年2月28日 上午11:29:07
	 * @throws
	 */
	public String queryStudent() throws Exception {
		try {
			pager = iStudentService.queryStudent(student,
					super.getDepartmentIdList(), super.getPage(),
					super.getRows());
			complexPropertyPreFilter = new ComplexPropertyPreFilter();
			complexPropertyPreFilter
					.setIncludes(new HashMap<Class<?>, String[]>() {
						private static final long serialVersionUID = -8411128674046835592L;
						{
							put(Page.class, new String[] { "total", "rows",
									"pageSize" });
							put(Student.class, new String[] { "id",
									"studentNo", "gread", "class_", "users",
									"department", "professional" });
							put(Professional.class, new String[] { "id",
									"professionalName", "professionLevel",
									"professionalSystem" });
							put(Users.class, new String[] { "id", "fullname",
									"email" });
							put(Department.class, new String[] { "name" });
						}
					});
			super.getResponse().setHeader("Content-type",
					"text/html;charset=UTF-8");
			JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
			super.getResponse()
					.getWriter()
					.print(JSON.toJSONString(pager, complexPropertyPreFilter,
							SerializerFeature.WriteDateUseDateFormat));
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"查询学生信息失败" + e.getMessage(), super.getResourceName(),
					super.getRequest());
			super.sendJsonMessage(false, "查询学生信息失败");
			e.getStackTrace();
		}
		return null;
	}

	/**
	 * @throws IOException
	 * @Title: insertStudent
	 * @Description: TODO(添加一个学生)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月28日 下午3:29:57
	 * @throws
	 */
	public String insertStudent() throws IOException {
		try {
			if (student.getDepartment().getId() == null
					|| student.getDepartment().getId() == 0) {
				student.getDepartment().setId(userInfo.getDepartmentID());
			}
			iStudentService.insertStudent(student);
			super.sendJsonMessage(true, "增加学生成功！");
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION, "增加学生失败" + e.getMessage(),
					super.getResourceName(), super.getRequest());

			super.sendJsonMessage(false, "增加学生失败！");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @throws IOException
	 * @Title: showStudent
	 * @Description: TODO(查询学生信息)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年4月16日 下午12:11:47
	 * @throws
	 */
	public String showStudent() throws IOException {
		try {
			student = iStudentService.showStudent(student);
			super.getResponse().setHeader("Content-type",
					"text/html;charset=UTF-8");
			complexPropertyPreFilter = new ComplexPropertyPreFilter();
			complexPropertyPreFilter
					.setIncludes(new HashMap<Class<?>, String[]>() {
						private static final long serialVersionUID = -8411128674046835592L;
						{
							put(Student.class, new String[] { "id",
									"studentNo", "startTime", "users",
									"department" });
							put(Users.class, new String[] { "fullname",
									"email", "sexy", "password", "mobilephone",
									"role", "description" });
							put(Department.class, new String[] { "id", "name" });
							put(Role.class, new String[] { "id", "name" });
						}
					});
			JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
			super.getResponse()
					.getWriter()
					.print(JSON.toJSONString(student, complexPropertyPreFilter,
							SerializerFeature.WriteDateUseDateFormat));
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION, "查询学生失败" + e.getMessage(),
					super.getResourceName(), super.getRequest());

			super.sendJsonMessage(false, "查询学生失败！");
			e.getStackTrace();
		}
		return null;
	}

	/**
	 * @throws IOException
	 * @Title: ajaxValidateStudent
	 * @Description: TODO(ajax验证学生此学生是否已存在)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月18日 下午1:52:17
	 * @throws
	 */
	public String ajaxValidateStudent() throws IOException {
		try {
			flag = iStudentService.ajaxValidateStudent(student);
			if (flag) {
				super.sendJsonMessage(true, "已存在此学生！");
			} else {
				super.sendJsonMessage(false, "");
			}
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"验证学生信息失败" + e.getMessage(), super.getResourceName(),
					super.getRequest());
			super.sendJsonMessage(false, "服务器发生异常请于管理员联系！");
		}
		return null;
	}

	/**
	 * @throws IOException
	 * @Title: queryProfessional
	 * @Description: TODO(查询院系的专业)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月27日 下午3:28:35
	 * @throws
	 */
	public String queryProfessional() throws IOException {
		try {
			List<Department> queryUserDepartment = iDepartmentService
					.queryUserDepartment(super.getDepartmentIdList());
			professionalList = iProfessionalService.queryProfessional(super
					.getDepartmentIdList());
			complexPropertyPreFilter = new ComplexPropertyPreFilter();
			complexPropertyPreFilter
					.setIncludes(new HashMap<Class<?>, String[]>() {
						private static final long serialVersionUID = -8411128674046835592L;
						{
							put(Tree.class, new String[] { "id", "text",
									"children", "type" });
						}
					});
			super.getResponse().setHeader("Content-type",
					"text/html;charset=UTF-8");
			super.getResponse()
					.getWriter()
					.print(JSON.toJSONString(this.getTreeList(
							queryUserDepartment, professionalList, null, null),
							complexPropertyPreFilter,
							SerializerFeature.WriteDateUseDateFormat));
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"查询专业信息失败" + e.getMessage(), super.getResourceName(),
					super.getRequest());
			super.sendJsonMessage(false, "查询专业学生信息失败");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @throws IOException
	 * @Title: queryGread
	 * @Description: TODO(查询年级)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月28日 下午2:52:29
	 * @throws
	 */
	public String queryGread() throws IOException {
		try {
			List<Department> queryUserDepartment = iDepartmentService
					.queryUserDepartment(super.getDepartmentIdList());
			Integer[] professionalIdArr = (Integer[]) ConvertUtils.convert(
					professionalIdStr.split(","), Integer.class);
			studentList = iStudentService.queryGread(Arrays
					.asList(professionalIdArr));
			List<Professional> queryProfessional = iProfessionalService
					.queryChoiceProfessional(Arrays.asList(professionalIdArr));
			complexPropertyPreFilter = new ComplexPropertyPreFilter();
			complexPropertyPreFilter
					.setIncludes(new HashMap<Class<?>, String[]>() {
						private static final long serialVersionUID = -8411128674046835592L;
						{
							put(Tree.class, new String[] { "id", "text",
									"children", "type" });
						}
					});
			JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
			super.getResponse().setHeader("Content-type",
					"text/html;charset=UTF-8");
			super.getResponse()
					.getWriter()
					.print(JSON.toJSONString(this.getTreeList(
							queryUserDepartment, queryProfessional,
							studentList, null), complexPropertyPreFilter,
							SerializerFeature.WriteDateUseDateFormat));
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"查询班级信息失败" + e.getMessage(), super.getResourceName(),
					super.getRequest());
			super.sendJsonMessage(false, "服务器发生异常请于管理员联系！");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @throws IOException
	 * @Title: queryClass
	 * @Description: TODO(查询班级)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月28日 下午4:26:26
	 * @throws
	 */
	public String queryClass() throws IOException {
		try {
			List<Department> queryUserDepartment = iDepartmentService
					.queryUserDepartment(super.getDepartmentIdList());
			Integer[] professionalIdArr = (Integer[]) ConvertUtils.convert(
					professionalIdStr.split(","), Integer.class);
			// 年级
			studentList = iStudentService.queryGread(Arrays
					.asList(professionalIdArr));
			// 班级
			String[] professionaGreadIdArr = (String[]) ConvertUtils.convert(
					greadIdStr.split(","), String.class);

			List<Student> students = new ArrayList<Student>();
			students = iStudentService.queryClass(Arrays
					.asList(professionaGreadIdArr));
			List<Professional> queryProfessional = new ArrayList<Professional>();
			queryProfessional = iProfessionalService
					.queryChoiceProfessional(Arrays.asList(professionalIdArr));
			complexPropertyPreFilter = new ComplexPropertyPreFilter();
			complexPropertyPreFilter
					.setIncludes(new HashMap<Class<?>, String[]>() {
						private static final long serialVersionUID = -8411128674046835592L;
						{
							put(Tree.class, new String[] { "id", "text",
									"children", "type" });
						}
					});
			JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
			super.getResponse().setHeader("Content-type",
					"text/html;charset=UTF-8");
			super.getResponse()
					.getWriter()
					.print(JSON.toJSONString(this.getTreeList(
							queryUserDepartment, queryProfessional,
							studentList, students), complexPropertyPreFilter,
							SerializerFeature.WriteDateUseDateFormat));
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"查询班级信息失败" + e.getMessage(), super.getResourceName(),
					super.getRequest());
			super.sendJsonMessage(false, "服务器发生异常请于管理员联系！");
			e.printStackTrace();
		}
		return null;
	}

	private List<Tree> getTreeList(List<Department> queryUserDepartment,
			List<Professional> professionals, List<Student> greadList,
			List<Student> classList) {
		List<Tree> treeDepartment = new ArrayList<Tree>();
		if (queryUserDepartment != null && queryUserDepartment.size() > 0) {
			Tree tree = null;
			Department department = null;
			for (int i = 0, len = queryUserDepartment.size(); i < len; i++) {
				department = queryUserDepartment.get(i);
				tree = new Tree();
				tree.setId(department.getId() + "");
				tree.setText(department.getName());
				tree.setType("0");
				List<Tree> professionalChildTreeList = this
						.getProfessionalChildTreeList(tree.getId(),
								professionals, greadList, classList);
				if (professionalChildTreeList.size() > 0) {
					tree.setChildren(professionalChildTreeList);
					treeDepartment.add(tree);
				}
			}
		}
		return treeDepartment;
	}

	private List<Tree> getProfessionalChildTreeList(String id,
			List<Professional> professionals, List<Student> greadList,
			List<Student> classList) {
		List<Tree> treeProfessional = new ArrayList<Tree>();
		if (professionals != null && professionals.size() > 0) {
			Tree tree = null;
			Professional professionalTmp = null;
			for (int i = 0, len = professionals.size(); i < len; i++) {
				professionalTmp = professionals.get(i);
				if (id.equals(professionalTmp.getPid() + "")) {
					tree = new Tree();
					tree.setId(professionalTmp.getId() + "");
					tree.setText(professionalTmp.getProfessionalName());
					tree.setType("1");
					List<Tree> greadChildTreeList = this.getGreadChildTreeList(
							tree.getId(), greadList, classList);
					if (greadList == null && classList == null) {
						tree.setChildren(greadChildTreeList);
						treeProfessional.add(tree);
					} else {
						if (greadChildTreeList.size() > 0) {
							tree.setChildren(greadChildTreeList);
							treeProfessional.add(tree);
						}
					}

				}
			}
		}
		return treeProfessional;
	}

	private List<Tree> getGreadChildTreeList(String id,
			List<Student> greadList, List<Student> classList) {
		List<Tree> childTreeList = new ArrayList<Tree>();
		if (greadList != null && greadList.size() > 0) {
			Tree tmpTree = null;
			for (Student student : greadList) {
				if (id.equals(student.getProfessionalId() + "")) {
					tmpTree = new Tree();
					tmpTree.setId(student.getProfessionalId() + ":"
							+ student.getGread());
					tmpTree.setText(student.getGread() + "级");
					tmpTree.setType("2");
					List<Tree> classChildTreeList = this.getClassChildTreeList(
							tmpTree.getId(), classList);
					if (classList == null) {
						tmpTree.setChildren(classChildTreeList);
						childTreeList.add(tmpTree);
					} else {
						if (classChildTreeList.size() > 0) {
							tmpTree.setChildren(classChildTreeList);
							childTreeList.add(tmpTree);
						}
					}
				}
			}
		}
		return childTreeList;
	}

	private List<Tree> getClassChildTreeList(String id, List<Student> classList) {
		List<Tree> childTreeList = new ArrayList<Tree>();
		if (classList != null && classList.size() > 0) {
			Tree tmpTree = null;
			for (Student stu1 : classList) {
				if (id.equals(stu1.getProfessionalId() + ":" + stu1.getGread())) {
					tmpTree = new Tree();
					tmpTree.setId(stu1.getDepartmentId() + ":"
							+ stu1.getProfessionalId() + ":" + stu1.getGread()
							+ ":" + stu1.getClass_());
					tmpTree.setText(reaverNumberClass(stu1.getClass_()));
					tmpTree.setType("3");
					childTreeList.add(tmpTree);
				}
			}
		}
		return childTreeList;
	}

	/**
	 * @Title: getStudentModel
	 * @Description: TODO(单例模式创建student)
	 * @param @return 设定文件
	 * @return Student 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月19日 下午9:37:37
	 * @throws
	 */
	private Student getStudentModel() {
		if (null == student) {
			student = new Student();
			student.setDepartment(new Department());
			student.setUsers(new Users());
			student.setProfessional(new Professional());
		}
		return student;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Integer> getDepartmentIdList() {
		return departmentIdList;
	}

	public void setDepartmentIdList(List<Integer> departmentIdList) {
		this.departmentIdList = departmentIdList;
	}

	public String getDepartmentArr() {
		return departmentArr;
	}

	/**
	 * @Title: reaverNumberClass
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param classNum
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月28日 下午11:28:41
	 * @throws
	 */
	public static String reaverNumberClass(Integer classNum) {
		String[] classArr = new String[20];
		classArr[0] = "一班";
		classArr[1] = "二班";
		classArr[2] = "三班";
		classArr[3] = "四班";
		classArr[4] = "五班";
		classArr[5] = "六班";
		classArr[6] = "七班";
		classArr[7] = "八班";
		classArr[8] = "九班";
		classArr[9] = "十班";
		return classArr[classNum - 1];
	}

	public void setDepartmentArr(String departmentArr) {
		this.departmentArr = departmentArr;
	}

	public String getProfessionalIdStr() {
		return professionalIdStr;
	}

	public void setProfessionalIdStr(String professionalIdStr) {
		this.professionalIdStr = professionalIdStr;
	}

	public String getGreadIdStr() {
		return greadIdStr;
	}

	public void setGreadIdStr(String greadIdStr) {
		this.greadIdStr = greadIdStr;
	}

}