

import com.google.common.base.Preconditions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public static String format(Date date, String format) {
        Preconditions.checkNotNull(date, "Input date must not be null");
        return new SimpleDateFormat(format).format(date);
    }

    public static String format(Date date) {
        return format(date, DEFAULT_DATE_FORMAT);
    }

}
