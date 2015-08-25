package com.zy.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** 
 * @author LL 
 * 
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	private final static Log LOG = LogFactory.getLog(DateUtils.class);
	
	public final static DateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");
	
	public final static DateFormat dfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public final static DateFormat serialFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	public final static DateFormat dfLessonDate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public final static DateFormat dfHm = new SimpleDateFormat("HH:mm");
	
	/**
	 * 查询开始时间
	 * @param source
	 * @return
	 */
	public static Date getStartDate(String source){
		if(StringUtils.isNotBlank(source)){
			source = source.trim() + " 00:00:00";
			return parseDateTime(source);
		}
		return null;
	}
	
	public static Date getStartDate(Date date){
		String source = dfDate.format(date);
		return getStartDate(source);
	}
	
	/**
	 * 查询结束时间
	 * @param source
	 * @return
	 */
	public static Date getEndDate(String source){
		if(StringUtils.isNotBlank(source)){
			source = source.trim() + " 23:59:59";
			return parseDateTime(source);
		}
		return null;
	}
	
	public static Date getEndDate(Date date){
		String source = dfDate.format(date);
		return getEndDate(source);
	}
	
	/**
	 * 
	 * @param source
	 * @return
	 */
	public static Date parseDateTime(String source){
		if(StringUtils.isBlank(source)){
			return null;
		}
		Date date = null;
		try {
			date = dfDateTime.parse(source.trim());
		} catch (ParseException e) {
			LOG.error("解析时间异常：["+source+"] " + e.getMessage());
		}
		return date;
	}
	
	/**
	 * 
	 * @param source
	 * @return
	 */
	public static Date parseDate(String source){
		if(StringUtils.isBlank(source)){
			return null;
		}
		Date date = null;
		try {
			date = dfDate.parse(source.trim());
		} catch (ParseException e) {
			LOG.error("解析时间异常：["+source+"] " + e.getMessage());
		}
		return date;
	}
	
	/**
	 * 根据当前时间生成序列号
	 * @return
	 */
	public synchronized static String serialNumber(){
		return serialFormatter.format(new Date());
	}

	/**
	 * 月第一天
	 * @param date
	 * @return
	 */
	public static Date getMonthStartDate(Date date){
		Date d = new Date(date.getTime());
		d = setHours(d, 0);
		d = setMinutes(d, 0);
		d = setSeconds(d, 1);
		return setDays(d, 1);
	}
	
	/**
	 * 月最后一天
	 * @param date
	 * @return
	 */
	public static Date getMonthEndDate(Date date){
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
		int maxMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.set(Calendar.DAY_OF_MONTH, maxMonth);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	}
	
	public static Date getLessonDate(String source){
		if(StringUtils.isBlank(source)){
			return null;
		}
		Date date = null;
		try {
			date = dfLessonDate.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
