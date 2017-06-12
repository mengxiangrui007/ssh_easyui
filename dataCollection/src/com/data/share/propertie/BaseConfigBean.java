package com.data.share.propertie;

/**
* @ClassName: BaseConfigBean
* @Description: TODO(全局变量类)
* @author: 孟祥瑞
* @company: 赤峰宏微网络科技有限公司
* @date 2016年2月21日 下午2:21:34
*/
public class BaseConfigBean{

    /**
     * @Fields ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE : 项目共享数据key
     */
    public static final String ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE = "root_web_application_context_attribute";

    /**
     * @Fields systemVersion : 系统版本号
     */
    private String systemVersion;

    /**
     * @Fields ROOT_WEB_SESSION_USER : 当前session的用户key
     */
    public static final String ROOT_WEB_SESSION_USER = "root_web_session_user";

    /**
     * @Fields ftpAddr : FTP服务器地址
     */
    private String ftpAddr;

    /**
     * @Fields ftpAccount : FTP用户名
     */
    private String ftpAccount;

    /**
     * @Fields ftpPasswd : FTP密码
     */
    private String ftpPasswd;
    /**
     * @Fields captchaCodeLength :验证码最小长度设置
     */
    private int captchaCodeMinLength;

    /**
     * @Fields captchaCodeMaxLength : 验证码最大长度设置
     */
    private int captchaCodeMaxLength;

    /**
     * @Fields captchaBackGroundColor : 验证码背景颜色
     */
    private String captchaBackGroundColor;

    /**
     * @Fields captchaImgWidth : 验证码图片宽度（单位：像素px）
     */
    private int captchaImgWidth;

    /**
     * @Fields captchaImgHeight : 验证码图片高度（单位：像素px）
     */
    private int captchaImgHeight;

    /**
     * @Fields captchaMaxFontSize : 验证码最大字体大小（单位：像素px）
     */
    private int captchaMaxFontSize;

    /**
     * @Fields captchaMinFontSize : 验证码最小字体大小（单位：像素px）
     */
    private int captchaMinFontSize;
    /**
     * @Fields captchaCodeChars : 验证码拥有的字符集合
     */
    private String captchaCodeChars;
    /**
    * @Fields pageSize : TODO(页面显示条目数)
    */
    private int pageSize;
	public String getSystemVersion() {
		return systemVersion;
	}
	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}
	public String getFtpAddr() {
		return ftpAddr;
	}
	public void setFtpAddr(String ftpAddr) {
		this.ftpAddr = ftpAddr;
	}
	public String getFtpAccount() {
		return ftpAccount;
	}
	public void setFtpAccount(String ftpAccount) {
		this.ftpAccount = ftpAccount;
	}
	public String getFtpPasswd() {
		return ftpPasswd;
	}
	public void setFtpPasswd(String ftpPasswd) {
		this.ftpPasswd = ftpPasswd;
	}
	public int getCaptchaCodeMinLength() {
		return captchaCodeMinLength;
	}
	public void setCaptchaCodeMinLength(int captchaCodeMinLength) {
		this.captchaCodeMinLength = captchaCodeMinLength;
	}
	public int getCaptchaCodeMaxLength() {
		return captchaCodeMaxLength;
	}
	public void setCaptchaCodeMaxLength(int captchaCodeMaxLength) {
		this.captchaCodeMaxLength = captchaCodeMaxLength;
	}
	public String getCaptchaBackGroundColor() {
		return captchaBackGroundColor;
	}
	public void setCaptchaBackGroundColor(String captchaBackGroundColor) {
		this.captchaBackGroundColor = captchaBackGroundColor;
	}
	public int getCaptchaImgWidth() {
		return captchaImgWidth;
	}
	public void setCaptchaImgWidth(int captchaImgWidth) {
		this.captchaImgWidth = captchaImgWidth;
	}
	public int getCaptchaImgHeight() {
		return captchaImgHeight;
	}
	public void setCaptchaImgHeight(int captchaImgHeight) {
		this.captchaImgHeight = captchaImgHeight;
	}
	public int getCaptchaMaxFontSize() {
		return captchaMaxFontSize;
	}
	public void setCaptchaMaxFontSize(int captchaMaxFontSize) {
		this.captchaMaxFontSize = captchaMaxFontSize;
	}
	public int getCaptchaMinFontSize() {
		return captchaMinFontSize;
	}
	public void setCaptchaMinFontSize(int captchaMinFontSize) {
		this.captchaMinFontSize = captchaMinFontSize;
	}
	public String getCaptchaCodeChars() {
		return captchaCodeChars;
	}
	public void setCaptchaCodeChars(String captchaCodeChars) {
		this.captchaCodeChars = captchaCodeChars;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
    
}
