package com.example.tabletapp.Tablet;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTablet {
    private Date date;
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd");
    private SimpleDateFormat dayAndMonthFormat = new SimpleDateFormat("dd.MM");
    private SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");

    public String getTime() {
        date = new Date();
        return timeFormat.format(date);
    }

    public String getDayAndMonth() {
        date = new Date();
        return dayAndMonthFormat.format(date);
    }

    public String getDate() {
        String result = dateFormat.format(date);
        String month = getMonthToCase(monthFormat.format(date));
        return Character.toUpperCase(result.charAt(0)) +
                result.substring(1) + " " +
                month;
    }

    private String getMonthToCase(String month) {
        char end = month.charAt(month.length() - 1);
        if(end == 'ь' || end == 'й')
            return month.substring(0, month.length() - 1) + "я";
        else if (end == 'т')
            return month + "а";
        else return month;
    }
}
