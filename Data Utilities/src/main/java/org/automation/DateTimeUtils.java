package org.automation;

import com.google.common.base.Preconditions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {
    public static final String DEFAULT_DATE_FORMAT = "dd/MM/yy";
    public static final String dd_MM_yyyy = "dd-MM-yyyy";
    public static final String DD_MM_YY = "dd-MM-yy";
    public static final String DD_MMM_YYYY = "dd-MMM-yyyy";
    public static final String DDD_MMM_yyyy = "DD-MMM-yyyy";
    public static final String DD_M_yyyy = "DD-M-yyyy";

    public static Date parse(String date, String format) {
        Preconditions.checkNotNull(date, "Input date must not be null");

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
	
	
public static Date subtractMonthFromDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		return cal.getTime();
	}
	
	 public static String getDayOfAWeek(Date date){
        DateFormat format2=new SimpleDateFormat("EEEE");
        return format2.format(date);
    }
	
	public static Date getFirstDayOfFirstSunday(Date date) {
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate firstDayOfFirstWeek = localDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY));
		firstDayOfFirstWeek = firstDayOfFirstWeek.minusDays(6);
		return Date.from(firstDayOfFirstWeek.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
    public static Date getPreviousMonday(Date date) {
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate lastMonday = localDate.minusDays(6);
		return Date.from(lastMonday.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
    public static String format(Date date, String format) {
        Preconditions.checkNotNull(date, "Input date must not be null");
        return new SimpleDateFormat(format).format(date);
    }
    public static Date getLastSunday(Date date) {
	LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	LocalDate lastSunday = localDate.with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY));

		return Date.from(lastSunday.atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant());
	}
    public static String format(Date date) {
        return format(date, DEFAULT_DATE_FORMAT);
    }

}
