package com.apps.yecotec.paperbud.domain.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.text.DateFormatSymbols;

/**
 * Created by kenruizinoue on 12/4/17.
 */

public class TimeUtil {

    public static long getTimeDifference(String time1, String time2) {

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date startDate = dateFormat.parse(time1);
            Date stopDate = dateFormat.parse(time2);

            //in milliseconds
            long diff = stopDate.getTime() - startDate.getTime();

            long diffHour = diff / (60 * 60 * 1000) % 24;
            return diffHour;

        } catch(Exception e) { //this generic but you can control another types of exception
            // look the origin of excption
        }

        return 0;
    }

    public static String getOnlyDate(String timeStampString) {
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(timeStampString);

            Calendar cal = Calendar.getInstance();
            cal.setTime(parsedDate);

            String monthString = new DateFormatSymbols().getMonths()[cal.get(Calendar.MONTH)];
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int year = cal.get(Calendar.YEAR);

            if(year < 1900) return null;

            return  capitalizeFirstLetter(monthString) + " " + day + ", " + year;

        } catch(Exception e) {
        }

        return null;
    }

    public static String capitalizeFirstLetter(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }
}
