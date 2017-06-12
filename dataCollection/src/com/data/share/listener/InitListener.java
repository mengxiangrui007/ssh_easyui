package com.data.share.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.data.share.propertie.BaseConfigHelper;

/**
* @ClassName: InitListener
* @Description: TODO(系统初始化侦听器用来加载默认配置等信息)
* @author: 孟祥瑞
* @company: 赤峰宏微网络科技有限公司
* @date 2016年2月21日 下午2:33:22
*/
public class InitListener implements ServletContextListener {
    /**
     * @Fields logger : log4j句柄
     */
    protected Logger logger = Logger.getLogger(getClass());
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        logger.info("系统正在关闭……");
    }	
    @Override
    public void contextInitialized(ServletContextEvent servletcontextevent) {

        // 加载系统配置文件
        try {
            logger.info("系统正在加载配置信息……");
            System.out.println("系统正在加载配置信息……");
            // 加载系统配置
            BaseConfigHelper.init(servletcontextevent);
        } catch (Exception e) {
            logger.error("加载系统时出错。异常如下:/n" + e.getMessage());
            e.printStackTrace();
        }
    }
}
