package com.breakthrough.scheduler.common;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateTool {
	
	public static LocalDate toLocalDate(final Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public static LocalDate toLocalDate(final int dayOfMonth, final int month, final int year) {
		return LocalDate.of(year, month, dayOfMonth);
	}
	
	public static Date toDate(final LocalDate localDate) {
		return java.util.Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
	
}
