package com.breakthrough.scheduler.common;

public class CronUtils {
	public static String executeMonthly(final int minute, final int hour, final int day) {
		 return String.format("0 %d %d %d * ?", minute, hour, day);		 
	}
	
	public static String excuteEveryOtherMonth(final int minute, final int hour, final int day) {
		 return String.format("0 %d %d %d 1/2 ? *", minute, hour, day);		
	}
}
