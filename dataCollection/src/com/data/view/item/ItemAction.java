package com.data.view.item;

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
import com.data.model.Item;
import com.data.model.LogType;
import com.data.model.Options;
import com.data.model.Question;
import com.data.model.QuestionOptions;
import com.data.model.Users;
import com.data.service.item.IItemService;
import com.data.tools.ComplexPropertyPreFilter;
import com.data.tools.Page;
import com.data.tools.Tree;
import com.data.view.base.BaseAction;

/**
 * @ClassName: ItemAction
 * @Description: TODO(调查项目Action)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年5月20日 下午4:38:36
 */
@Controller
@Scope("prototype")
public class ItemAction extends BaseAction {

	/**
	 * @Fields iStudentService : TODO(注入)
	 */
	@Autowired(required = true)
	private IItemService iItemService;

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	private Item item = getItemModel();
	/**
	 * @Fields jsonData : TODO(JSON的数据)
	 */
	private String jsonData = "";
	private Integer itemId;
	/**
	 * @Fields itemList : TODO(调查项目集合)
	 */
	private List<Item> itemList = null;

	/**
	 * @throws IOException
	 * @Title: queryItem
	 * @Description: TODO(查询已创建的调查项目)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月20日 下午4:44:36
	 * @throws
	 */
	public String queryItem() throws IOException {
		try {
			pager = iItemService.queryItem(item, super.getDepartmentIdList(),
					super.getPage(), super.getRows());

			complexPropertyPreFilter = new ComplexPropertyPreFilter();
			complexPropertyPreFilter
					.setIncludes(new HashMap<Class<?>, String[]>() {
						private static final long serialVersionUID = -8411128674046835592L;
						{
							put(Page.class, new String[] { "total", "rows",
									"pageSize" });
							put(Item.class,
									new String[] { "id", "name", "createTime",
											"users", "department",
											"description", "itemType",
											"isHaveQuestion" });
							put(Users.class, new String[] { "id", "fullname" });
							put(Department.class, new String[] { "id", "name" });
						}
					});
			super.getResponse().setHeader("Content-type",
					"text/html;charset=UTF-8");
			JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:MM";
			super.getResponse()
					.getWriter()
					.print(JSON.toJSONString(pager, complexPropertyPreFilter,
							SerializerFeature.WriteDateUseDateFormat));
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"查询调查项目信息失败" + e.getMessage(), super.getResourceName(),
					super.getRequest());
			super.sendJsonMessage(false, "查询调查项目信息失败");
			e.getStackTrace();
		}
		return null;
	}

	/**
	 * @throws IOException
	 * @Title: queryUserItem
	 * @Description: TODO(查看调查项目)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年6月2日 上午11:45:35
	 * @throws
	 */
	public String queryUserItem() throws IOException {
		try {
			item = iItemService.queryUserItem(itemId);
			super.getResponse().setHeader("Content-type",
					"text/html;charset=UTF-8");
			complexPropertyPreFilter = new ComplexPropertyPreFilter();
			complexPropertyPreFilter
					.setIncludes(new HashMap<Class<?>, String[]>() {
						private static final long serialVersionUID = -8411128674046835592L;
						{
							put(Item.class, new String[] { "id", "name",
									"questions" });
							put(QuestionOptions.class,
									new String[] { "options" });
							put(Question.class, new String[] { "id", "name",
									"orderNo", "type", "score",
									"questionOptionses" });
							put(Options.class, new String[] { "id", "orderNo",
									"name", "score" });
						}
					});
			JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
			super.getResponse()
					.getWriter()
					.print(JSON.toJSONString(item, complexPropertyPreFilter,
							SerializerFeature.WriteDateUseDateFormat));
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"查看调查项目失败" + e.getMessage(), super.getResourceName(),
					super.getRequest());

			super.sendJsonMessage(false, "查看调查项目失败！");
			e.getStackTrace();
		}
		return null;
	}

	/**
	 * @Title: queryHaveQuestionItem
	 * @Description: TODO(查找已创建问题的调查项目)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月26日 下午9:05:15
	 * @throws
	 */
	public String queryHaveQuestionItem() {
		try {
			itemList = iItemService.queryHaveQuestionItem(super
					.getDepartmentIdList());
			complexPropertyPreFilter = new ComplexPropertyPreFilter();
			complexPropertyPreFilter
					.setIncludes(new HashMap<Class<?>, String[]>() {
						private static final long serialVersionUID = -8411128674046835592L;
						{
							put(Tree.class,
									new String[] { "id", "text", "desc" });
						}
					});
			super.getResponse().setHeader("Content-type",
					"text/html;charset=UTF-8");
			super.getResponse()
					.getWriter()
					.print(JSON.toJSONString(this.getItemList(itemList),
							complexPropertyPreFilter,
							SerializerFeature.WriteNullStringAsEmpty,
							SerializerFeature.WriteNullListAsEmpty));
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"查找已创建问题的调查项目失败" + e.getMessage(), super.getResourceName(),
					super.getRequest());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title: getItemList
	 * @Description: TODO(转换Item格式)
	 * @param @param item
	 * @param @return 设定文件
	 * @return List<Tree> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月26日 下午9:15:57
	 * @throws
	 */
	private List<Tree> getItemList(List<Item> items) {
		treeList = new ArrayList<Tree>();
		Tree tree = null;
		for (int i = 0, len = items.size(); i < len; i++) {
			Item itemTmp = items.get(i);
			tree = new Tree();
			tree.setId(itemTmp.getId() + "");
			tree.setText(itemTmp.getName());
			tree.setDesc(itemTmp.getDescription());
			treeList.add(tree);
		}
		return treeList;
	}

	/**
	 * @Title: insertItem
	 * @Description: TODO(添加一个调查项目)
	 * @param @return
	 * @param @throws IOException 设定文件
	 * @return String 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月21日 下午1:04:21
	 * @throws
	 */
	public String insertItem() throws IOException {

		try {
			item.setUsers(userInfo);
			item.setDepartment(new Department(userInfo.getDepartmentID(), "", 0));
			iItemService.insertItem(item);
			super.sendJsonMessage(true, "增加调查项目成功！");
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"增加调查项目失败" + e.getMessage(), super.getResourceName(),
					super.getRequest());

			super.sendJsonMessage(false, "增加调查项目失败！");
		}
		return null;
	}

	/**
	 * @throws IOException
	 * @Title: insertQuestionOption
	 * @Description: TODO(添加问题项)
	 * @param @return 设定文件
	 * @return string 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月23日 下午4:31:30
	 * @throws
	 */
	public String insertQuestionOption() throws IOException {
		try {
			Item itemTmp = JSON.parseObject(jsonData, Item.class);
			iItemService.insertQuestionOption(itemTmp);
			super.sendJsonMessage(true, "您已添加问题项成功！");
		} catch (Exception e) {
			iLogerService.saveLog(LogType.EXCEPTION,
					"添加问题项失败" + e.getMessage(), super.getResourceName(),
					super.getRequest());
			super.sendJsonMessage(false, "添加问题项失败,请与管理员联系！");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title: getItemModel
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return Item 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月20日 下午6:15:50
	 * @throws
	 */
	private Item getItemModel() {
		if (null == item) {
			item = new Item();
			item.setDepartment(new Department());
		}
		return item;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getDepartmentArr() {
		return departmentArr;
	}

	public void setDepartmentArr(String departmentArr) {
		this.departmentArr = departmentArr;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
}
