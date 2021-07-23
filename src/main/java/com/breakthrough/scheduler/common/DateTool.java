package com.breakthrough.scheduler.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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
	
	/**
	 * @param dateString
	 * @param dateFormat ex: "yyyy/MM/dd"
	 * @return
	 * @throws Exception
	 */
	public static Date toDate(final String dateString, final String dateFormat) throws Exception {
		DateFormat df = new SimpleDateFormat(dateFormat);
		return df.parse(dateString);
	}
	
	public static String now() {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		return df.format(new Date());
	}
	
	public static String toString(final Date date) {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		return df.format(date);
	}
}
