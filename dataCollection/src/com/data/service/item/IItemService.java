package com.data.service.item;

import java.util.List;

import com.data.model.Item;
import com.data.tools.Page;

/**
 * @ClassName: IItemService
 * @Description: TODO(调查项目Service)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年5月20日 下午4:59:55
 */
public interface IItemService {

	/**
	 * @Title: queryItem
	 * @Description: TODO(查询调查项目)
	 * @param @param item
	 * @param @param departmentIdList
	 * @param @param page
	 * @param @param rows
	 * @param @return 设定文件
	 * @return Page 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月21日 下午1:06:18
	 * @throws
	 */
	public Page queryItem(Item item, List<Integer> departmentIdList, int page,
			int rows) throws Exception;

	/**
	 * @Title: insertItem
	 * @Description: TODO(添加调查项目)
	 * @param @param item 设定文件
	 * @return void 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月21日 下午1:06:29
	 * @throws
	 */
	public void insertItem(Item item) throws Exception;

	/**
	 * @param itemTmp
	 * @Title: insertQuestionOption
	 * @Description: TODO(添加问题项)
	 * @param @throws Exception 设定文件
	 * @return void 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月24日 下午8:52:09
	 * @throws
	 */
	public void insertQuestionOption(Item itemTmp) throws Exception;

	/**
	 * @Title: queryHaveQuestionItem
	 * @Description: TODO(查找已创建问题的调查项目)
	 * @param @param departmentIdList
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List<Item> 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月26日 下午9:18:16
	 * @throws
	 */
	public List<Item> queryHaveQuestionItem(List<Integer> departmentIdList)
			throws Exception;

	/**
	 * @Title: queryUserItem
	 * @Description: TODO(查看问题项)
	 * @param @param item
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return Item 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年6月2日 上午11:48:11
	 * @throws
	 */
	public Item queryUserItem(Integer itemId);
}
