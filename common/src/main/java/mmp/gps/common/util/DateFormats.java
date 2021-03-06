package mmp.gps.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 工具集
 */
public class DateFormats {
    /**
     * 日期格式化: yyyy-MM-dd
     */
    public static SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 日期时间格式化: yyyy-MM-dd HH:mm:ss
     */
    public static SimpleDateFormat DateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String toDateTimeString(Date date) {
        if (date == null)
            return "";
        return DateTimeFormat.format(date);
    }

    public static String toDateString(Date date) {
        if (date == null)
            return "";
        return DateFormat.format(date);
    }
}
