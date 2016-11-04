package com.jl.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	private static final String DEFAULT_DATE_FROMAT = "yyyy-MM-dd HH:mm:ss";

	private static final String DATE_FROMAT_WITHOUT_HOUR = "yyyy-MM-dd 00:00:00";

	private static final String DATE_FORMAT_END_TIME = "yyyy-MM-dd 23:59:59";

	private static final String DATE_FORMAT_DATE = "yyyy-MM-dd";
	
	private static final String DATE_FORMAT_T_TIME = "yyyy-MM-dd HH:mm:ssZZZ";

	/**
	 * 获得指定格式的时间字符串
	 * 
	 * @param dateFormat
	 * @param date
	 * @return
	 */
	public static String getTimeString(String dateFormat, Date date) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		return format.format(date);
	}

	/**
	 * 获取默认格式的现在时间字符串
	 * 
	 * @return
	 */
	public static String getNowTime() {
		return getTimeString(DEFAULT_DATE_FROMAT, new Date());
	}

	public static String getTime(Date date) {
		if (date == null) {
			return "";
		}
		return getTimeString(DEFAULT_DATE_FROMAT, date);
	}
	public static String getTTime(Date date) {
		if (date == null) {
			return "";
		}
		return getTimeString(DATE_FORMAT_T_TIME, date).replace(" ", "T");
	}
	public static Date getOneDayBeginTime() {
		Date date = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat(
					DATE_FROMAT_WITHOUT_HOUR);
			String str = format.format(new Date());
			date = format.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public static Date getOneDayEndTime() {
		Date date = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_END_TIME);
			String str = format.format(new Date());
			date = format.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public static Date getBeforeDayBiginTime(int days) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FROMAT_WITHOUT_HOUR);
		Date date = format.parse(getBeforeDaysTime(days));
		return date;
	}

	/**
	 * 获得指定格式 days天以前的时间字符串
	 * 
	 * @param dateFormat
	 * @param days
	 * @return
	 */
	public static String getBeforeDaysTime(int days) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -days);
		return getTimeString(DATE_FROMAT_WITHOUT_HOUR, c.getTime());

	}

	public static Date getDateTime(String dateTime) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FROMAT);
		Date date = format.parse(dateTime);
		return date;
	}

	public static Date getDate(String dateString, int hour)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_DATE);
		Date date = format.parse(dateString);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR, hour);
		return c.getTime();
	}

	public static String getDayFirstTime() {
		return getTimeString(DATE_FROMAT_WITHOUT_HOUR, getOneDayBeginTime());
	}

	public static String getDayEndTime() {
		return getTimeString(DATE_FROMAT_WITHOUT_HOUR, getOneDayEndTime());
	}

	/**
	 * 获取本月第一天
	 * 
	 * @return
	 */
	public static String getMonthFirstDay() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		return getTimeString(DATE_FROMAT_WITHOUT_HOUR, c.getTime());
	}

	public static String getTimeWithoutHour(Date date) {
		return getTimeString(DATE_FROMAT_WITHOUT_HOUR, date);
	}

	public static String getTodayWithoutHour() {
		return getTimeWithoutHour(new Date());
	}

	/**
	 * 获得开始时间字符串
	 * 
	 * @param beginTimeStr
	 * @return
	 * @throws ParseException
	 */
	public static String getBeginTimeStr(String beginTimeStr)
			throws ParseException {
		return getTimeStr(DATE_FROMAT_WITHOUT_HOUR, beginTimeStr);
	}

	/**
	 * 获得结束时间字符串
	 * 
	 * @param endTimeStr
	 * @return
	 * @throws ParseException
	 */
	public static String getEndTimeStr(String endTimeStr) throws ParseException {
		return getTimeStr(DATE_FORMAT_END_TIME, endTimeStr);
	}


	public static String getTimeStr(String dateFormat, String dateStr)
			throws ParseException {
		SimpleDateFormat dFormat = new SimpleDateFormat(DATE_FORMAT_DATE);
		Date date = dFormat.parse(dateStr);
		return getTimeString(dateFormat, date);
	}
	

	/**
	 * 
	 * @Title: computeCoolingOffPeriod 
	 * @Description: 获取两个时间之间相差的天数
	 * @param @param sBeginDate
	 * @param @param sEndDate
	 * @param @return   相差天数
	 * @return int    返回类型 
	 * @throws
	 */
	public static int computeCoolingOffPeriod(String sBeginDate,String sEndDate) 
    {
     Calendar calendar1=Calendar.getInstance();
     Calendar calendar2=Calendar.getInstance();
     try
     {
         SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");//格式很重要：是20051031，还是2005-10-31格式呢？

         if (sBeginDate.equals("0")) 
         {
          sBeginDate = "19000101"; 
         }
         calendar1.setTime(formatter1.parse(sBeginDate));
         calendar2.setTime(formatter1.parse(sEndDate));
         
     }
     catch(Exception e)
     {
      e.printStackTrace();
     }
     return  (int)( (calendar2.getTimeInMillis()-calendar1.getTimeInMillis())/1000/60/60/24 );//获取天数的差值。
     
    }
	/**
	 * 
	 * @Title: computeCoolingOffPeriod 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param sBeginDate
	 * @param @param sEndDate
	 * @param @param dateFormat
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	public static int computeCoolingOffPeriod(String sBeginDate,String sEndDate,String dateFormat) 
    {
     Calendar calendar1=Calendar.getInstance();
     Calendar calendar2=Calendar.getInstance();
     try
     {
         SimpleDateFormat formatter1 = new SimpleDateFormat(dateFormat);//格式很重要：是20051031，还是2005-10-31格式呢？

         if (sBeginDate.equals("0")) 
         {
          sBeginDate = "19000101"; 
         }
         calendar1.setTime(formatter1.parse(sBeginDate));
         calendar2.setTime(formatter1.parse(sEndDate));
         
     }
     catch(Exception e)
     {
      e.printStackTrace();
     }
     return  (int)( (calendar2.getTimeInMillis()-calendar1.getTimeInMillis())/1000/60/60/24 );//获取天数的差值。
     
    }
	
	/**
	 * 
	 * @Title: computeCoolingOffPeriod 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param date1
	 * @param @param date2
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	public static int computeCoolingOffPeriod(Date date1,Date date2)
	{
		 Calendar calendar1=Calendar.getInstance();
	     Calendar calendar2=Calendar.getInstance();
	     calendar1.setTime(date1);
         calendar2.setTime(date2);
         return  (int)( (calendar2.getTimeInMillis()-calendar1.getTimeInMillis())/1000/60/60/24 );//获取天数的差值。
	}
}
