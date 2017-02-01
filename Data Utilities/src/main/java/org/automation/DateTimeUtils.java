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
	private static Calendar cal = new GregorianCalendar();
    public static final String DEFAULT_DATE_FORMAT = "dd/MM/yy";
    public static final String dd_MM_yyyy = "dd-MM-yyyy";
    public static final String DD_MM_YY = "dd-MM-yy";
    public static final String DD_MMM_YYYY = "dd-MMM-yyyy";
    public static final String DDD_MMM_yyyy = "DD-MMM-yyyy";
    public static final String DD_M_yyyy = "DD-M-yyyy";
     
	public static Date resetTime(Date d) {

        cal.setTime(d);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
	public static int getWeeksBetween(Date a, Date b) {

        if (b.before(a)) {
            return -getWeeksBetween(b, a);
        }
        a = resetTime(a);
        b = resetTime(b);


        cal.setTime(a);
        int weeks = 0;
        while (cal.getTime().before(b)) {
            cal.add(Calendar.WEEK_OF_YEAR, 1);
            weeks++;
        }
        return weeks;
    }

    public static Date parse(String date, String format) {
        Preconditions.checkNotNull(date, "Input date must not be null");

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
	  public static Date convert(String dd_mmm_yy) {
        DateFormat df = new SimpleDateFormat("dd-MMM-yy");
        Date aDate = null;
        try {
            aDate = df.parse(dd_mmm_yy);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return aDate;
    }
	  public static Date convertWithFormat(String date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        Date aDate = null;
        try {
            aDate = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return aDate;
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
	  private static int getDays(String startDate, String endDate) {
        //http://www.calculator.net/date-calculator.html?
        return (int) (((convert(endDate)).getTime() - (convert(startDate)).getTime()) / (1000 * 60 * 60 * 24)) + 1;

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
	
	 private static int getWeek(String startDate, String endDate) {
        //http://www.calculator.net/date-calculator.html?
        return getWeeksBetween((convert(startDate)), (convert(endDate)));
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
