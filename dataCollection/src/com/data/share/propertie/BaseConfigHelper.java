package com.data.share.propertie;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;

import com.data.model.Users;
import com.data.tools.PropertiesUtil;
import com.data.tools.WebUtil;

/**
* @ClassName: BaseConfigHelper
* @Description: TODO(基础类配置)
* @author: 孟祥瑞
* @company: 赤峰宏微网络科技有限公司
* @date 2016年2月21日 下午2:24:54
*/
public class BaseConfigHelper {
	
	/**
	* @Title: setBaseConfig
	* @Description: TODO(设置全局变量的函数)
	* @param @param context
	* @param @param baseconfigbean    设定文件
	* @return void    返回类型
	* @author: 孟祥瑞
	* @date 2016年2月21日 下午2:25:17
	* @throws
	*/
	public static void setBaseConfig(ServletContext context,BaseConfigBean baseconfigbean){
		context.setAttribute(BaseConfigBean.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, baseconfigbean);
	}
	
	/**
	* @Title: getBaseConfig
	* @Description: TODO(获取全局变量的函数)
	* @param @param context
	* @param @return    设定文件
	* @return BaseConfigBean    返回类型
	* @author: 孟祥瑞
	* @date 2016年2月21日 下午2:25:33
	* @throws
	*/
	public static BaseConfigBean getBaseConfig(ServletContext context){
		return (BaseConfigBean)context.getAttribute(BaseConfigBean.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	}
	/**
	* @Title: getUser
	* @Description: TODO(在session中获取个人user对象)
	* @param @param request
	* @param @return    设定文件
	* @return User    返回类型
	* @author: 孟祥瑞
	* @date 2016年2月21日 下午2:25:47
	* @throws
	*/
	public static Users getUser(HttpServletRequest request){
		return (Users)(request.getSession().getAttribute(BaseConfigBean.ROOT_WEB_SESSION_USER));
	}
	/**
	* @Title: init
	* @Description: TODO(初始化bean函数 利用java反射机制)
	* @param @param servletcontextevent
	* @param @throws Exception    设定文件
	* @return void    返回类型
	* @author: 孟祥瑞
	* @date 2016年2月21日 下午2:36:08
	* @throws
	*/
	public static void init(ServletContextEvent servletcontextevent) throws Exception{
	    //加载系统配置文件
	    PropertiesUtil sysProperties = new PropertiesUtil("system.properties");
	    Class<?> baseconfigbeanClass = Class.forName("com.data.share.propertie.BaseConfigBean");
	    //创建实例
	    BaseConfigBean baseconfigbean = (BaseConfigBean)baseconfigbeanClass.newInstance();
	    Field[] fields = baseconfigbeanClass.getDeclaredFields();
	    //不进行实例化的字符
	    String[] noSetMethodStr = {"ROOT_WEB_SESSION_USER","ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE"};
	    List<String> noSetMethods = Arrays.asList(noSetMethodStr);
	    
	    //开始添加属性
	    for(Field field : fields){
	        String fieldName = field.getName();
	        if(!noSetMethods.contains(fieldName)){
	            String fieldTypeString = field.getType().toString();
	            String val = sysProperties.getValue(fieldName);
	            String setMethodName = "set" + WebUtil.firstWordToUpperCase(fieldName);
	               
	            // 获取方法
	            Method method = baseconfigbeanClass.getMethod(setMethodName, field.getType());
	            
	            //当为int类型时
	            if("int".equals(fieldTypeString)){
	                method.invoke(baseconfigbean,Integer.valueOf(val));
	            }else{
	                method.invoke(baseconfigbean,val);
	            }
	        }
	    }
	    
	    //将属性添加全局变量中
	    BaseConfigHelper.setBaseConfig(servletcontextevent.getServletContext(),baseconfigbean);
	}
}
