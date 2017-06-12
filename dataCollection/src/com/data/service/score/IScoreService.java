package com.data.service.score;

import com.data.model.Score;
import com.data.model.Users;

/**
 * @ClassName: IScoreService
 * @Description: TODO(打分接口)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年6月2日 下午9:04:38
 */
public interface IScoreService {

	/**
	 * @Title: insertScore
	 * @Description: TODO(添加分数)
	 * @param @param scoreList
	 * @param @param users
	 * @param @throws Exception 设定文件
	 * @return void 返回类型
	 * @author: 孟祥瑞
	 * @date 2016年6月2日 下午9:17:02
	 * @throws
	 */
	public void insertScore(Score score, Users users);

}
