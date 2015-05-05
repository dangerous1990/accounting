package com.limeng.common;

import java.util.Calendar;

/**
 * 日历工具类
 * 
 * @author zhsys
 * 
 */
public class DateHelper
{

	public static int getCurrYear()
	{
		Calendar cal = Calendar.getInstance();

		return cal.get(Calendar.YEAR);
	}

	public static int getCurrMonth()
	{
		Calendar cal = Calendar.getInstance();

		return cal.get(Calendar.MONTH) + 1;
	}

	public static int getCurrDay()
	{
		Calendar cal = Calendar.getInstance();

		return cal.get(Calendar.DAY_OF_MONTH);
	}
	
	
	/**
	 * 获取指定日历所在的本周第一天的日历
	 * @param c
	 * @return
	 */
	public static Calendar weekFirst(Calendar c){
		Calendar cc = Calendar.getInstance();
		
		cc.setTimeZone(c.getTimeZone());
		cc.set(Calendar.YEAR, c.get(Calendar.YEAR));
		cc.set(Calendar.MONTH, c.get(Calendar.MONTH));
		cc.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH));
		
		cc.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY));
		cc.set(Calendar.MINUTE, c.get(Calendar.MINUTE));
		cc.set(Calendar.SECOND, c.get(Calendar.SECOND));
		cc.set(Calendar.MILLISECOND, c.get(Calendar.MILLISECOND));
		
		cc.add(Calendar.DAY_OF_WEEK, 1 - c.get(Calendar.DAY_OF_WEEK));
		
		
		return cc;
	}
	
	/**
	 * 获取指定日历所在的本周最后一天的日历
	 * @param c
	 * @return
	 */
	public static Calendar weekLast(Calendar calendar){
		Calendar c = Calendar.getInstance();
		
		c.setTimeZone(calendar.getTimeZone());
		c.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
		c.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		c.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
		
		c.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
		c.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
		c.set(Calendar.SECOND, calendar.get(Calendar.SECOND));
		c.set(Calendar.MILLISECOND, calendar.get(Calendar.MILLISECOND));
		
		c.add(Calendar.DAY_OF_WEEK, 7 - calendar.get(Calendar.DAY_OF_WEEK));
		
		return c;
	}
	
	/**
	 * 获取指定日期所在月份的第一天
	 * @return
	 */
	public static Calendar monthFirst(Calendar calendar){
		Calendar c = Calendar.getInstance();
		
		c.setTimeZone(calendar.getTimeZone());
		c.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
		c.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		
		c.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
		c.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
		c.set(Calendar.SECOND, calendar.get(Calendar.SECOND));
		c.set(Calendar.MILLISECOND, calendar.get(Calendar.MILLISECOND));
		
		c.add(Calendar.DAY_OF_MONTH, 1- calendar.get(Calendar.DAY_OF_MONTH));
		
		return c;
	}
	/**
	 * 获取指定日期所在月份的最后一天
	 * @return
	 */
	public static Calendar monthLast(Calendar calendar){
		Calendar c = Calendar.getInstance();
		
		c.setTimeZone(calendar.getTimeZone());
		c.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
		c.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		c.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
		
		c.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
		c.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
		c.set(Calendar.SECOND, calendar.get(Calendar.SECOND));
		c.set(Calendar.MILLISECOND, calendar.get(Calendar.MILLISECOND));
		
		c.add(Calendar.MONTH, 1); //下个月的这一天
		c.set(Calendar.DAY_OF_MONTH, 1);//下个月的第一天
		c.add(Calendar.DAY_OF_MONTH, -1);
		
		return c;
	}
	
	
//获取当天00：00：00.000
	public static Calendar start(Calendar c)
	{
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.YEAR, c.get(Calendar.YEAR));
		ca.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH));
		ca.set(Calendar.MONTH, c.get(Calendar.MONTH));
		ca.setTimeZone(c.getTimeZone());

		ca.set(Calendar.HOUR_OF_DAY, 0);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		ca.set(Calendar.MILLISECOND, 0);

		return ca;
	}
	//获取当天23：59：59.999
	public static Calendar end(Calendar c)
	{
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.YEAR, c.get(Calendar.YEAR));
		ca.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH));
		ca.set(Calendar.MONTH, c.get(Calendar.MONTH));
		ca.setTimeZone(c.getTimeZone());

		ca.set(Calendar.HOUR_OF_DAY, 23);
		ca.set(Calendar.MINUTE, 59);
		ca.set(Calendar.SECOND, 59);
		ca.set(Calendar.MILLISECOND, 999);
		
		return ca;
	}

	/**
	 * 获得指定日期的上一天
	 * @param c
	 * @return
	 */
	public static Calendar prevDay(Calendar calendar){
		Calendar c = Calendar.getInstance();
		
		c.setTimeZone(calendar.getTimeZone());
		c.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
		c.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		c.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
		
		c.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
		c.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
		c.set(Calendar.SECOND, calendar.get(Calendar.SECOND));
		c.set(Calendar.MILLISECOND, calendar.get(Calendar.MILLISECOND));
		
		c.add(Calendar.DAY_OF_MONTH, -1);
		
		return c;
	}
	
	/**
	 *  获得指定日期的上一周的当天
	 * @param calendar
	 * @return
	 */
    public static Calendar prevWeek(Calendar calendar){
    	Calendar c = Calendar.getInstance();
    	
    	c.setTimeZone(calendar.getTimeZone());
    	c.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
    	c.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
    	c.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
    	
    	c.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
		c.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
		c.set(Calendar.SECOND, calendar.get(Calendar.SECOND));
		c.set(Calendar.MILLISECOND, calendar.get(Calendar.MILLISECOND));
    	
    	c.add(Calendar.DAY_OF_MONTH, -7);
    	
    	return c; 
    }
    
    /**
     * 获得指定日期的上一月的当天
     * @param calendar
     * @return
     */
    public static Calendar prevMonth(Calendar calendar){
    	Calendar c = Calendar.getInstance();
    	
    	c.setTimeZone(calendar.getTimeZone());
    	c.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
    	c.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
    	c.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
    	
    	c.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
		c.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
		c.set(Calendar.SECOND, calendar.get(Calendar.SECOND));
		c.set(Calendar.MILLISECOND, calendar.get(Calendar.MILLISECOND));
    	
    	c.add(Calendar.MONTH, -1);
    	
    	return c; 
    }

	
	
	
}