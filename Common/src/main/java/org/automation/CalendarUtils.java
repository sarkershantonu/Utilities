package org.automation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class CalendarUtils {
    private static Calendar cal = new GregorianCalendar();
    public static Calendar getCalender(){
        return cal;
    }
    static {
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTimeZone(TimeZone.getTimeZone("EST"));

    }

    public static Date resetTime(Date d) {
        cal.setTime(d);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
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
    public static Date convertWithFprmat(String date,String format) {
        DateFormat df = new SimpleDateFormat(format);
        Date aDate = null;
        try {
            aDate = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return aDate;
    }
    public static int getDays(String startDate, String endDate) {
        return (int) (((convert(endDate)).getTime() - (convert(startDate)).getTime()) / (1000 * 60 * 60 * 24)) + 1;
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

    public static int getWeek(String startDate, String endDate) {
        return getWeeksBetween((convert(startDate)), (convert(endDate)));
    }
}
