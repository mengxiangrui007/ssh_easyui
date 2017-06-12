package com.data.dao.options.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.data.dao.base.impl.BaseDaoImpl;
import com.data.model.Options;

/**
 * @ClassName: OptionsDaoImpl
 * @Description: TODO(选项特殊业务处理)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年5月25日 上午10:16:19
 */
@Repository("optionsDaoImpl")
public class OptionsDaoImpl extends BaseDaoImpl<Options, Integer> {

	/**
	 * @Title: saveOptions
	 * @Description: TODO(保存选项)
	 * @param @param option
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年5月25日 上午10:22:22
	 * @throws
	 */
	public int saveOptions(Options option) throws Exception {
		sql = "INSERT INTO options (ID,ORDER_NO, NAME, SCORE) VALUES (?,?,?,?)";
		Query query = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql);
		query.setParameter(0, option.getId());
		query.setParameter(1, option.getOrderNo());
		query.setParameter(2, option.getName());
		query.setParameter(3, option.getScore());
		return query.executeUpdate();
	}
}
