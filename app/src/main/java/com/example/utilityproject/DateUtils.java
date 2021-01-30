package com.example.utilityproject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static final String dateFormatYearMonthDay = "yyyy-MM-dd";
    public static final String dateFormatStandard_yyyy_MM_dd_HH_mm_ss= "yyyy-MM-dd HH:mm:ss";
    public static String formatDate(Date date, String inputDateFormat){
        DateFormat dateFormat = new SimpleDateFormat(inputDateFormat, Locale.getDefault());
        return dateFormat.format(date);
    }
    public static Date parse(String timeStamp, String inputDateFormat) {

        SimpleDateFormat formatter = new SimpleDateFormat(dateFormatStandard_yyyy_MM_dd_HH_mm_ss);
        String dateString = formatter.format(new Date(Long.parseLong(timeStamp)));

        DateFormat df = new SimpleDateFormat(inputDateFormat, Locale.getDefault());
        Date date = new Date();
        try {
            date = df.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
