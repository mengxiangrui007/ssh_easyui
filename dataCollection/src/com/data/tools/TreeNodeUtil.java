package com.data.tools;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TreeNodeUtil
 * @Description: TODO(id pid 转 id child)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年3月19日 下午12:16:23
 */
public class TreeNodeUtil {
	/**
	 * @Title: getfatherNode
	 * @Description 方法描述: 父节点
	 * @param 设定文件
	 *            ： @param treeDataList
	 * @param 设定文件
	 *            ： @return
	 * @return 返回类型：List<Tree>
	 * @throws
	 * @date 最后修改时间：2015年6月9日 下午6:39:26
	 */
	public final static List<Tree> getfatherNode(List<Tree> treeDataList) {
		List<Tree> newTreeDataList = new ArrayList<Tree>();
		for (Tree Tree : treeDataList) {
			if (Tree.getPid() == null) {
				// 获取父节点下的子节点
				Tree.setChildren(getChildrenNode(Tree.getId(), treeDataList));
				Tree.setState("open");
				newTreeDataList.add(Tree);
			}
		}
		return newTreeDataList;
	}

	/**
	 * @Title: getChildrenNode
	 * @Description 方法描述: 子节点
	 * @param 设定文件
	 *            ： @param pid
	 * @param 设定文件
	 *            ： @param treeDataList
	 * @param 设定文件
	 *            ： @return
	 * @return 返回类型：List<Tree>
	 * @throws
	 * @date 最后修改时间：2015年6月9日 下午6:39:50
	 */
	private final static List<Tree> getChildrenNode(String integer,
			List<Tree> treeDataList) {
		List<Tree> newTreeDataList = new ArrayList<Tree>();
		for (Tree Tree : treeDataList) {
			if (Tree.getPid() == null)
				continue;
			// 这是一个子节点
			if (Tree.getPid().equals(integer)) {
				// 递归获取子节点下的子节点
				Tree.setChildren(getChildrenNode(Tree.getId(), treeDataList));
				newTreeDataList.add(Tree);
			}
		}
		return newTreeDataList;
	}
}
