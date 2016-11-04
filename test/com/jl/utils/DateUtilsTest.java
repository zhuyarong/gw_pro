package com.jl.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

public class DateUtilsTest {
	@Test
	public void getTTime(){
		System.out.println(DateUtils.getTTime(new Date()));
		
	}
	@Test
	public void testTimeZone(){
		Calendar cal = Calendar.getInstance();
		TimeZone timeZone = cal.getTimeZone();
		System.out.println(timeZone.getDSTSavings());
		System.out.println(timeZone.getID());
		System.out.println(timeZone.getDisplayName());
	}
}
