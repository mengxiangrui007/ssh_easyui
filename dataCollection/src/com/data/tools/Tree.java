package com.data.tools;

import java.util.List;

/**
 * @ClassName: Tree
 * @Description: TODO(Tree)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年3月17日 下午4:40:58
 */
/**
 * @ClassName: Tree
 * @Description: TODO(树工具)
 * @author: 孟祥瑞
 * @company: 赤峰宏微网络科技有限公司
 * @date 2016年3月17日 下午4:51:25
 */
public class Tree {

	/**
	 * @Fields id : TODO(ID)
	 */
	private String id;
	private String pid;
	/**
	 * @Fields text : TODO(文本信息)
	 */
	private String text;
	/**
	 * @Fields checked : TODO(是否选中)
	 */
	private boolean checked;

	/**
	 * @Fields state : TODO(closed为关闭)
	 */
	private String state;

	/**
	 * @Fields children : TODO(孩子节点)
	 */
	private List<Tree> children;

	/**
	 * @Fields paramType : TODO(属性字段类型)
	 */
	private String paramType;

	/**
	 * @Fields paramObject : TODO(属性对象)
	 */
	private Object paramObject;
	/**
	 * @Fields iconCls : TODO(图标)
	 */
	private String iconCls;

	/**
	 * @Fields desc : TODO(描述)
	 */
	private String desc;

	/**
	 * @Fields type : TODO(类型)
	 */
	private String type;

	/**
	 * @Fields file : TODO(路径)
	 */
	private String file;

	/**
	 * @Fields name : TODO(名称)
	 */
	private String name;

	/**
	 * @Fields pId : TODO(父节点)
	 */
	private String pId;
	private boolean open;

	public String getId() {
		return id;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getText() {
		return text;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Tree> getChildren() {
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public Object getParamObject() {
		return paramObject;
	}

	public void setParamObject(Object paramObject) {
		this.paramObject = paramObject;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
}
