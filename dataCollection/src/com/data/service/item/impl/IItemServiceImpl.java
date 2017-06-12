package com.data.service.item.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.dao.base.IBaseDao;
import com.data.dao.item.impl.ItemDaoImpl;
import com.data.dao.options.impl.OptionsDaoImpl;
import com.data.dao.question.impl.QuestionDaoImpl;
import com.data.dao.questionOptions.impl.QuestionOptionsDaoImpl;
import com.data.model.Item;
import com.data.model.Options;
import com.data.model.Question;
import com.data.model.QuestionOptions;
import com.data.service.base.impl.BaseSericeImpl;
import com.data.service.item.IItemService;
import com.data.tools.DateUtil;
import com.data.tools.Page;
import com.data.tools.WebUtil;

@Service("iItemService")
public class IItemServiceImpl extends BaseSericeImpl<Item, Integer> implements
		IItemService {

	@Autowired(required = true)
	private ItemDaoImpl itemDaoImpl;
	/**
	 * @Fields iUserService : TODO(注入)
	 */
	@Autowired(required = true)
	private QuestionDaoImpl questionDaoImpl;
	/**
	 * @Fields optionsDaoImpl : TODO(选项Dao)
	 */
	@Autowired(required = true)
	private OptionsDaoImpl optionsDaoImpl;

	/**
	 * @Fields questionOptionsDaoImpl : TODO(中间表)
	 */
	@Autowired(required = true)
	private QuestionOptionsDaoImpl questionOptionsDaoImpl;
	/**
	 * @Fields userList : TODO(调查项目List)
	 */
	private List<Item> itemList = null;

	@Resource(name = "itemDaoImpl")
	@Override
	public void setiBaseDao(IBaseDao<Item, Integer> iBaseDao) {
		// TODO Auto-generated method stub
		super.setiBaseDao(iBaseDao);
	}

	@Override
	public Page queryItem(Item item, List<Integer> departmentIdList, int page,
			int rows) throws Exception {
		checkItemQueryCondition(item);
		sum = iBaseDao.countModelByProperties(propertyList.toArray(propertys),
				valuesList.toArray(), departmentIdList);
		pager = new Page(sum, page, rows);
		itemList = iBaseDao.getModelByProperties(
				propertyList.toArray(propertys), valuesList.toArray(),
				departmentIdList, pager.getCurrentPage(), pager.getPageSize());
		pager.setRows(itemList);
		return pager;
	}

	/**
	 * @Title: checkStudentQueryCondition
	 * @Description: TODO(用户的查询条件)
	 * @param @param student 设定文件
	 * @return void 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年3月19日 下午9:42:13
	 * @throws
	 */
	private void checkItemQueryCondition(Item item) {
		propertyList = new ArrayList<String>();
		valuesList = new ArrayList<Object>();
		if (item.getName() != null && item.getName().length() != 0) {
			propertyList.add("name");
			valuesList.add(item.getName());
		}
	}

	@Override
	public void insertItem(Item item) throws Exception {
		item.setId(Integer.parseInt(WebUtil.getPK() + ""));
		item.setCreateTime(DateUtil.getTimestamp());
		item.setIsHaveQuestion((short) 0);
		iBaseDao.save(item);
	}

	@Override
	public void insertQuestionOption(Item itemTmp) throws Exception {
		Set<Question> questions = itemTmp.getQuestions();
		Iterator<Question> iterator = questions.iterator();
		while (iterator.hasNext()) {
			Question question = iterator.next();
			question.setId(Integer.parseInt(WebUtil.getPK() + ""));
			question.setItem(itemTmp);
			questionDaoImpl.saveQuestion(question);
			// questionDaoImpl.save(question);
			if (question.getType() != 3) {
				Set<QuestionOptions> questionOptionses = question
						.getQuestionOptionses();
				Iterator<QuestionOptions> iterator2 = questionOptionses
						.iterator();
				while (iterator2.hasNext()) {
					QuestionOptions questionOptions = iterator2.next();
					Options option = new Options();
					option.setName(questionOptions.getName());
					option.setOrderNo(questionOptions.getOrderNo());
					option.setScore(questionOptions.getScore());
					option.setId(Integer.parseInt(WebUtil.getPK() + ""));
					optionsDaoImpl.saveOptions(option);
					// optionsDaoImpl.save(option);
					QuestionOptions questionOption = new QuestionOptions(
							question, option);
					questionOption
							.setId(Integer.parseInt(WebUtil.getPK() + ""));
					questionOptionsDaoImpl.saveQuestionOptions(questionOption);
					/*
					 * questionOptionsDaoImpl.save(new QuestionOptions(question,
					 * option));
					 */
				}
			}
		}
		// 更新状态
		itemDaoImpl.updateItemIsHaveQuestion(itemTmp);
	}

	@Override
	public List<Item> queryHaveQuestionItem(List<Integer> departmentIdList)
			throws Exception {
		return itemDaoImpl.queryHaveQuestionItem(departmentIdList);
	}

	@Override
	public Item queryUserItem(Integer itemId) {
		return iBaseDao.getById(itemId);
	}
}
