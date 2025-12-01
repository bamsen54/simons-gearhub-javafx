package com.simonsgearhubjavafx.regex;

import java.util.regex.Pattern;

public class Regex {

    public static boolean isMatch( String string, String pattern ) {
        return Pattern.compile( pattern, Pattern.CASE_INSENSITIVE ).matcher( string ).matches();
    }

    public static boolean isFound( String string, String pattern ) {
        return Pattern.compile( pattern, Pattern.CASE_INSENSITIVE ).matcher( string ).find();
    }
}
