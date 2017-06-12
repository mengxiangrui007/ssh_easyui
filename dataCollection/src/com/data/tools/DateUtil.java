package com.data.tools;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: DateUtil
 * @Description: 时间工具类
 * @author 孟祥瑞
 * @date 2012-2-22
 */

public class DateUtil {

	/** 缺省日期格式 */
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	/** 缺省时间格式 */
	public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

	/** 缺省月格式 */
	public static final String DEFAULT_MONTH = "MONTH";

	/** 缺省年格式 */
	public static final String DEFAULT_YEAR = "YEAR";

	/** 缺省日格式 */
	public static final String DEFAULT_DATE = "DAY";

	/** 缺省小时格式 */
	public static final String DEFAULT_HOUR = "HOUR";

	/** 缺省分钟格式 */
	public static final String DEFAULT_MINUTE = "MINUTE";

	/** 缺省秒格式 */
	public static final String DEFAULT_SECOND = "SECOND";

	/** 缺省长日期格式 */
	public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH-mm";

	/** 缺省长日期格式,精确到秒 */
	public static final String DEFAULT_DATETIME_FORMAT_SEC = "yyyy-MM-dd HH:mm:ss";

	/** 缺省长日期格式,精确到毫秒 */
	public static final String DEFAULT_DATETIME_FORMAT_MILL ="yyyy-MM-dd HH:mm:ss.S";
	/** 星期数组 */
	public static final String[] WEEKS = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

	
	/**
	* @Fields DAY_TO_MILLIS : 一天对应的毫秒数
	*/
	public static final long DAY_TO_MILLIS = 24 * 60 * 60 * 1000;

	
	/**
	* @Title: getTimestamp
	* @Description: TODO(获取当前时间的Timestamp)
	* @param @return    设定文件
	* @return Timestamp    返回类型
	* @author: 孟祥瑞
	* @date 2016年2月23日 下午4:51:47
	* @throws
	*/
	public static Timestamp getTimestamp(){
		return  new Timestamp(System.currentTimeMillis());
	}
	
	/**
	 * 取当前日期的字符串表示
	 * 
	 * @return 当前日期的字符串 ,如2010-05-28
	 **/
	public static String today() {
		return today(DEFAULT_DATE_FORMAT);
	}

	/**
	 * 根据输入的格式得到当前日期的字符串
	 * 
	 * @param strFormat 日期格式
	 * @return
	 */
	public static String today(String strFormat) {
		return toString(new Date(), strFormat);
	}

	/**
	 * 取当前时间的字符串表示,
	 * @return 当前时间,如:21:10:12
	 **/
	public static String currentTime() {
		return currentTime(DEFAULT_TIME_FORMAT);
	}

	/**
	 * 根据输入的格式获取时间的字符串表示
	 * 
	 * @param 输出格式 ,如'hh:mm:ss'
	 * @return 当前时间,如:21:10:12
	 **/

	public static String currentTime(String strFormat) {
		return toString(new Date(), strFormat);
	}

	/**
	 * @Title: getTime
	 * @Description: 根据时间格式和时间字符串转换成时间戳
	 * @param String time
	 * @param String strFormat
	 * @return long 时间戳
	 * @author: 温泉
	 * @date 2012-04-19 17:29:06 +0800
	 * @throws
	 */
	public static long getTime(String time, String strFormat) {
		long result = 0;
		if (!StringUtils.isEmpty(time)) {
			SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
			Date d;
			try {
				d = sdf.parse(time);
				result = d.getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return result;
	};

	/**
	 * @Title: getStrTime
	 * @Description: 讲时间戳转换成字符串
	 * @param long datetime
	 * @param String format
	 * @return String 时间格式字符串
	 * @author: 温泉
	 * @date 2012-04-19 17:37:28 +0800
	 */
	public static String getStrTime(long datetime, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(datetime));
	};

	/**
	 * 取得相对于当前时间增加天数/月数/年数后的日期 <br>
	 * 欲取得当前日期5天前的日期,可做如下调用:<br>
	 * getAddDay("DATE", -5).
	 * @param field 段,如"year","month","date",对大小写不敏感
	 * @param amount  增加的数量(减少用负数表示),如5,-1
	 * @return 格式化后的字符串 如"2010-05-28"
	 * @throws ParseException
	 **/

	public static String getAddDay(String field, int amount) throws ParseException {
		return getAddDay(field, amount, null);
	}

	/**
	 * 取得相对于当前时间增加天数/月数/年数后的日期,按指定格式输出
	 * 
	 * 欲取得当前日期5天前的日期,可做如下调用:<br>
	 * getAddDay("DATE", -5,'yyyy-mm-dd hh:mm').
	 * 
	 * @param field 段,如"year","month","date",对大小写不敏感
	 * @param amount 增加的数量(减少用负数表示),如5,-1
	 * @param strFormat 输出格式,如"yyyy-mm-dd","yyyy-mm-dd hh:mm"
	 * @return 格式化后的字符串 如"2010-05-28"
	 * @throws ParseException
	 **/
	public static String getAddDay(String field, int amount, String strFormat) throws ParseException {
		return getAddDay(null, field, amount, strFormat);
	}

	/**
	 * 功能：对于给定的时间增加天数/月数/年数后的日期,按指定格式输出
	 * 
	 * @param date
	 *            String 要改变的日期
	 * @param field
	 *            int 日期改变的字段，YEAR,MONTH,DAY
	 * @param amount
	 *            int 改变量
	 * @param strFormat
	 *            日期返回格式
	 * @return
	 * @throws ParseException
	 */
	public static String getAddDay(String date, String field, int amount, String strFormat) throws ParseException {
		if (strFormat == null) {
			strFormat = DEFAULT_DATETIME_FORMAT_SEC;
		}
		Calendar rightNow = Calendar.getInstance();
		if (date != null && !"".equals(date.trim())) {
			rightNow.setTime(parseDate(date, strFormat));
		}
		if (field == null) {
			return toString(rightNow.getTime(), strFormat);
		}
		rightNow.add(getInterval(field), amount);
		return toString(rightNow.getTime(), strFormat);
	}

	/**
	 * 获取时间间隔类型
	 * 
	 * @param field
	 *            时间间隔类型
	 * @return 日历的时间间隔
	 */
	protected static int getInterval(String field) {
		String tmpField = field.toUpperCase();
		if (tmpField.equals(DEFAULT_YEAR)) {
			return Calendar.YEAR;
		} else if (tmpField.equals(DEFAULT_MONTH)) {
			return Calendar.MONTH;
		} else if (tmpField.equals(DEFAULT_DATE)) {
			return Calendar.DATE;
		} else if (DEFAULT_HOUR.equals(tmpField)) {
			return Calendar.HOUR;
		} else if (DEFAULT_MINUTE.equals(tmpField)) {
			return Calendar.MINUTE;
		} else {
			return Calendar.SECOND;
		}
	}

	/**
	 * 获取格式化对象
	 * 
	 * @param strFormat
	 *            格式化的格式 如"yyyy-MM-dd"
	 * @return 格式化对象
	 */
	public static SimpleDateFormat getSimpleDateFormat(String strFormat) {
		if (strFormat != null && !"".equals(strFormat.trim())) {
			return new SimpleDateFormat(strFormat);
		} else {
			return new SimpleDateFormat();
		}
	}

	/**
	 * 得到当前日期的星期数
	 * 
	 * @return 当前日期的星期的字符串
	 * @throws ParseException
	 */
	public static String getWeekOfMonth() throws ParseException {
		return getWeekOfMonth(null, null);
	}

	/**
	 * 根据日期的到给定日期的在当月中的星期数
	 * 
	 * @param date
	 *            给定日期
	 * @return
	 * @throws ParseException
	 */
	public static String getWeekOfMonth(String date, String fromat) throws ParseException {
		Calendar rightNow = Calendar.getInstance();
		if (date != null && !"".equals(date.trim())) {
			rightNow.setTime(parseDate(date, fromat));
		}
		return WEEKS[rightNow.get(Calendar.WEEK_OF_MONTH)];
	}

	/**
	 * 将java.util.date型按照指定格式转为字符串
	 * 
	 * @param date
	 *            源对象
	 * @param format
	 *            想得到的格式字符串
	 * @return 如：2010-05-28
	 */
	public static String toString(Date date, String format) {
		return getSimpleDateFormat(format).format(date);
	}

	/**
	 * 将java.util.date型按照缺省格式转为字符串
	 * 
	 * @param date
	 *            源对象
	 * @return 如：2010-05-28
	 */
	public static String toString(Date date) {
		return toString(date, DEFAULT_DATE_FORMAT);
	}

	/**
	 * 强制类型转换 从串到日期
	 * 
	 * @param sDate
	 *            源字符串，采用yyyy-MM-dd格式
	 * @param sFormat
	 *            ps
	 * @return 得到的日期对象
	 * @throws ParseException
	 */
	public static Date parseDate(String strDate, String format) throws ParseException {
		return getSimpleDateFormat(format).parse(strDate);
	}

	/***
	 * 根据传入的毫秒数和格式，对日期进行格式化输出
	 * 
	 * @version 2011-7-12
	 * @param object
	 * @param format
	 * @return
	 */
	public static String millisecondFormat(Long millisecond, String format) {
		if (millisecond == null || millisecond <= 0) {
			throw new IllegalArgumentException(String.format("传入的时间毫秒数[%s]不合法", "" + millisecond));
		}
		if (format == null || "".equals(format.trim())) {
			format = DEFAULT_DATE_FORMAT;
		}
		return toString(new Date(millisecond), format);
	}

	/**
	 * 强制类型转换 从串到时间戳
	 * 
	 * @param sDate
	 *            源串
	 * @param sFormat
	 *            遵循格式
	 * @return 取得的时间戳对象
	 * @throws ParseException
	 */
	public static Timestamp parseTimestamp(String strDate, String format) throws ParseException {
		Date utildate = getSimpleDateFormat(format).parse(strDate);
		return new Timestamp(utildate.getTime());
	}

	/**
	 * getCurDate 取当前日期
	 * 
	 * @return java.util.Date型日期
	 **/
	public static Date getCurDate() {
		return (new Date());
	}

	/**
	 * getCurTimestamp 取当前时间戳
	 * 
	 * @return java.sql.Timestamp
	 **/
	public static Timestamp getCurTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	/**
	 * getCurTimestamp 取遵循格式的当前时间
	 * 
	 * @param sFormat
	 *            遵循格式
	 * @return java.sql.Timestamp
	 **/
	public static Date getCurDate(String format) throws Exception {
		return getSimpleDateFormat(format).parse(toString(new Date(), format));
	}

	/**
	 * Timestamp按照指定格式转为字符串
	 * 
	 * @param timestamp
	 *            源对象
	 * @param format
	 *            ps（如yyyy.mm.dd）
	 * @return 如：2010-05-28 或2010-05-281 13:21
	 */
	public static String toString(Timestamp timestamp, String format) {
		if (timestamp == null) {
			return "";
		}
		return toString(new Date(timestamp.getTime()), format);
	}

	/**
	 * Timestamp按照缺省格式转为字符串
	 * 
	 * @param ts
	 *            源对象
	 * @return 如：2010-05-28
	 */
	public static String toString(Timestamp ts) {
		return toString(ts, DEFAULT_DATE_FORMAT);
	}

	/**
	 * Timestamp按照缺省格式转为字符串，可指定是否使用长格式
	 * 
	 * @param timestamp
	 *            欲转化之变量Timestamp
	 * @param fullFormat
	 *            是否使用长格式
	 * @return 如：2010-05-28 或2010-05-28 21:21
	 */
	public static String toString(Timestamp timestamp, boolean fullFormat) {
		if (fullFormat) {
			return toString(timestamp, DEFAULT_DATETIME_FORMAT_SEC);
		} else {
			return toString(timestamp, DEFAULT_DATE_FORMAT);
		}
	}

	/**
	 * 将sqldate型按照指定格式转为字符串
	 * 
	 * @param sqldate
	 *            源对象
	 * @param sFormat
	 *            ps
	 * @return 如：2010-05-28 或2010-05-28 00:00
	 */
	public static String toString(java.sql.Date sqldate, String sFormat) {
		if (sqldate == null) {
			return "";
		}
		return toString(new Date(sqldate.getTime()), sFormat);
	}

	/**
	 * 将sqldate型按照缺省格式转为字符串
	 * 
	 * @param sqldate
	 *            源对象
	 * @return 如：2010-05-28
	 */
	public static String toString(java.sql.Date sqldate) {
		return toString(sqldate, DEFAULT_DATE_FORMAT);
	}

	/**
	 * 计算日期时间之间的差值， date1得时间必须大于date2的时间
	 * 
	 * @version 2011-7-12
	 * @param date1
	 * @param date2
	 * @return {@link java.util.Map} Map的键分别为, day(天),
	 *         hour(小时),minute(分钟)和second(秒)。
	 */
	public static Map<String, Long> timeDifference(final Date date1, final Date date2) {
		if (date1 == null || date2 == null) {
			throw new NullPointerException("date1 and date2 can't null");
		}
		long mim1 = date1.getTime();
		long mim2 = date2.getTime();
		if (mim1 < mim2) {
			throw new IllegalArgumentException(String.format("date1[%s] not be less than date2[%s].", mim1 + "", mim2
					+ ""));
		}
		long m = (mim1 - mim2 + 1) / 1000l;
		long mday = 24 * 3600;
		final Map<String, Long> map = new HashMap<String, Long>();
		map.put("day", m / mday);
		m = m % mday;
		map.put("hour", (m) / 3600);
		map.put("minute", (m % 3600) / 60);
		map.put("second", (m % 3600 % 60));
		return map;
	}

	public static Map<String, Integer> compareTo(final Date date1, final Date date2) {
		if (date1 == null || date2 == null) {
			return null;
		}
		long time1 = date1.getTime();
		long time2 = date2.getTime();
		long time = Math.max(time1, time2) - Math.min(time1, time2);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("year", (calendar.get(Calendar.YEAR) - 1970) > 0 ? (calendar.get(Calendar.YEAR) - 1970) : 0);
		map.put("month", (calendar.get(Calendar.MONTH) - 1) > 0 ? (calendar.get(Calendar.MONTH) - 1) : 0);
		map.put("day", (calendar.get(Calendar.DAY_OF_MONTH) - 1) > 0 ? (calendar.get(Calendar.DAY_OF_MONTH) - 1) : 0);
		map.put("hour", (calendar.get(Calendar.HOUR_OF_DAY) - 8) > 0 ? (calendar.get(Calendar.HOUR_OF_DAY) - 8) : 0);
		map.put("minute", calendar.get(Calendar.MINUTE) > 0 ? calendar.get(Calendar.MINUTE) : 0);
		map.put("second", calendar.get(Calendar.SECOND) > 0 ? calendar.get(Calendar.SECOND) : 0);
		return map;
	}
	
	public static String getLastDayOfMonth(String year, String month) {
	    Calendar cal = Calendar.getInstance();
	    //年
	    cal.set(Calendar.YEAR, Integer.parseInt(year));
	    //月，因为Calendar里的月是从0开始，所以要-1
	    cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
	    //日，设为一号
	    cal.set(Calendar.DATE, 1);
	    //月份加一，得到下个月的一号
	    cal.add(Calendar.MONTH,1);
	    //下一个月减一为本月最后一天
	    cal.add(Calendar.DATE, -1);
	    return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));//获得月末是几号
	 }

    /**
     * @Title: toXmlDateTime
     * @Description: java
     *               的日期字符串描述：http://docs.oracle.com/javase/tutorial/i18n/format
     *               /simpleDateFormat.html<br />
     *               XML的日期字符串描述：http://www.w3school.com.cn/schema/
     *               schema_dtypes_date.asp<br />
     *               由于java和xml的时区无法兼容，只能手动写上'+08:00'
     * @param @param d
     * @param @return 设定文件
     * @return String 返回类型
     * @author: 韩欣宇
     * @date 2014-04-06 17:51:32 +0800
     * @throws
     */
    public static String toXmlDateTime(Date d) {
        // 2014-03-24T07:43:18Z
        return toString(d, "yyyy-MM-dd'T'HH:mm:ss'+08:00'");
    }
    
    /**
    * @Title: getTime
    * @Description: TODO(获取系统当前时间)
    * @param @param format
    * @param @return    设定文件
    * @return String    返回类型
    * @author: 阮启伟
    * @date 2016年2月15日 下午5:36:46
    * @throws
    */
    public static String getTime(String format){
    	SimpleDateFormat dateFormat = new SimpleDateFormat(format);
    	return dateFormat.format(new Date());
    }
    
    /**
    * @Title: addDate
    * @Description: TODO(时间相加 日期类)
    * @param @param day
    * @param @param x
    * @param @return    设定文件
    * @return String    返回类型
    * @author: 阮启伟
    * @date 2016年2月15日 下午5:45:40
    * @throws
    */
    public static String addDate(String day, int x) 
    { 
        SimpleDateFormat format = new SimpleDateFormat(DateUtil.DEFAULT_DATETIME_FORMAT_SEC);//24小时制 
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//12小时制 
        Date date = null; 
        try 
        { 
            date = format.parse(day); 
        } 
        catch (Exception ex)    
        { 
            ex.printStackTrace(); 
        } 
        if (date == null) return ""; 
        Calendar cal = Calendar.getInstance(); 
        cal.setTime(date); 
        cal.add(Calendar.SECOND, x);//24小时制 
        //cal.add(Calendar.HOUR, x);12小时制 
        date = cal.getTime(); 
        cal = null; 
        return format.format(date); 
    }
    
    public static Date addDate(Date date,int s){
    	// SimpleDateFormat format = new SimpleDateFormat(DateUtil.DEFAULT_DATETIME_FORMAT_SEC);//24小时制 
          Calendar cal = Calendar.getInstance(); 
          cal.setTime(date); 
          cal.add(Calendar.SECOND, s);//24小时制 
          //cal.add(Calendar.HOUR, x);12小时制 
          date = cal.getTime(); 
          cal = null; 
          return date; 
    }
    
    public static boolean twoTimeCompare(String timeOne,String timeTwo){
    	SimpleDateFormat df = new SimpleDateFormat(DateUtil.DEFAULT_DATETIME_FORMAT_SEC);
    	//获取Calendar实例
    	Calendar currentTime = Calendar.getInstance();
    	Calendar compareTime = Calendar.getInstance();
    	try {
    		//把字符串转成日期类型
    		currentTime.setTime(df.parse(timeOne));
    		compareTime.setTime(df.parse(timeTwo));
    		} catch (ParseException e) {
    		e.printStackTrace();
    		}
    		//利用Calendar的方法比较大小
    		if (currentTime.compareTo(compareTime) > 0) {
    			//前者时间小
    		    return false;
    		}else{
    		    return true;
    		}
    }
}
