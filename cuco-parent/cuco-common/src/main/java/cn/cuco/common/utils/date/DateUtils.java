package cn.cuco.common.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * description：日期时间工具类 author：WangShuai time：2017年02月20 13:35:00 修改时间：2017年02月20
 * 13:35:00 修改备注：
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss",
			"yyyy.MM.dd HH:mm", "yyyy.MM" };

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * 得到当前小时符串 格式（HH）小时
	 */
	public static String getHour() {
		return formatDate(new Date(), "HH");
	}

	public static String getMinutes(Date date) {
		return formatDate(date, "mm");
	}

	/**
	 * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd",
	 * "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * 
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (24 * 60 * 60 * 1000);
	}

	/**
	 * 获取过去的小时
	 * 
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 60 * 1000);
	}

	/**
	 * 获取过去的分钟
	 * 
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 1000);
	}

	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * 
	 * @param timeMillis
	 * @return
	 */
	public static String formatDateTime(long timeMillis) {
		long day = timeMillis / (24 * 60 * 60 * 1000);
		long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
		long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
		return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
	}

	/**
	 * 获取两个日期之间的天数
	 *
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static Date now() {
		return new Date();
	}

	/**
	 * 根据年月获取 当月最大的天数 @Title: maxDay @Description: TODO @author
	 * q.p.x @param @param year @param @param month @param @return @return
	 * int @date 2016年3月22日 下午1:44:44 @throws
	 */
	public static int getMaxDay(int year, int month) {

		int maxDay = 0;
		int day = 1;
		/**
		 * 与其他语言环境敏感类一样，Calendar 提供了一个类方法 getInstance， 以获得此类型的一个通用的对象。Calendar 的
		 * getInstance 方法返回一 个 Calendar 对象，其日历字段已由当前日期和时间初始化：
		 */
		Calendar calendar = Calendar.getInstance();
		/**
		 * 实例化日历各个字段,这里的day为实例化使用
		 */
		calendar.set(year, month - 1, day);
		/**
		 * Calendar.Date:表示一个月中的某天 calendar.getActualMaximum(int
		 * field):返回指定日历字段可能拥有的最大值
		 */
		maxDay = calendar.getActualMaximum(Calendar.DATE);
		return maxDay;

	}

	/**
	 * 获取本月最小的日期 例：2016-8-01 00:00:00 flag true设置date天为最小， false不设置天
	 * 
	 * @Description:TODO
	 * @author:JIAZHIPENG
	 * @time:2016-9-13 上午9:52:26
	 * @return String
	 */
	public static Calendar getMiniDate(String dateStr, boolean flag) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(dateStr);
		// 设置时间,当前时间不用设置
		calendar.setTime(date);
		if (flag) {
			// 设置日期为本月最小日期
			calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		}
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
		calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
		return calendar;
	}

	/**
	 * 获取本月最大的日期 例：2016-8-31 23:59:59 flag true设置date天为最大， false不设置天
	 * 
	 * @Description:TODO
	 * @author:JIAZHIPENG
	 * @time:2016-9-13 上午9:52:26
	 * @return String
	 */
	public static Calendar getMaxDate(String dateStr, boolean flag) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(dateStr);
		// 设置时间,当前时间不用设置
		calendar.setTime(date);
		if (flag) {
			// 设置日期为本月最大日期
			calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		}
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
		return calendar;
	}

	/**
	 * 获取某年第一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getYearFirst(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

	/**
	 * 获取某年最后一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getYearLast(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();

		return currYearLast;
	}

	/**
	 * 获取当日最小的日期 例：2016-8-01 00:00:00
	 * 
	 * @Description:TODO
	 * @time:2016-9-13 上午9:52:26
	 * @return String
	 */
	public static Date getDayMiniDate(Date date) {
		Calendar c1 = new GregorianCalendar();
		c1.setTime(date);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		return c1.getTime();
	}

	/**
	 * 获取某月的第一天 如，2016-11-01 00:00:00
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstOfMonth(Date date) {
		RelyParamVerify.paramNotNull(date);
		return DateUtils.parseDate(DateUtils.formatDate(date, "yyyy-MM"));
	}

	/**
	 * 获取两个日期相差秒数
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static Long getDiffSecondsOfTwoDate(Date startTime, Date endTime) {
		return (endTime.getTime() - startTime.getTime()) / 1000;
	}

	public static String formatDateTime(long timeMillis, String dayStr, String hoursStr, String minStr, String sStr) {
		long day = timeMillis / (24 * 60 * 60 * 1000);
		long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
		long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
		return (day > 0 ? day + dayStr : "") + hour + hoursStr + min + minStr + s + sStr;
	}

	/**
	 * 获取某月的最后一天 如，2016-11-30 00:00:00
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastOfMonth(Date date) {
		RelyParamVerify.paramNotNull(date);
		return DateUtils.addDays(DateUtils.addMonths(DateUtils.getFirstOfMonth(date), 1), -1);
	}

	/**
	 *
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date addMonth2Date(Date date, Integer months) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}

	/**
	 * 获取当日最大的日期 例：2016-8-31 23:59:59
	 * 
	 * @Description:TODO
	 * @time:2016-9-13 上午9:52:26
	 * @return String
	 */
	public static Date getDayMaxDate(Date date) {
		Calendar c2 = new GregorianCalendar();
		c2.setTime(date);
		c2.set(Calendar.HOUR_OF_DAY, 23);
		c2.set(Calendar.MINUTE, 59);
		c2.set(Calendar.SECOND, 59);
		return c2.getTime();
	}

	/**
	 * 获取时间段内周数
	 * 
	 * @Title: getCountTwoDayWeek
	 * @Description: TODO
	 * @author: wangchuntao
	 * @param startDate
	 * @param endDate
	 * @return Object
	 * @throws ParseException
	 * @date: 2017年3月1日 下午12:28:21
	 */
	public static int getCountTwoDayWeek(Date startDate, Date endDate) throws Exception {

		Date start = getMondayByDate(startDate);
		Date end = getSundayByDate(endDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(start);
		long time1 = cal.getTimeInMillis();
		cal.setTime(end);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		Double days = Double.parseDouble(String.valueOf(between_days));
		if ((days / 7) > 0 && (days / 7) <= 1) { // 不满一周的按一周算
			return 1;
		} else if (days / 7 > 1) {
			int day = days.intValue();
			if (day % 7 > 0) {
				return day / 7 + 1;
			} else {
				return day / 7;
			}
		} else if ((days / 7) == 0) {
			return 0;
		}
		return 0;
	}

	/**
	 * 获取时间段内月数
	 * 
	 * @Title: getCountTwoDayMonth
	 * @Description: TODO
	 * @author: wangchuntao
	 * @param startDate
	 * @param endDate
	 * @throws ParseException
	 * @return int
	 * @date: 2017年3月1日 下午12:29:26
	 */
	public static int getCountTwoDayMonth(Date startDate, Date endDate) throws ParseException {
		int result = 0;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(startDate);
		c2.setTime(endDate);

		result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

		return result == 0 ? 1 : Math.abs(result)+1;

	}

	/**
	 * @Title: getSundayByDate
	 * @Description: 取周日
	 * @author huanghua
	 * @param date
	 * @throws ParseException
	 * @return void
	 */
	public static Date getSundayByDate(Date date) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		// Date date=sdf.parse("2015-9-4 14:22:47");
		cal.setTime(date);
//		System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期

		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, 6);
//		System.out.println("所在周星期日的日期：" + sdf.format(cal.getTime()));
		return cal.getTime();
	}

	/**
	 * @Title: getMondayByDate
	 * @Description: 取周一
	 * @author huanghua
	 * @param date
	 * @return
	 * @throws ParseException
	 * @return Date
	 */
	public static Date getMondayByDate(Date date) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		// Date date=sdf.parse("2015-9-4 14:22:47");
		cal.setTime(date);
//		System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		return cal.getTime();
	}

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
	}

}
