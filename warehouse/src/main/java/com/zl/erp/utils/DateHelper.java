package com.zl.erp.utils;

import org.apache.commons.lang3.time.DurationFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {

	public static final String Y = "yyyy";

	public static final String Y_M = "yyyy-MM";

	public static final String Y_M_D = "yyyy-MM-dd";

	public static final String Y_M_D_H_M = "yyyy-MM-dd HH:mm";

	public static final String Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";

	public static final String Y_M_D_H_M_S_S = "yyyyMMddHHmmssSSS";

	public static final String MM_DD = "MM月dd日";

	public static final String M_D = "MM月dd日";

	public static final String YYYYMMDD = "yyyyMMdd";

	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	public static final String HH_MM = "HH:mm";

	public static final String YYYYMMDD_SPRIT = "yyyy/MM/dd";

	public static final String YYMMDD = "yyMMdd";

	private DateHelper() {

	}

	public static String getFormatDate() {
		return getFormatDate(Y_M_D, new Date());
	}

	public static String getFormatDate(String format) {
		return getFormatDate(format, new Date());
	}

	public static String getFormatDate(Date date) {
		return getFormatDate(Y_M_D, date);
	}

	public static String getFormatDate(String format, Date date) {
		return new SimpleDateFormat(format).format(date);
	}

	public static String getFormatDate(String format, String date) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			return dateFormat.format(dateFormat.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String getFormatYesterday() {
		return getFormatYesterday(Y_M_D, new Date());
	}

	public static String getFormatYesterday(String format) {
		return getFormatYesterday(format, new Date());
	}

	public static String getFormatYesterday(Date date) {
		return getFormatYesterday(Y_M_D, date);
	}

	public static String getFormatYesterday(String format, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1);
		return getFormatDate(format, calendar.getTime());
	}

	public static String getYesterdayStartTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return getFormatDate(Y_M_D_H_M_S, calendar.getTime());
	}

	public static String getYesterdayStartTime(String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return getFormatDate(format, calendar.getTime());
	}

	public static String getYesterdayStartTime(String format, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return getFormatDate(format, calendar.getTime());
	}

	public static String getYesterdayStartTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return getFormatDate(Y_M_D_H_M_S, calendar.getTime());
	}

	public static String getNextDate(String date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parseDate(date));
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return getFormatDate(Y_M_D, calendar.getTime());
	}

	public static Date parseDate(String source) {
		try {
			return new SimpleDateFormat(Y_M_D).parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date parseDate(String source, String format) {
		try {
			return new SimpleDateFormat(format).parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date parseDate(Date source, String format) {
		try {
			return new SimpleDateFormat(format).parse(new SimpleDateFormat(format).format(source));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getOtherDay(int date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return getFormatDate(YYYYMMDD, calendar.getTime());
	}

	/**
	 *
	 * @Title: getLastTwelveMonths
	 * @Description: 最近12个月日期
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getLastTwelveMonths() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -11);
		return getFormatDate(Y_M, calendar.getTime());
	}

	public static String getLastDay(int d) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -d);
		return getFormatDate(Y_M_D, calendar.getTime());
	}

	public static String getLastMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return getFormatDate(Y_M, calendar.getTime());
	}

	public static String getLastMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return getFormatDate(Y_M_D, calendar.getTime());
	}

	public static Date getMonth() {
		return parseDate(getFormatDate(Y_M), Y_M);
	}

	/**
	 * 几天后的时间
	 * @param d
	 * @param day
	 * @return
	 */
	public static String getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return getFormatDate(Y_M_D, now.getTime());
	}

	/**
	 * 获取传入时间的  凌晨 和 23.59.59时刻 flag=0 表示凌晨 flag=1 表示当天最后时间
	 * @param date
	 * @param flag
	 * @return
	 */
	public static Date weeHours(Date date, int flag) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		// 时分秒（毫秒数）
		long millisecond = hour * 60 * 60 * 1000 + minute * 60 * 1000 + second * 1000;
		// 凌晨00:00:00
		cal.setTimeInMillis(cal.getTimeInMillis() - millisecond);

		if (flag == 0) {
			return cal.getTime();
		} else if (flag == 1) {
			// 凌晨23:59:59
			cal.setTimeInMillis(cal.getTimeInMillis() + 23 * 60 * 60 * 1000 + 59 * 60 * 1000 + 59 * 1000);
		}
		return cal.getTime();
	}

	/**
	 * 几天后的时间
	 * @param date
	 * @param day
	 * @return
	 */
	public static String getDateAfter(String date, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(parseDate(date));
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return getFormatDate(Y_M_D, now.getTime());
	}

	/**
	 * 获取传入月第一天
	 * @param date
	 * @return
	 */
	public static String findMonthFirstDay(String date) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(Y_M);
			Calendar cale = Calendar.getInstance();
			cale.setTime(format.parse(date));
			cale.add(Calendar.MONTH, 0);
			cale.set(Calendar.DAY_OF_MONTH, 1);
			format = new SimpleDateFormat(Y_M_D);
			return format.format(cale.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 
	 * @Title: findMonthFirstDay
	 * @Description: 获取当月第一天
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String findMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return getFormatDate(Y_M_D, calendar.getTime());
	}

	/**
	 * 
	 * @Title: getTodayEndTime
	 * @Description:获取当天结束时间 
	 * @param @return
	 * @return Date
	 * @throws
	 */
	public static Date getTodayEndTime() {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR_OF_DAY, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTime();
	}

}
