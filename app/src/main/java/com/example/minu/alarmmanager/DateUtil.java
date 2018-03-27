package com.example.minu.alarmmanager;




import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static String getFormatedDate(long dateString, String format, String currentFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(currentFormat, Locale.ENGLISH);
        try {
            Date date = sdf.parse(String.valueOf(dateString));
            String formated = new SimpleDateFormat(format).format(date);
            return formated;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}

