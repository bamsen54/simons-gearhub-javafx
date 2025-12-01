package com.simonsgearhubjavafx.time;

import java.time.LocalDateTime;

public class LocalDateTimeToString {

    public static String toString(LocalDateTime localDateTime) {

        String year   = String.valueOf( localDateTime.getYear() );
        String month  = String.valueOf( localDateTime.getMonthValue() );
        String day    = String.valueOf( localDateTime.getDayOfMonth() );
        String hour   = String.valueOf( localDateTime.getHour() );
        String minute = String.valueOf( localDateTime.getMinute() );
        String second = String.valueOf( localDateTime.getSecond() );

        if( month.length() == 1 )
            month = "0" + month;

        if( day.length() == 1 )
            day = "0" + day;

        if( hour.length() == 1 )
            hour = "0" + hour;

        if( minute.length() == 1 )
            minute = "0" + minute;

        if( second.length() == 1 )
            second = "0" + second;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append( year ).append( "/" ).append( month ).append( "/" ).append( day ).append( " " );
        stringBuilder.append( hour ).append( ":" ).append( minute ).append( ":" ).append( second );

        return stringBuilder.toString();
    }
}
