package demo.bank.kata.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeFormatter {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd HH:mm:ss");

    private DateTimeFormatter() {
        // empty
    }

    public static final String format(Date date) {
        if (date == null) {
            return "";
        }
        return SIMPLE_DATE_FORMAT.format(date);
    }

    public static final String format(Calendar calendar) {
        if (calendar == null) {
            return "";
        }
        return SIMPLE_DATE_FORMAT.format(calendar.getTime());
    }
}
