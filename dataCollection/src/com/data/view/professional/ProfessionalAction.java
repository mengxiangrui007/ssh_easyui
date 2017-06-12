package com.data.view.professional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.data.model.Department;
import com.data.model.LogType;
import com.data.model.Professional;
import com.data.model.Student;
import com.data.service.department.IDepartmentService;
import com.data.service.professional.IProfessionalService;
import com.data.tools.ComplexPropertyPreFilter;
import com.data.tools.Tree;
import com.data.view.base.BaseAction;

/**
 * @ClassName: ProfessionalAction
 * @Description: TODO(学科专业)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年5月27日 下午3:23:20
 */
@Controller
@Scope("prototype")
public class ProfessionalAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	/**
	 * @Fields iProfessionalService : TODO(注入)
	 */
	@Autowired(required = true)
	private IProfessionalService iProfessionalService;
	/**
	 * @Fields iDepartmentService : TODO(注入)
	 */
	@Autowired(required = true)
	private IDepartmentService iDepartmentService;
	/**
	 * @Fields professional : TODO(初始化值)
	 */
	private Professional professional = createModel();

	/**
	 * @Fields professionalList : TODO(专业集合)
	 */
	private List<Professional> professionalList = null;

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
				if (id.equals(professionalTmp.getPid())) {
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
				if (id.equals(student.getProfessionalId())) {
					tmpTree = new Tree();
					tmpTree.setId(student.getGread() + "");
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
				if (id.equals(stu1.getGread())) {
					tmpTree = new Tree();
					tmpTree.setId(stu1.getClass_() + "");
					tmpTree.setText(reaverNumberClass(stu1.getClass_()));
					tmpTree.setType("3");
					childTreeList.add(tmpTree);
				}
			}
		}
		return childTreeList;
	}

	/**
	 * @Title: createModel
	 * @Description: TODO(单例)
	 * @param @return 设定文件
	 * @return Professional 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月27日 下午3:26:52
	 * @throws
	 */
	private Professional createModel() {
		if (professional == null) {
			professional = new Professional();
		}
		return professional;
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
}
