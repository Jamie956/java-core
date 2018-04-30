package com.jarvis.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {
	public static final String pattern_date = "yyyy-MM-dd";
	public static final String pattern_time = "yyyy-MM-dd HH:mm:ss";
	
	public static String formatDate(Date date) {
		return formatDate(date, pattern_time);
	}

	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}

	public static Date parseString(String dateStr) {
		return parseString(dateStr, "yyyy-MM-dd HH:mm:ss");
	}

	public static Date parseString(String dateStr, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			if (!StringHelper.isEmpty(dateStr)) {
				return sdf.parse(dateStr);
			}
		} catch (ParseException ex) {
			ex.printStackTrace();
			System.err.println(dateStr + "转换成日期失败，可能是不符合格式：" + pattern);
		}
		return null;
	}

	public static String getWeekStr(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int week = calendar.get(7);
		--week;
		String weekStr = "";
		switch (week) {
		case 0:
			weekStr = "星期日";
			break;
		case 1:
			weekStr = "星期一";
			break;
		case 2:
			weekStr = "星期二";
			break;
		case 3:
			weekStr = "星期三";
			break;
		case 4:
			weekStr = "星期四";
			break;
		case 5:
			weekStr = "星期五";
			break;
		case 6:
			weekStr = "星期六";
		}
		return weekStr;
	}

	public static long getDateMiliDispersion(Date fromDate, Date toDate) {
		if ((fromDate == null) || (toDate == null)) {
			return 0L;
		}

		long fromTime = fromDate.getTime();
		long toTime = toDate.getTime();

		return toTime - fromTime;
	}

	public static int getDateDiff(Date fromDate, Date toDate) {
		if ((fromDate == null) || (toDate == null)) {
			return 0;
		}
		long fromTime = fromDate.getTime();
		long toTime = toDate.getTime();

		long diff = toTime - fromTime;
		// 1000 * 60 * 60 * 24
		Long longValue = new Long(diff / 86400000L);
		return longValue.intValue();
	}

	/**
	 * 描述：获取指定日期之前多少天的日期
	 * 
	 * @param date
	 *            指定日期
	 * @param day
	 *            天数
	 * @return 日期
	 */
	public static Date getDataDiff(Date date, int day) {
		if (date == null) {
			return null;
		}
		long time = date.getTime();
		time -= 86400000L * day;
		return new Date(time);
	}

	/**
	 * 描述：获取当前周
	 * 
	 * @return
	 */
	public static int getCurrentWeek() {
		Calendar calendar = Calendar.getInstance();
		int week = calendar.get(7);
		--week;
		if (week == 0) {
			week = 7;
		}
		return week;
	}

	/**
	 * 描述：获取中文当前周
	 * 
	 * @return
	 */
	public static String getCurrentWeekStr() {
		return getWeekStr(new Date());
	}

	/**
	 * 描述：获取本年
	 * 
	 * @return
	 */
	public static int getCurrentYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(1);
	}

	/**
	 * 描述：获取本月
	 * 
	 * @return
	 */
	public static int getCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(2) + 1;
	}

	/**
	 * 描述：获取本月的当前日期数
	 * 
	 * @return
	 */
	public static int getCurrentDay() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(5);
	}

	/**
	 * 描述：当前时间与指定时间的差
	 * 
	 * @param str
	 *            秒数
	 * @return 时间差，单位：秒
	 */
	public static int getUnixTime(String str) {
		if ((str == null) || ("".equals(str))) {
			return 0;
		}
		try {
			long utime = Long.parseLong(str) * 1000L;
			Date date1 = new Date(utime);

			Date date = new Date();

			long nowtime = (date.getTime() - date1.getTime()) / 1000L;
			return (int) nowtime;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("获取时差失败");
		}
		return 0;
	}

	/**
	 * 描述：去除日期字串中原“-”和“:”
	 * 
	 * @param dateTime日期字串
	 * @return
	 */
	public static String formatString(String dateTime) {
		if ((dateTime != null) && (dateTime.length() >= 8)) {
			String formatDateTime = dateTime.replaceAll("-", "");
			formatDateTime = formatDateTime.replaceAll(":", "");
			String date = formatDateTime.substring(0, 8);
			return date;
		}

		return "";
	}

	/**
	 * 描述：当前时间与指定时间的差
	 * 
	 * @param str
	 *            yyyy-MM-dd HH:mm:ss 格式的日期
	 * @return 时间差，单位：秒
	 */
	public static int getTimesper(String str) {
		if ((str == null) || ("".equals(str))) {
			return 0;
		}
		try {
			Date date1 = new Date(Long.parseLong(str));
			Date date = new Date();
			long nowtime = (date.getTime() - date1.getTime()) / 1000L;
			return (int) nowtime;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("日期转换出错");
		}
		return 0;
	}

	/**
	 * 描述：获取16位日期时间，yyyyMMddHHmmss
	 * 
	 * @param dateTime
	 *            字串日期
	 * @return
	 */
	public static String formatDateTime(String dateTime) {
		if ((dateTime != null) && (dateTime.length() >= 8)) {
			String formatDateTime = dateTime.replaceAll("-", "");
			formatDateTime = formatDateTime.replaceAll(":", "");
			String date = formatDateTime.substring(0, 8);
			String time = formatDateTime.substring(8).trim();
			for (int i = time.length(); i < 6; ++i) {
				time = time + "0";
			}
			return date + time;
		}

		return "";
	}

	/**
	 * 描述：获取16位日期时间，yyyyMMddHHmmss
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static String formatDateTime(Date date) {
		String dateTime = formatDate(date);
		return formatDateTime(dateTime);
	}
}