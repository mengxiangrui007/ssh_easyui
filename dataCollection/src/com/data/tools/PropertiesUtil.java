package com.data.tools;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
/**
* @ClassName: PropertiesUtil
* @Description: Properties文件操作类
* @company:上海苏秦科技
* @author 温泉
* @date 2012-2-23
*/ 
public class PropertiesUtil {
	
	private Properties propertie;
	//private FileInputStream inputFile;
	private FileOutputStream outputFile;
	private String fileName;
	
	 protected Logger logger = Logger.getLogger(getClass());  
	/**
	* <p>Title: 构造函数 </p>
	* <p>Description: 构造函数</p>
	*/ 
	public PropertiesUtil() {
	   propertie = new Properties();
	}

	public Properties getPropertie() {
        return propertie;
    }

    /**
	* <p>Title: 构造函数</p>
	* <p>Description: <构造函数/p>
	* @param String filePath 文件路径
	* @throws Exception
	*/ 
	public PropertiesUtil(String filePath) throws Exception {
		propertie = new Properties();
		load(filePath);
	}
	
	
	/**
	* @Title: load
	* @Description: 读取属性文件的函数
	* @author 温泉
	* @date 2012-2-23
	* @param     String 文件路径
	* @return void    返回类型
	* @throws
	*/ 
	private void load(String filePath) throws Exception{
	    InputStream inStream = WebUtil.class.getResourceAsStream("/"  +  filePath);   
        propertie.load(inStream);
        inStream.close();
	}
	
	/**
	* @Title: load
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @throws Exception    设定文件
	* @return void    返回类型
	* @author: 温泉
	* @date 2012-03-20 09:37:34 +0800
	* @throws
	*/
	public void load() throws Exception{
        InputStream inStream = WebUtil.class.getResourceAsStream("/"  +  fileName);   
        propertie.load(inStream);
        inStream.close();
    }
	
	
	/**
	* @Title: getValue
	* @Description: 通过key获取属性文件的值
	* @author 温泉
	* @date 2012-2-23
	* @param String key
	* @return String    返回类型
	* @throws
	*/ 
	public String getValue(String key) {
		if (propertie.containsKey(key)) {
			String value = propertie.getProperty(key);// 得到某一属性的值
			return value;
		}else{
			return "";
		}
	}

	
	/**
	* @Title: getValue
	* @Description: 重载函数，得到key的值
	* @author 温泉
	* @date 2012-2-23
	* @param String filePath 文件路径名
	* @param String key   属性key名称
	* @return String   对应的值
	* @throws
	*/ 
	public String getValue(String filePath, String key) {
		try {
			String value = "";
			load(filePath);//加载属性文件
			if (propertie.containsKey(key)) {
				value = propertie.getProperty(key);
				return value;
			}else{
				return value;
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
			return "";
		}catch (IOException e) {
			e.printStackTrace();
			return "";
		}catch (Exception ex) {
			ex.printStackTrace();
			return "";
	   }
	}

	
	/**
	* @Title: clear
	* @Description: 清除properties文件中所有的key和其值
	* @author 温泉
	* @date 2012-2-23
	* @return void   
	* @throws
	*/ 
	public void clear() {
		propertie.clear();
	}

	
	/**
	* @Title: setValue
	* @Description: 根据key开始赋值
	* @author 温泉
	* @date 2012-2-23
	* @param String key
	* @param String value
	* @return void  
	* @throws
	*/ 
	public void setValue(String key, String value) {
		propertie.setProperty(key, value);
	}

	
	/**
	* @Title: saveFile
	* @Description: 将更改后的文件数据存入指定的文件中，该文件可以事先不存在。
	* @author 温泉
	* @date 2012-2-23
	* @param @param fileName
	* @param @param description 描述
	* @param @throws Exception    设定文件
	* @return void 
	* @throws
	*/ 
	public void saveFile(String fileName, String description) throws Exception {
		outputFile = new FileOutputStream(WebUtil.getClassPath() +  fileName);
		propertie.store(outputFile, description);
		outputFile.close();
	}


    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }


    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
	
	
	
}
