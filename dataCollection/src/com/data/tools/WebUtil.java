package com.data.tools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebUtil {
	/**
	 * @Title: toUTF8
	 * @Description: "ISO-8859-1"格式字符转换成"UTF-8"
	 * @param String
	 *            str "ISO-8859-1"格式字符
	 * @return "UTF-8"格式字符
	 * @author: 温泉
	 * @date 2012-05-18 10:20:38 +0800
	 * @throws
	 */
	public static String toUTF8(String str) {
		try {
			str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return str;
	}

	/**
	 * @Title: firstWordToUpperCase
	 * @Description: 将字符串首字母转换成大写字母的函数
	 * @param String
	 *            str 带转换字符串
	 * @return String 新字符串
	 * @author: 温泉
	 * @date 2012-03-07 14:54:35 +0800
	 * @throws
	 */
	public static String firstWordToUpperCase(String str) {
		Pattern expression = Pattern.compile("\\w+");
		Matcher matcher = expression.matcher(str);
		String result = "";
		while (matcher.find()) {
			result = matcher.group();
			String x = String.valueOf((char) (result.charAt(0) - 32));
			result = result.replaceFirst("[a-zA-Z]", x);
		}
		return result;
	}

	/**
	 * @Title: getClassPath
	 * @Description: 返回系统的classes根目录全路径
	 * @author 温泉
	 * @date 2012-2-23
	 * @return String 返回系统的classes根目录全路径
	 * @throws
	 */
	public static String getClassPath() {
		String path = "";
		Properties prop = System.getProperties();
		// 获取操作系统类型
		String os = prop.getProperty("os.name");
		// 代表是windows系统
		if (os.startsWith("win") || os.startsWith("Win")) {
			path = WebUtil.class.getResource("/").getPath().toString()
					.substring(1);
		} else {
			path = WebUtil.class.getResource("/").getPath().toString();
		}
		prop = null;
		return path;
	}

	/**
	 * @Title: getFileExtName
	 * @Description: 获取文件后缀名
	 * @author 温泉 quan.wen@soooqooo.com
	 * @date 2012-2-28
	 * @param String
	 *            fileName 文件名称
	 * @return String 获取文件后缀名
	 * @throws
	 */
	public static String getFileExtName(String fileName) {
		String extName = "";
		if (fileName.lastIndexOf(".") >= 0) {
			extName = fileName.substring(fileName.lastIndexOf("."));
		}
		return extName;
	}

	private static long[] ls = new long[3000];
	private static int li = 0;

	/**
	 * @Title: getPK
	 * @Description: 主键生成方法，生成Long型的数字主键
	 * @return long 返回long 主键内容
	 * @author: 温泉
	 * @date 2012-08-02 09:56:37 +0800
	 * @throws
	 */
	public synchronized static long getPK() {
		/*
		 * 2012-10-18 苏沫予： 修改主键生成算法，使主键在软件生命周期内具有时间连续性同时适应JS
		 * 前端应用，根据当前算法，至少17年内不会发生2^53溢出问题 - 关于性能问题：
		 * 新主键方案每10毫秒内有十万分之一的可能性会发生冲突主键问题，因此
		 * 当系统每秒数据生成量达到100条时生成器实际性能大约下降0.003%，此
		 * 后呈线性下降，每秒数据生成量150000条时，主键生成性能大约下降一倍， 每秒生成数据超过300000条后，该主键生成算法将不再安全 -
		 * 关于并发问题： 该算法并发运行时（例如分布式服务器系统）存在每秒千万分之一的冲突
		 * 可能性，因此建议不用于并发式系统，即便投入应用，也应当保证每秒并 发插入数据不超过1000条。
		 */
		String a = String
				.valueOf((System.currentTimeMillis() / 10L) % 1000000L);
		String d = (String.valueOf((1 + Math.random()) * 100000)).substring(1,
				4);
		// 苏沫予：添加代码结束（同时移除韩欣宇的代码方案）
		long lo = Long.parseLong(a + d);
		for (int i = 0; i < 3000; i++) {
			long lt = ls[i];
			if (lt == lo) {
				lo = getPK();
				break;
			}
		}
		ls[li] = lo;
		li++;
		if (li == 3000) {
			li = 0;
		}
		return lo;
	}

	/**
	 * @Title: getRanName
	 * @Description: 根据时间生成一个随机数
	 * @author: 温泉 quan.wen@soooqooo.com
	 * @date: 2012-03-01 20:51:19 +0800
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public synchronized static String getRanName() {
		return String.valueOf(getPK());
	}

	/**
	 * @Title: addParamToUrl
	 * @Description: 向url后面添加一个参数
	 * @param @param url 原url
	 * @param @param key 参数名
	 * @param @param value 参数值
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author: 韩欣宇
	 * @date 2013-10-10 16:57:25 +0800
	 * @throws
	 */
	public static String addParamToUrl(String url, String key, String value) {
		if (url.contains("?")) {
			String after = url
					.substring(url.lastIndexOf("?") + 1, url.length());
			if ("".equals(after) || after.endsWith("&")) {
				url = url + key + "=" + value;
			} else if (after.contains("&")) {
				url = url + "&" + key + "=" + value;
			} else {
				url = url + "?" + key + "=" + value;
			}
		} else {
			url = url + "?" + key + "=" + value;
		}
		return url;
	}

	/**
	 * @Title: getHttpConnectResponseWithGet
	 * @Description: 用get的方式提交一个url并获取到返回值
	 * @param @param data
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @author: 韩欣宇
	 * @date 2013-10-11 17:17:08 +0800
	 * @throws
	 */
	public static String getHttpConnectResponseWithGet(String myUrl)
			throws Exception {
		return getHttpConnectResponseWithGet(myUrl, "UTF-8");
	}

	/**
	 * @Title: getHttpConnectResponseWithGet
	 * @Description: 用get的方式提交一个url并获取到返回值
	 * @param @param data
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @author: 韩欣宇
	 * @date 2013-10-11 17:17:08 +0800
	 * @throws
	 */
	public static String getHttpConnectResponseWithGet(String myUrl,
			String encoding) throws Exception {
		URL url = new URL(myUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// conn.setConnectTimeout(100000);
		// conn.setReadTimeout(300000);
		conn.setRequestMethod("GET");// 提交模式
		InputStream inStream = conn.getInputStream();
		String result = new String(readInputStream(inStream), encoding);
		inStream.close();
		conn.disconnect();
		return result;
	}

	/**
	 * @throws IOException
	 * @Title: readInputStream
	 * @Description: 从InputStream读取数据
	 * @param @param inStream
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return byte[] 返回类型
	 * @author: 韩欣宇
	 * @date 2013-10-11 17:20:48 +0800
	 * @throws
	 */
	public static byte[] readInputStream(InputStream inStream)
			throws IOException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();// 网页的二进制数据
		outStream.close();
		inStream.close();
		return data;
	}

	/**
	 * @Title: URLEncode
	 * @Description: 统一的url编码方法
	 * @param @param url
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author: 韩欣宇
	 * @date 2014-11-18 15:48:18 +0800
	 * @throws
	 */
	@SuppressWarnings("deprecation")
	public static String URLEncode(String url) {
		return java.net.URLEncoder.encode(url).replaceAll("\\+", "%20");
	}

	/**
	 * @Title: URLDecode
	 * @Description: 统一的url解码方法
	 * @param @param url
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author: 韩欣宇
	 * @date 2014-11-18 15:48:33 +0800
	 * @throws
	 */
	@SuppressWarnings("deprecation")
	public static String URLDecode(String url) {
		return java.net.URLDecoder.decode(url);
	}

	public static void main(String[] args) {
		/*
		 * String s = "@#$%^&*(A  *^*&O汉字YO"; System.out.println(s); String ss =
		 * WebUtil.URLEncode(s); System.out.println(ss); String sss =
		 * WebUtil.URLDecode(ss); System.out.println(sss);
		 * System.out.println(sss.equals(sss));
		 */
		System.out.println(Integer.MAX_VALUE);
		System.out.println(WebUtil.getPK());
	}
}
