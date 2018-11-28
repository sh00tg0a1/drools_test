package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cx on 2018/11/22.
 */
public class DateHelper {
    public static String sFormat = "yyyy-MM-dd";

    public static Date getDate(String sDate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(sFormat);
        return sdf.parse(sDate);
    }

    public static Date getDate(String sDate, String anotherFormat) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(anotherFormat);
        return sdf.parse(sDate);
    }
}
